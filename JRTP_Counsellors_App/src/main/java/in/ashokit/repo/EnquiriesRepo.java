package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Enquiries;

public interface EnquiriesRepo extends JpaRepository<Enquiries, Integer> {

	@Query(value = "select count(*) from  Enquiries where counseller_Id=:id", nativeQuery = true)
	public Long getEnquries(Integer id);

	@Query(value = "select count(*) from  Enquiries where counseller_Id=:id and status=:status", nativeQuery = true)
	public Long getEnquries(Integer id, String status);

}
