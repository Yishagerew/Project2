/**
 * @author Yishagerew L.
 * @DateModified Nov 12, 20145:00:54 PM
 */
package eHealth.rest.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.Person;

public class PersonImpl {
	/**
	 * This method is for finding the person identified by its Id,personId 
	 * and returned to the caller
	 * @param personId
	 *        Person id used for search
	 * @return
	 */
public static Person getPersonById(int personId)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("Person.findByPersonId",Person.class).setParameter("personId", personId);
	Person person=(Person) query.getSingleResult();
	return person;
}
public static void addNewPerson(Person person) {
	EntityManager em = HealthInfoDao.instance.getEntityManager();
	EntityTransaction tx = em.getTransaction();
	tx.begin();
	em.persist(person);
	tx.commit();
}
}
