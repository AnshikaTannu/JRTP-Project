package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {

	@Query(value = "select * from state where country_id=:cid", nativeQuery = true)
	public List<State> getStates(Integer cid);

}
