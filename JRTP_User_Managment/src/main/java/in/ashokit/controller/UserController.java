package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UserDto;
import in.ashokit.service.UserService;
import in.ashokit.util.AppConstant;
import in.ashokit.util.AppProperties;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	public AppProperties prop;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("registerDto", new RegisterDto());
		Map<Integer, String> countriesMap = userService.getCountries();
		model.addAttribute("countries", countriesMap);

		return AppConstant.REG_VIEW;
	}

	@GetMapping("/states/{cid}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable("cid") Integer cid) {

		return userService.getStates(cid);
	}

	@GetMapping("/cities/{sid}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable("sid") Integer sid) {
		return userService.getCities(sid);
	}

	@PostMapping("/register")
	public String register(RegisterDto regDto, Model model) {
		model.addAttribute("countries", userService.getCountries());
		Map<String, String> messages = prop.getMessages();

		UserDto user = userService.getUser(regDto.getEmail());
		if (user != null) {
			model.addAttribute(AppConstant.ERROR_MSG, messages.get("dupEmail"));

		} else {
			boolean registerUser = userService.registerUser(regDto);
			if (registerUser) {
				model.addAttribute(AppConstant.SUCC_MSG, messages.get("regSucc"));
			} else {
				model.addAttribute(AppConstant.ERROR_MSG, messages.get("regFail"));
			}
		}
		return AppConstant.REG_VIEW;
	}

	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return "index";
	}

	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model) {
		Map<String, String> messages = prop.getMessages();

		UserDto user = userService.getUser(loginDto);
		if (user == null) {
			model.addAttribute(AppConstant.ERROR_MSG, messages.get("invalidCredentials"));
			return "index";
		}

		if ("YES".equals(user.getPwdUpdate())) {
			return "redirect:dashboard";
		} else {
			// pwd not uploaded
			ResetPwdDto resetPwdDto = new ResetPwdDto();
			resetPwdDto.setEmail(user.getEmail());
			model.addAttribute("resetPwdDto", resetPwdDto);
			return AppConstant.RESET_PWD_VIEW;
		}
	}

	@PostMapping("/resetPwd")
	public String resetPwd(ResetPwdDto pwdDto, Model model) {

		Map<String, String> messages = prop.getMessages();

		if ((!pwdDto.getNewPwd().equals(pwdDto.getConfirmPwd()))) {
			model.addAttribute(AppConstant.ERROR_MSG, messages.get("pwdMismatch"));
			return AppConstant.RESET_PWD_VIEW;
		}

		UserDto user = userService.getUser(pwdDto.getEmail());
		if (user.getPwd().equals(pwdDto.getOldPwd())) {

			boolean resetPwd = userService.resetPwd(pwdDto);
			if (resetPwd) {
				return "redirect:dashboard";
			} else {
				model.addAttribute(AppConstant.ERROR_MSG, messages.get(" pwdUpdateErr"));
				return AppConstant.RESET_PWD_VIEW;

			}
		} else {
			model.addAttribute(AppConstant.ERROR_MSG, messages.get("oldPwdErr"));
			return AppConstant.RESET_PWD_VIEW;
		}
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		String quote = userService.getQuote();
		model.addAttribute("quote", quote);
		return "dashboardView";
	}

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

}
