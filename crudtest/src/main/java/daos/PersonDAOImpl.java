package daos;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Person;


@Repository
public class PersonDAOImpl {

	//private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public PersonDAOImpl() {
		
	}


	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		//logger.info("Person saved successfully, Person Details="+p);
	}

	
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		//logger.info("Person updated successfully, Person Details="+p);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		
		//session.beginTransaction();
		
		List<Person> personsList = session.createQuery("from Person").list();
		/*for(Person p : personsList){
			logger.info("Person List::"+p);
		}*/
		return personsList;
	}

	
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		//logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		//logger.info("Person deleted successfully, person details="+p);
	}

}

