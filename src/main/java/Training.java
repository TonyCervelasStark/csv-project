import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Training {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = null;
	
	int month;
	BigDecimal nbDays;
	Date expectedDate;
	Date realDate;
	String title;
	String place;
	String organism;
	
	
	public Training() {
	}
	
	@Override
	public String toString() {
		return "Training [id=" + id + ", month=" + month + ", nbDays=" + nbDays + ", expectedDate=" + expectedDate
				+ ", realDate=" + realDate + ", title=" + title + ", place=" + place + ", organism=" + organism + "]";
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public BigDecimal getNbDays() {
		return nbDays;
	}


	public void setNbDays(BigDecimal nbDays) {
		this.nbDays = nbDays;
	}


	public Date getExpectedDate() {
		return expectedDate;
	}


	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}


	public Date getRealDate() {
		return realDate;
	}


	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getOrganism() {
		return organism;
	}


	public void setOrganism(String organism) {
		this.organism = organism;
	}
	
	
	
	
}
