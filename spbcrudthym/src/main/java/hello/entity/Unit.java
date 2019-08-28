package hello.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "UNITS")
public class Unit {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence-generator")
	@SequenceGenerator(
			name = "sequence-generator",
			schema = "PORTAL",
			sequenceName = "UNITS_SEQ",
			allocationSize = 1
			)
	private Integer id;
	
	//@UniqueElements(message = "This unit code is used.")
	@NotBlank(message = "Code of unit is mandatory.")
	private String code;
	
	@NotBlank(message = "Name of unit is mandatory.")
	private String name;
	
	@OneToMany(mappedBy = "unit")
	private List<Person> persons = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public void addPerson(Person person) {
		persons.add(person);
		person.setUnit(this);
	}
	
	public void removePerson(Person person) {
		persons.remove(person);
		person.setUnit(null);
	}
	
}
