
package bean;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity
@Table(name = "candidates")
//@PrimaryKeyJoinColumn(name="uid")
public class Candidates {
	@Id
	@Column(name = "candiate_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int candiate_id;
	
	@Column(name = "uid")
	private String uid;
	@Column(name = "party_name")
	private String party_name;
	@Column(name = "symbol")
	private Blob symbol;

	@Column(name = "election_ward_id_fk")
	private int election_ward_id_fk;
	
	@Column(name = "no_of_votes")
	private int no_of_votes;

	public Candidates() {

		// TODO Auto-generated constructor stub
	}
	@XmlElement
	public String getParty_name() {
		return party_name;
	}

	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	@XmlElement
	@XmlJavaTypeAdapter(BlobXmlAdapter.class)
	public Blob getSymbol() {
		return symbol;
	}

	public void setSymbol(Blob symbol) {
		this.symbol = symbol;
	}
	
	@XmlElement
	public int getElection_ward_id_fk() {
		return election_ward_id_fk;
	}

	public void setElection_ward_id_fk(int election_ward_id_fk) {
		this.election_ward_id_fk = election_ward_id_fk;
	}
	@XmlElement
	public int getNo_of_votes() {
		return no_of_votes;
	}

	public void setNo_of_votes(int no_of_votes) {
		this.no_of_votes = no_of_votes;
	}
	@XmlElement
	public int getCandiate_id() {
		return candiate_id;
	}
	@XmlElement
	public String getUid() {
		return uid;
	}

}
