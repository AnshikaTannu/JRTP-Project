package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.User;
import in.ashokit.repo.UserRepo;
import in.ashokit.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userrepo;
	@Autowired
	private EmailUtils emailutils;

	@Override
	public boolean saveUser(User user) {
		User saveUser = userrepo.save(user);
		if (saveUser.getUid() != null) {
			String subject = "Your account is created";
			String body = "<h1>Congratulation, Welcome to Ashok IT</h1>";
			emailutils.sendEmail(user.getEmail(), subject, body);
		}
		return true;
	}

	@Override
	public User getUser(String email, String pwd) {
		return userrepo.findByEmailAndPwd(email, pwd);
	}

}
