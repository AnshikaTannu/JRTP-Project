package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.QuoteDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UserDto;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.User;
import in.ashokit.repository.CityRepo;
import in.ashokit.repository.CountryRepo;
import in.ashokit.repository.StateRepo;
import in.ashokit.repository.UserRepo;
import in.ashokit.util.EmailUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private EmailUtil emailUtil;

	Random r = new Random();

	private QuoteDto[] quotations = null;

	@Override
	public UserDto getUser(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			return null;

		}

		ModelMapper mapper = new ModelMapper();
		return  mapper.map(user, UserDto.class);

		
	}

	@Override
	public boolean registerUser(RegisterDto regDto) {

		ModelMapper mapper = new ModelMapper();
		User entity = mapper.map(regDto, User.class);

		Country country = countryRepo.findById(regDto.getCountryId()).orElseThrow();

		State state = stateRepo.findById(regDto.getStateId()).orElseThrow();

		City city = cityRepo.findById(regDto.getCityId()).orElseThrow();

		entity.setCountry(country);
		entity.setState(state);
		entity.setCity(city);

		entity.setPwd(generateRandom());
		entity.setPwdUpdate("no");

		User saveEntity = userRepo.save(entity);
		String subject = "User Registration";

		String body = "Your temporary Pwd is " + entity.getPwd();
		emailUtil.sendEmail(regDto.getEmail(), subject, body);
		return saveEntity.getuId() != null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countryMap = new HashMap<>();

		List<Country> countryList = countryRepo.findAll();
		countryList.forEach(c ->
			countryMap.put(c.getCountryId(), c.getCountryName())

		);
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer cid) {

		Map<Integer, String> statesMap = new HashMap<>();

		List<State> stateList = stateRepo.getStates(cid);
		stateList.forEach(s -> 
			statesMap.put(s.getStateId(), s.getStateName())
		);
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer sid) {

		Map<Integer, String> cityMap = new HashMap<>();

		List<City> cityList = cityRepo.getCities(sid);
		cityList.forEach(c -> 
			cityMap.put(c.getCityId(), c.getCityName())
		);
		return cityMap;
	}

	@Override
	public UserDto getUser(LoginDto loginDto) {
		User user = userRepo.findByEmailAndPwd(loginDto.getEmail(), loginDto.getPwd());

		if (user == null) {
			return null;
		}

		ModelMapper mapper = new ModelMapper();
		return mapper.map(user, UserDto.class);

	}

	@Override
	public boolean resetPwd(ResetPwdDto pwdDto) {
		User user = userRepo.findByEmailAndPwd(pwdDto.getEmail(), pwdDto.getOldPwd());

		if (user != null) {
			user.setPwd(pwdDto.getNewPwd());
			user.setPwdUpdate("YES");
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public String getQuote() {

		if (quotations == null) {
			String url = "https://type.fit/api/quotes";

			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
			String responseBody = forEntity.getBody();
			ObjectMapper mapper = new ObjectMapper();

			try {
				quotations = mapper.readValue(responseBody, QuoteDto[].class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(quotations!=null) {
		int index = r.nextInt(quotations.length - 1);

		return quotations[index].getText();
	}else {
		return null;
	}
	}

	private String generateRandom() {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int randIndex = r.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}

}
