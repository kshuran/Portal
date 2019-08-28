package hello.dao;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

@Component
public class LdapUserRepositoryImpl implements LdapUserRepository {

	private LdapTemplate ldapTemplate;

	@Autowired
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	public List<String> getAllPersonName() {
		return ldapTemplate.search(query()
				.attributes("uid")
				.where("objectclass").is("person"), 
				new AttributesMapper<String>() {
					public String mapFromAttributes(Attributes attrs) throws NamingException {
						return attrs.get("uid").get().toString();
					}
				});
	}
}
