import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TrainingDemand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = null;

	/*
	@OneToMany
	Training training;
	*/
}
