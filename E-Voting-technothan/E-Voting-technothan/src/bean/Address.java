package bean;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name="address_id")
	private int address_id;
	@Column(name="location")
	private String location;
	@Column(name="landmark")
	private String landmark;
	@Column(name="vtc")
	private String vtc;
	@Column(name="po")
	private String po;
	@Column(name="district")
	private String district;
	@Column(name="sub_district")
	private String sub_district;
	@Column(name="pin_code")
	private String pin_code;
	@Column(name="ward_id_fk")
	private int ward_id;
	@Column(name="zone_id_fk")
	private int zone_id;
	public int getAddress_id() {
		return address_id;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getVtc() {
		return vtc;
	}
	public void setVtc(String vtc) {
		this.vtc = vtc;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSub_district() {
		return sub_district;
	}
	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}
	public String getPin_code() {
		return pin_code;
	}
	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public int getWard_id() {
		return ward_id;
	}

	public void setWard_id(int ward_id) {
		this.ward_id = ward_id;
	}

	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}
	
	
	
	
	
}
