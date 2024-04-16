package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellers;
import in.ashokit.repo.CounsellersRepo;

@Service
public class ConsellerServiceImpl implements CounsellerService {

	@Autowired
	private CounsellersRepo counsellerRepo;

	@Override
	public boolean saveCounseller(Counsellers counseller) {
		
		Counsellers findByEmail = counsellerRepo.findByEmail(counseller.getEmail());
		if(findByEmail !=null) {
			return false;
		}
		
		Counsellers save = counsellerRepo.save(counseller);
		return save.getCounseller_Id() != null;
	}

	@Override
	public Counsellers getCounsellers(String email, String pwd) {
		return counsellerRepo.findByEmailAndPwd(email, pwd);
	}

}
