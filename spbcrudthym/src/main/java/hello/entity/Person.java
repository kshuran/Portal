package hello.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "PERSONS")
public class Person {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence-generator")
	@SequenceGenerator(
			name = "sequence-generator",
			schema = "PORTAL",
			sequenceName = "PERSONS_SEQ",
			allocationSize = 1
			)
	private Integer id;
	
	@Column(name = "PERSON_CODE")
	//@UniqueElements(message = "This number of employee is used.")
	@Positive(message = "Number of employee must be positive number.")
	private int number;
	
	//@UniqueElements(message = "This employee has created already.")
	@NotBlank(message = "Name of employee is mandatory.")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "unit_id",
		foreignKey = @ForeignKey(name = "PERS_UNITID_FK"))
	private Unit unit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
	
}
