package hello.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository {

	List<String> getAllPersonName();
}
