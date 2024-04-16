package in.ashokit.service;

import in.ashokit.entity.Counsellers;

public interface CounsellerService {

	// Registration
	public boolean saveCounseller(Counsellers counseller);

	// login
	public Counsellers getCounsellers(String email, String pwd);
}
