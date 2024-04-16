package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Counsellers;

public interface CounsellersRepo extends JpaRepository<Counsellers, Integer> {

	public Counsellers findByEmailAndPwd(String email,String pwd);
	
	public Counsellers findByEmail(String email);
	
}
