package bean;

import java.sql.Blob;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlRootElement
@Entity  
@Table(name = "citizens")  
//@Inheritance(strategy=InheritanceType.JOINED)
public class Person{
	@Id
	@Column(name = "UID") 
	private String uid;
	
	@Column(name = "first_name") 	
	private String firstName;
	
	@Column(name = "middle_name") 
	private String middleName;
	
	@Column(name = "last_name") 
	private String lastName;
	
	@Column(name = "contact_no") 
	private String contactNo;

	@Column(name = "date_of_birth")	
	private Date dob;

	@Column(name = "gender") 
	private char gender;

	@Column(name = "email_id") 
	private String emailId;	

	@Column(name = "address_ID") 
	private int address_ID;	

	@Column(name="photo")
    private Blob photo;
	
	@XmlElement
	@XmlJavaTypeAdapter(BlobXmlAdapter.class)
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	@XmlElement
	public String getUid() {
		return uid;
	}
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	@XmlElement
	@XmlJavaTypeAdapter(DateAdapter.class)
   public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@XmlElement
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	@XmlElement
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@XmlElement
	public int getAddress_ID() {
		return address_ID;
	}
	public void setAddress_ID(int address_ID) {
		this.address_ID = address_ID;
	}
	
	
	
	
}
