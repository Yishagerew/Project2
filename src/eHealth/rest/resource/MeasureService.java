package eHealth.rest.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import utils.MeasureResolverUtil;
import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.MeasureHistory;

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

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_HTML })
	public List<MeasureHistory> getPersonalMeasures() {
		MeasureResolverUtil resolver = new MeasureResolverUtil();
		int measureId = resolver.getMeasureNameId(measureName);
		EntityManager em = HealthInfoDao.instance.getEntityManager();
		Query query = em.createNamedQuery("MeasureHistory.findByPersonId",
				MeasureHistory.class);
		query.setParameter("personId", personId);
		query.setParameter("measureDefId", measureId);
		@SuppressWarnings("unchecked")
		List<MeasureHistory> mHistory = query.getResultList();
		return mHistory;

	}
}
