/**
 * @author Yishagerew L.
 * @DateModified Nov 12, 20142:10:01 AM
 */
package eHealth.rest.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.MeasureDefinition;
@Path("/measures")
public class MeasureTypeService {
@Context
UriInfo uriInfo;
@Context
Request request;


                                            /*******************
                                             * ****REQUEST #9***
                                             *******************/
/**
 * The following method returns all the available measures  
 * @return
 */
@GET
@Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public List<MeasureDefinition> getAvailableDefinitions()
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("MeasureDefinition.findAllMeasureNames",MeasureDefinition.class);
	@SuppressWarnings("unchecked")
	List<MeasureDefinition> mDefinition=query.getResultList();
	return mDefinition;
}

}
