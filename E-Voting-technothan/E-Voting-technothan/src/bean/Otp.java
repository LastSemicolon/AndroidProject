package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp 
{
	@Id
	@Column(name = "uid") 
   String uid;
	@Column(name = "phone") 
   String phone;
	@Column(name = "gen_otp") 
   int gen_otp;
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public void setGen_otp(int gen_otp) {
	this.gen_otp = gen_otp;
}
public int getGen_otp() {
	return gen_otp;
}
   
}
