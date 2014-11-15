/**
 * @author Yishagerew L.
 * @DateModified Nov 12, 20142:09:43 AM
 */
package eHealth.rest.resource;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import eHealth.rest.business.HealthProfileResolverImpl;
import eHealth.rest.business.MeasureHistoryImpl;
import eHealth.rest.business.MeasureResolverImpl;
import eHealth.rest.business.PersonImpl;
import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.HealthProfile;
import eHealth.rest.model.MeasureDefinition;
import eHealth.rest.model.MeasureHistory;
import eHealth.rest.model.Person;

public class MeasureService {

	@Context
	UriInfo measureUriInfo;
	@Context
	Request measureRequest;
	int personId;
	String measureName;

	public MeasureService(UriInfo uriInfo2, Request request2, int personId,
			String MeasureName) {
		this.measureRequest = request2;
		this.measureUriInfo = uriInfo2;
		this.personId = personId;
		this.measureName = MeasureName;
	}

	/*******************
	 * ***REQUEST #6****
	 * *****************/

	/**
	 * The following method allows to fetch all measureed values of a measure
	 * type For exmaple:Height,values of a person identified by personId
	 * 
	 * @return
	 */
/**
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_HTML })
	public List<MeasureHistory> getPersonalMeasures() {

		/**
		 * Getting the value of Measure Defnition Id from its name Note:The
		 * resource is accessed as /Height or /Weight not using numbers
		 * 
		EntityManager em = HealthInfoDao.instance.getEntityManager();
		MeasureDefinition mDef = MeasureResolverImpl
				.getMeasureNameId(measureName);
		Query personQuery = em.createNamedQuery("Person.findByPersonId",
				Person.class).setParameter("personId", personId);
		Person person = (Person) personQuery.getSingleResult();
		Query queryMeasure = em.createNamedQuery(
				"MeasureHistory.findByPersonId", MeasureHistory.class);
		queryMeasure.setParameter("measuredefinition", mDef);
		queryMeasure.setParameter("person", person);
		@SuppressWarnings("unchecked")
		List<MeasureHistory> mHistory = queryMeasure.getResultList();
		return mHistory;

	}*/

	/**
	 * Pass the parameter of Measure Id[Mid] so as to get the specific value of
	 * a measure
	 * 
	 * @param MeasureId
	 *            Measure History Identifier
	 * @return
	 */
	@Path("{Mid}")
	public MeasureSpecificService getMeasuresByMID(
			@PathParam("Mid") int MeasureId) {
		return new MeasureSpecificService(MeasureId, personId, measureUriInfo,
				measureRequest);
	}

	/********************
	 * ***REQUEST #8*****
	 ********************/

	/**
	 * The following code is used to add new measure history of a given measure
	 * name from *form*
	 * 
	 * @param measuredValue
	 * @param response
	 */
	/**
	 * @POST
	 * @Produces(MediaType.TEXT_HTML)
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED) public void
	 *                                                  addNewProfileMeasure
	 *                                                  (@FormParam(
	 *                                                  "measuredValue") double
	 *                                                  measuredValue,
	 * @Context HttpServletResponse response) {
	 */
	/**
	 * Adding new measure to measure history
	 * 
	 * Person person=PersonFinderByIdImplUtil.getPersonById(personId);
	 * MeasureDefinition
	 * mDef=MeasureResolverImplUtil.getMeasureNameId(measureName);
	 * MeasureHistory mHistory=new MeasureHistory(measuredValue,person,mDef);
	 * MeasureHistory.addMeasureHistory(mHistory); /** Add new profile if there
	 * is no record with that measure
	 * 
	 * HealthProfile
	 * hprofile=HealthProfileResolverUtil.getHealthProfile(measureName);
	 * if(hprofile==null) {
	 * HealthProfileResolverUtil.addNewProfileInfo(hprofile); } /** Update the
	 * row if there is an existing measure
	 * 
	 * else { hprofile.setMeasuredValue(measuredValue);
	 * HealthProfileResolverUtil.updateHealthProfile(hprofile); }
	 * 
	 * }
	 */

	/**
	 * The following is used to add new measure history given information of a
	 * new measure
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addNewProfileMeasure(HealthProfile hProfile) {
		Person person = PersonImpl.getPersonById(personId);
		MeasureDefinition mDef = MeasureResolverImpl
				.getMeasureNameId(measureName);
		// MeasureHistory.addMeasureHistory(mHistory);
		/**
		 * Add new profile if there is no record with that measure
		 */
		HealthProfile hprofile = HealthProfileResolverImpl
				.getHealthProfile(measureName);
		if (hprofile == null) {
			HealthProfileResolverImpl.addNewProfileInfo(hprofile);
		}
		/**
		 * Update the row if there is an existing measure
		 */
		else {
			/**
			 * Save to the history
			 */
			MeasureHistory mHistory = new MeasureHistory();
			mHistory.setMeasuredefinition(mDef);
			mHistory.setPerson(person);
			mHistory.setValue(hprofile.getMeasuredValue());
			MeasureHistoryImpl.addMeasureHistory(mHistory);
			/**
			 * Remove the object from person Health profile
			 */
			HealthProfileResolverImpl.removeHealthProfile(hprofile);
			/**
			 * Save the new object to health profile information
			 */
			HealthProfileResolverImpl.addNewProfileInfo(hprofile);

		}
	}
	
	                       /******************************
	                        **********REQUEST #6 and #11**mHistory
	                        ******************************/

	/**
	 * Get the list of people in the given range of dates which passed as a
	 * parameter
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<MeasureHistory> getHealthMeasures(
			@QueryParam("before") Date startDate,
			@QueryParam("after") Date endDate)
			{
		if((startDate==null)||(endDate==null))
		{
			EntityManager em = HealthInfoDao.instance.getEntityManager();
			MeasureDefinition mDef = MeasureResolverImpl
					.getMeasureNameId(measureName);
			Query personQuery = em.createNamedQuery("Person.findByPersonId",
					Person.class).setParameter("personId", personId);
			Person person = (Person) personQuery.getSingleResult();
			Query queryMeasure = em.createNamedQuery(
					"MeasureHistory.findByPersonId", MeasureHistory.class);
			queryMeasure.setParameter("measuredefinition", mDef);
			queryMeasure.setParameter("person", person);
			@SuppressWarnings("unchecked")
			List<MeasureHistory> mHistory = queryMeasure.getResultList();	
			return mHistory;
		}
		else
		{
		return MeasureHistoryImpl.getMeasureHistoryByDateRange(startDate,endDate,personId,measureName);
		}
	}

}
