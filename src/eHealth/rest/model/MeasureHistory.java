package eHealth.rest.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the MEASUREHISTORY database table.
 * 
 */
@Entity
@Table(name="MeasureHistory")
@NamedQueries({
			@NamedQuery(name="MeasureHistory.findAll", query="SELECT m FROM MeasureHistory m"),
			@NamedQuery(name="MeasureHistory.findByPersonId",query="SELECT m FROM MeasureHistory m WHERE m.person=:person and m.measuredefinition=:measuredefinition")
})

@XmlRootElement(name="Measures")
public class MeasureHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableGenerator(name="sqlite_measureHistory", table="sqlite_sequence",
			pkColumnName="name", valueColumnName="seq",
			pkColumnValue="Measure_History_Gen")
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="sqlite_measureHistory")
	@Column(name="\"historyMeasureId\"")
	private int historyMeasureId;
	@Column(name="\"dateCreated\"")
	private String dateCreated;
	/**@Column(name="\"measureDefId\"")
	private int measureDefId; */

	@Column(name="\"updateDate\"")
	private String updateDate;

	@Column(name="\"value\"")
	private double value;

	//bi-directional one-to-one association to MeasureDefinition
	@OneToOne
	@JoinColumn(name="measureDefId",referencedColumnName="measureDefId",insertable=true,updatable=true)
	private MeasureDefinition measuredefinition;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="personId",referencedColumnName="personId")
	private Person person;

	public MeasureHistory() {
	}

	public String getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getHistoryMeasureId() {
		return this.historyMeasureId;
	}

	public void setHistoryMeasureId(int historyMeasureId) {
		this.historyMeasureId = historyMeasureId;
	}
/**
	public int getMeasureDefId() {
		return this.measureDefId;
	}

	public void setMeasureDefId(int measureDefId) {
		this.measureDefId = measureDefId;
	}
*/
	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public MeasureDefinition getMeasuredefinition() {
		return this.measuredefinition;
	}

	public void setMeasuredefinition(MeasureDefinition measuredefinition) {
		this.measuredefinition = measuredefinition;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}