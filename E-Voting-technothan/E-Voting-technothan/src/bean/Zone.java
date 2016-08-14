package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zone")
public class Zone {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int zone_id;
	@Column(name="zone_name")
	private String zone_name;
	@Column(name="zone_no")
	private int zone_no;
	public String getZone_name() {
		return zone_name;
	}
	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}
	public int getZone_no() {
		return zone_no;
	}
	public void setZone_no(int zone_no) {
		this.zone_no = zone_no;
	}
	public int getZone_id() {
		return zone_id;
	}
	
	
}
