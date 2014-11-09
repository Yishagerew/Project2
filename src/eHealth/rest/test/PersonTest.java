package eHealth.rest.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.*;

import eHealth.rest.model.Person;

public class PersonTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private EntityTransaction tx;
@Test
public void getListOfPersons()
{
	List<Person>peoples=em.createNamedQuery("Person.findAll",Person.class).getResultList();
	for(Person people:peoples)
	{
		System.out.println("First Name:"+people.getCreatedDate());
	}
}
@Ignore
@Test
public void addNewPerson()
{
	//List<Person>peoples=em.createNamedQuery("Person.findAll",Person.class).getResultList();
	Person p= new Person();
	p.setPersonId(1222);
	p.setUserName("lulie");
	p.setPassword("lulie");
	p.setFirstName("lulie");
	p.setCreatedDate("12/12/1990 2:34:45 AM");
	tx.begin();
	em.persist(p);
	tx.commit();
	assertNotNull("Id cannot be null",p.getPersonId());
	int NewPersonId=p.getPersonId();
System.out.println("=============Querying for the new person===============");
em.getTransaction().begin();
Person newPerson=em.createNamedQuery("Person.findByPersonId",Person.class).setParameter("personId", NewPersonId).getSingleResult();
assertEquals("New added person returned by the query",1222,newPerson.getPersonId());
System.out.println("===============Deleting the added row=================");
System.out.println("Person details");
System.out.println("First Name"+newPerson.getFirstName());
System.out.println("Created Date"+newPerson.getCreatedDate());
em.remove(newPerson);
em.getTransaction().commit();

}
@BeforeClass
public static void beforeClass() {
System.out.println("Testing JPA on lifecoach database using 'introsde-jpa' persistence unit");
emf = Persistence.createEntityManagerFactory("healthbook-jpa");
System.out.println("Created EMF is "+emf);
em = emf.createEntityManager();
System.out.println("Entity manager created++++++++++++++++++++++++++++++++++"+ em);
}
@AfterClass
public static void afterClass() {
em.close();
emf.close();
}
@Before
public void before() {
	tx = em.getTransaction();
}


}
