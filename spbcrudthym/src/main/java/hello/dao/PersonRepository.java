package hello.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{

	List<Person> findByUnitId(int unitId);
}
