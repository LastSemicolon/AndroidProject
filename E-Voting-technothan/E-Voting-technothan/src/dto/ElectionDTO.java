package dto;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import bean.Person;

public class ElectionDTO {
	
	public Session createVotersSession(){
		//Configuration configuration = new Configuration().configure();
		Configuration configuration= new Configuration().configure("hibernateMaster.cfg.xml");
		
		PropertyConfigurator.configure("log4j.properties");

		
		Session ses = configuration.buildSessionFactory().openSession();
		return ses;
	}
	

	

}
