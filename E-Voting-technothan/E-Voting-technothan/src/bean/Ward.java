package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ward")
public class Ward {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ward_id;
	@Column(name="ward_name")
	private String ward_name;
	@Column(name="ward_no")
	private int ward_no;
	public String getWard_name() {
		return ward_name;
	}
	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}
	public int getWard_no() {
		return ward_no;
	}
	public void setWard_no(int ward_no) {
		this.ward_no = ward_no;
	}
	public int getWard_id() {
		return ward_id;
	}
	
	
	
	
}
