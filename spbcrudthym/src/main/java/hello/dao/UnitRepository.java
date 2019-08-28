package hello.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.entity.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Integer>{

}
