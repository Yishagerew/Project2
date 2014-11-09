package eHealth.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;
//import java.text.SimpleDateFormat;

/**
 * The persistent class for the PERSON database table.
 * 
 */
/**
 * 	SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
	String dateInString = "Friday, Jun 7, 2013 12:10:56 PM";		
 
	try {
 
		Date date = formatter.parse(dateInString);
		System.out.println(date);
		System.out.println(formatter.format(date));
 
	} catch (ParseException e) {
		e.printStackTrace();
	}
 * @author jc
 * @DateModified Nov 7, 201412:26:17 AM
 */
@Entity
@Table(name="PERSON")
@NamedQueries({@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
			   @NamedQuery(name="Person.findByPersonId",query="SELECT p FROM Person p WHERE p.personId=:personId")

})

@XmlRootElement
public class Person implements Serializable {
	private static final long serialVersionUID = 100L;
	@Id
	@Column(name = "personId")
	private int personId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "middleName")
	private String middleName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "createdDate")
	private String createdDate;
	@Column(name = "updatedDate")
	private String updatedDate;
	// bi-directional many-to-one association to HealthProfile
	@OneToMany(mappedBy = "person",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<HealthProfile> healthprofiles;

	// bi-directional many-to-one association to MeasureHistory
	@OneToMany(mappedBy = "person", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<MeasureHistory> measurehistories;

	public String getCreatedDate() {
		
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElementWrapper(name="HealthProfile")
	public List<HealthProfile> getHealthprofiles() {
		return this.healthprofiles;
	}

	public void setHealthprofiles(List<HealthProfile> healthprofiles) {
		this.healthprofiles = healthprofiles;
	}

	public HealthProfile addHealthprofile(HealthProfile healthprofile) {
		getHealthprofiles().add(healthprofile);
		healthprofile.setPerson(this);

		return healthprofile;
	}

	public HealthProfile removeHealthprofile(HealthProfile healthprofile) {
		getHealthprofiles().remove(healthprofile);
		healthprofile.setPerson(null);

		return healthprofile;
	}

	@XmlElementWrapper(name="MeasuredHistories")
	public List<MeasureHistory> getMeasurehistories() {
		return this.measurehistories;
	}

	public void setMeasurehistories(List<MeasureHistory> measurehistories) {
		this.measurehistories = measurehistories;
	}

	public MeasureHistory addMeasurehistory(MeasureHistory measurehistory) {
		getMeasurehistories().add(measurehistory);
		measurehistory.setPerson(this);

		return measurehistory;
	}

	public MeasureHistory removeMeasurehistory(MeasureHistory measurehistory) {
		getMeasurehistories().remove(measurehistory);
		measurehistory.setPerson(null);

		return measurehistory;
	}

}