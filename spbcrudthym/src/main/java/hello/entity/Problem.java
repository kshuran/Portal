package hello.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "PROBLEMS")
public class Problem {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "sequence-generator")
	@SequenceGenerator(
			name = "sequence-generator",
			schema = "PORTAL",
			sequenceName = "PROBLEMS_SEQ",
			allocationSize = 1
			)
	private Integer id;
	
	@Column(insertable = true, updatable = false)
	private String person;
	
	@Column(insertable = true, updatable = false)
	@Enumerated(EnumType.STRING)
	private Product product;
	
	@Column(name = "PROBLEM", insertable = true, updatable = false)
	@Size(max = 2000)
	@NotBlank(message = "Description of problem is mandatory")
	private String problemDescription;
	
	@Column(name = "CREATE_DATETIME", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date problemDateTime;
	
	@Column(name = "RESOLVED", insertable = false)
	@Type(type = "numeric_boolean")
	private Boolean isResolved;
	
	@Column(name = "RESOLVED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolvedDateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public Date getProblemDateTime() {
		return problemDateTime;
	}

	public void setProblemDateTime(Date problemDateTime) {
		this.problemDateTime = problemDateTime;
	}

	public Boolean getIsResolved() {
		return isResolved;
	}

	public void setIsResolved(Boolean isResolved) {
		this.isResolved = isResolved;
	}

	public Date getResolvedDateTime() {
		return resolvedDateTime;
	}

	public void setResolvedDateTime(Date resolvedDateTime) {
		this.resolvedDateTime = resolvedDateTime;
	}

	@Override
	public String toString() {
		return "Problem [id=" + id + ", person=" + person + ", product=" + product + ", problemDescription="
				+ problemDescription + ", problemDateTime=" + problemDateTime + ", isResolved=" + isResolved
				+ ", resolvedDateTime=" + resolvedDateTime + "]";
	}
    
}
