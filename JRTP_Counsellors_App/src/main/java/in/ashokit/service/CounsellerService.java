package in.ashokit.service;

import in.ashokit.entity.Counsellers;

public interface CounsellerService {

	
	public boolean saveCounseller(Counsellers counseller);


	public Counsellers getCounsellers(String email, String pwd);
}
