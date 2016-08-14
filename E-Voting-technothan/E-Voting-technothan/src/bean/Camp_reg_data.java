package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity  
@Table(name = "camp_reg_data") 
public class Camp_reg_data 
{
	@Id
	@Column(name="username")
   String username;
	@Column(name="password")
   String password;
   @Column(name="uid")
   String uid;
	
   

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Camp [user_name=" + username + ", password=" + password
				+ ", uid=" + uid + "]";
	}
	
}
