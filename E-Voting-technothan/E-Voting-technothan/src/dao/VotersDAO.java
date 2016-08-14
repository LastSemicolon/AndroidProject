package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.Camp_reg_data;
import dto.ElectionDTO;
import bean.Address;
import bean.Candidates;
import bean.Person;
import bean.Voted_Citizen;
import bean.Zone;

public class VotersDAO {
	
	private Configuration configuration1,configuration2;
	private Session ses;
	private Query query;
	private Person p_obj;
	
	public VotersDAO(String filename) {
		 configuration1 = new Configuration().configure(filename);
		 ses = configuration1.buildSessionFactory().openSession();
			PropertyConfigurator.configure("log4j.properties");
	}
	
	public VotersDAO(String filename1,String filename2) {
		configuration1 = new Configuration().configure(filename1);
		configuration2 = new Configuration().configure(filename2);
		ses = configuration1.buildSessionFactory().openSession();
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public VotersDAO() {
		// TODO Auto-generated constructor stub
	}

	//check phone number is exist in the database or not!
	public String validateContact(String uid) {
		//Configuration configuration = new Configuration().configure("govt_hibernate.cfg.xml");
		//PropertyConfigurator.configure("log4j.properties");
		
		if(checkDuplicateUser(uid)==false)
		{
		
		  p_obj = (Person)ses.get(Person.class, uid);
		 //query = ses.createQuery("from Person where uid=:id");
		//query.setParameter("id", uid);
		//Person p_obj = (Person) query.list().get(0);
		
		  ses.close();
		if(p_obj!=null)
		 {
		     if (p_obj.getContactNo() == null)
		     {
			   return "not_found";

		    } else 
		    {
			  return p_obj.getContactNo();
		    }
		}
		else
			return "invalid_voter";
	}
		else
			return "duplicate_voter";
	
	}
public boolean checkDuplicateUser(String uid) {
		
//		/System.out.println("Inside checkduplicate user")
	Configuration configuration3 = new Configuration().configure("election_hibernate.cfg.xml");
		Session ses2 = configuration3.buildSessionFactory().openSession();
			//PropertyConfigurator.configure("log4j.properties");
	 //  Transaction tx=ses2.beginTransaction();
		Voted_Citizen vc=(Voted_Citizen) ses2.get(Voted_Citizen.class, uid);
		//tx.commit();
		ses2.close();
		if(vc==null)
		return false;
		else
        return true;
	}

	/*public List<Candidates> getCandidates(String uid) {

		Configuration configuration = new Configuration()
				.configure("govt_hibernate.cfg.xml");

		Session ses = configuration.buildSessionFactory().openSession();

		String query1;
		String query2;
		query1 = "select p.address_ID from Person p where uid=:uidTemp";

		Query query = ses.createQuery(query1);
		query.setParameter("uidTemp", uid);
		// List<Integer> persons=query.list();
		int addId = (Integer) query.list().get(0);
		query2 = "select a.ward_id,a.zone_id from Address a where address_id=:tempAddID";
		Query quer = ses.createQuery(query2);
		quer.setParameter("tempAddID", addId);
		List<Object> address = (List<Object>) quer.list().get(0);
		//int ward , zone;
		//for (Object[] add : address) {
			int zone = (Integer)address.get(1);
			int ward = (Integer)address.get(2);
		//}

		ses.close();

		// SECOND SESSION

		Configuration configuration2 = new Configuration()
				.configure("election_hibernate.cfg.xml");
		PropertyConfigurator.configure("log4j.properties");

		ses = configuration2.buildSessionFactory().openSession();

		String query3;
		query3 = " from Candidates where election_ward_id_fk=:tempwardId";
		Query quer2 = ses.createQuery(query3);
		quer2.setParameter("tempwardId", ward);
		List<Candidates> candidateList = quer2.list();

		
		

		
		ses.close();

		return candidateList;

	}
	*/
	
	//this method return the list of candidate based on ward or zone(Based on election type)
	public List<Candidates> getCandidates(String uid) {

		//Configuration configuration = new Configuration().configure("govt_hibernate.cfg.xml");

		//ses = configuration1.buildSessionFactory().openSession();
		
		
	
		
		List<Candidates> candidateList = new ArrayList<Candidates>();
		p_obj = (Person)ses.get(Person.class, uid);
		
		
		Address address_obj = (Address)ses.get(Address.class, p_obj.getAddress_ID());
		//query = ses.createQuery("select p.address_ID from Person p where uid='"+p_obj.ge+"'");
		//query = ses.createQuery("select a.zone_id from Address a where address_id=:tempAddID");//.setParameterList("tempAddID", query.list());
		//query.setParameter("tempAddID", p_obj.getAddress_ID());
		//ses.close();

		
		ses.close();
		Session ses2 = configuration2.buildSessionFactory().openSession();
		
		Voted_Citizen obj = (Voted_Citizen)ses2.get(Voted_Citizen.class, uid);
		
		if(obj==null){
			query = ses2.createQuery("from Candidates where election_ward_id_fk="+address_obj.getZone_id());//.setParameterList("tempwardId", query.list());

			candidateList = query.list();

			ses2.close();
			
			return candidateList;
		}else{
			return candidateList;
		}
			
			
	

	}

	
	
	//this method is use for updating the votes of candiadte
	public int updateVotes(int candidateId,String uid) {
		
		Transaction tx = ses.beginTransaction();

		Candidates candidate = (Candidates) ses.get(Candidates.class,candidateId);
		candidate.setNo_of_votes((candidate.getNo_of_votes() + 1));
		//setVotedCitizen(uid);;
	    Voted_Citizen vc=new Voted_Citizen();
	    vc.setUid(uid);
	    ses.save(vc);
		tx.commit();
		ses.close();
		
		//setVotedCitizen("342422676452");
		return 1;
	}

	
	
	
	public boolean validateLogin(String user,String pass)
	{
		
		
	   Camp_reg_data obj = (Camp_reg_data)ses.get(Camp_reg_data.class, user);
      
      // query=ses.createQuery("from Camp_reg_data where username=:tempUser");
      // query.setParameter("tempUser", user);
     //  List<Camp_reg_data> list=query.list();
      // String password="df";
    	// if(!(list.isEmpty()))
    	// {
    		// password=list.get(0).getPassword();
             
        //  }

	 if(pass.equals(obj.getPassword()))
		return true;
	 else
		return false;
	
    }

	public Candidates fetchResult(String zone_name)
	{
		
       Query query=ses.createQuery("from Zone where zone_name=:temp");
       query.setParameter("temp", zone_name);
        List<Zone> zones=( List<Zone>)query.list();
         int zoneId=0;
      if(!(zones.isEmpty()))
         zoneId=zones.get(0).getZone_id();
	    ses.close(); 
	  if(zoneId!=0)
	  {	
		  ses= configuration2.buildSessionFactory().openSession();
			PropertyConfigurator.configure("log4j.properties");
		  Query query1=ses.createQuery("from Candidates where no_of_votes=(select max(no_of_votes) from Candidates where election_zone_id_fk=:tempZone) ");
	       query1.setParameter("tempZone", zoneId);
         List<Candidates> candidateList=query1.list();
	    
	  // System.out.println("The database has been updated: ");
       return candidateList.get(0);
	  }
	  else
		  return null;
	}
	
	
	public void setVotedCitizen(String uid)
	{
	    Transaction tx1=ses.beginTransaction();
	    Voted_Citizen vc=new Voted_Citizen();
	    vc.setUid(uid);
	    ses.save(vc);
        tx1.commit();
	 
	   System.out.println("Updated the database of voted citizen");

    }
	
	
}