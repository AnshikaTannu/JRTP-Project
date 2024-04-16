package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.City;
import java.util.List;


public interface CityRepo extends JpaRepository<City, Integer> {
	
	@Query(value = "select * from city where state_id=:sid", nativeQuery = true)
	public List<City> getCities(Integer sid);

}
