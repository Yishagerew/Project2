package eHealth.rest.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.Person;
@Path("/person")
public class PeopleService {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
@GET
@Produces({MediaType.TEXT_XML,MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
public static List<Person> getListOfPerson()
{
	System.out.println("We are called");
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	System.out.println("Entity manager created");
	Query query=em.createNamedQuery("Person.findAll",Person.class);
	System.out.println("Query Building tried");
	@SuppressWarnings("unchecked")
	List<Person> peoples=query.getResultList();
	System.out.println("List tried to retrieved from the query");
	return peoples ;
}
@Path("{personId}")
public PersonService getPersonResource(@PathParam("personId") int personId)
{
	return new PersonService(uriInfo,request,personId);
}
}
