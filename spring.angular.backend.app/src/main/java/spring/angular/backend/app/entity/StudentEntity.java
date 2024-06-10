package spring.angular.backend.app.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import spring.angular.backend.app.enums.EnumTypes.Section;
import spring.angular.backend.app.enums.EnumTypes.Standard;

@Entity
@Table(name = "DC_STUDENT")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLL_NO")
	private long id;

	@NotNull
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MID_NAME")
	private String middleName;

	@NotNull
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "SECTION")
	@Enumerated(EnumType.STRING)
	private Section section;

	@Column(name = "STANDARD")
	@Enumerated(EnumType.STRING)
	private Standard standard;

	@Column(name = "DATE_OF_BIRTH")
	private Date dob;

	@OneToOne
	@JoinColumn(name = "SCHOOL_ID", nullable = true)
	private SchoolEntity school;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public SchoolEntity getSchool() {
		return school;
	}

	public void setSchool(SchoolEntity school) {
		this.school = school;
	}
}