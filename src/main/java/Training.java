import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.sun.prism.impl.Disposer.Record;

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
	
	@OneToOne
	Employee employee;
	
	String Organism;
	
	
	public int getMonth() {
		
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	
}
