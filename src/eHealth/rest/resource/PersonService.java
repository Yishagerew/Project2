package eHealth.rest.resource;

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

public class PersonService {

	@Context
	Request request;
	@Context
	UriInfo uriInfo;
	int personId;

	public PersonService(UriInfo uriInfo, Request request, int personId) {
		this.request = request;
		this.uriInfo = uriInfo;
		this.personId = personId;

	}
@GET
@Produces({MediaType.TEXT_HTML,MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
public Person getPersonDetails()
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Person person=new Person();
	Query personQuery=em.createNamedQuery("Person.findByPersonId",Person.class);
	personQuery.setParameter("personId", personId);
	person=(Person) personQuery.getSingleResult();
	return person;
}
@Path("{MeasureId}")
public MeasureService getMeasureDetails(@PathParam("MeasureId") String MeasureName)
{
	return new MeasureService(uriInfo,request,personId,MeasureName);
}
}
