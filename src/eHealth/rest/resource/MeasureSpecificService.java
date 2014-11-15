/**
 * @author Yishagerew L.
 * @DateModified Nov 12, 20142:09:29 AM
 */
package eHealth.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import eHealth.rest.business.MeasureHistoryImpl;
import eHealth.rest.model.MeasureHistory;


public class MeasureSpecificService {
	
@Context
UriInfo measureSpecificUriInfo;
@Context
Request measureSpecificrequest;
int personId;
int mid;

	public MeasureSpecificService(int measureId, int personId,
			UriInfo measureUriInfo, Request measureRequest) {
		this.measureSpecificUriInfo=measureUriInfo;
		this.personId=personId;
		this.mid=measureId;
		this.measureSpecificrequest=measureRequest;
		

	}
	
									/******************
									 * **REQUEST #7****
									 ******************/
	
/**
 * The following method allows to retrieve the value of a specific measure value
 * Identified by Mid
 * @return
 */
@GET
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_HTML,MediaType.TEXT_XML})
public MeasureHistory getSpecificMeasureValue()
{
		MeasureHistory mHistory=MeasureHistoryImpl.getHistoryByMid(mid, personId);
	    
		return mHistory;
	
}

                            /*****************
                             ****REQUEST #10**
                             *****************/
/**
 * The following updates a measure history row .Since it is implemented with PUT,it will be
 * a full update
 * @param mhistory
 *        A measure history object
 * @return
 */
@PUT
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public Response updateMeasure(MeasureHistory mhistory)
{
	Response res;	
	MeasureHistory mHistory=MeasureHistoryImpl.getHistoryByMid(mid, personId);
	/**
	 * make a change of the new value to meausre history
	 */
	
	/**
	 * If resource is not found,create one,based on REST Architecture spec.
	 */
	if(mHistory==null)
	{
	MeasureHistoryImpl.addMeasureHistory(mhistory);	
	/** For the browser response*/
	res=Response.status(Response.Status.CREATED)
			    .entity(mHistory)
			    .tag("Ok")
			    .header("Location", measureSpecificUriInfo.getAbsolutePath().getPath()).build();
	 
	/** For the console response**/
	
	}
	else
	{
		mHistory.setValue(mhistory.getValue());
		MeasureHistoryImpl.updateMeasureHistory(mHistory);
		res=Response.status(Response.Status.OK)
				    .entity("Resource successfully updated")
				    .header("Location", measureSpecificUriInfo.getAbsolutePath().getPath()).build();
	}
	return res;
	
}

}
