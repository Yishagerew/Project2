package eHealth.rest.servers;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eHealth.rest.model.Person;
import eHealth.rest.utils.JSONFormatter;

public class MyPostman {
	static int first_person_id;
	static int last_person_id;
	public static void main(String[] args) throws ParseException {
		StringBuilder resultStatus=new StringBuilder("OK");
		
									/************************
									 *********** ************
									 ****TESTING REQUESTS****
									 *********** ************
									 ************************/
		/**
		 * Instantiates a new client
		 */
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(UriBuilder.fromUri("http://localhost:8089/").build());
	    /**
	     * creates a resource for call
	     */
	    WebTarget peopleResource=service.path("person");
	    Invocation.Builder invocationBuilder=peopleResource.request(MediaType.APPLICATION_JSON);
	    invocationBuilder.accept(MediaType.APPLICATION_JSON);
	    invocationBuilder.header("Accept", "application/json");
	    invocationBuilder.header("Content-Type", "application/json");
	    Response response=invocationBuilder.get();
	    String personListString=response.readEntity(String.class);
	    /**
    	 * Parse string to object array
    	 */
	    JSONParser parser=new JSONParser();
		Object obj=parser.parse(personListString);
         JSONArray jsonArray = (JSONArray)obj;	
	         /**
	          * Calculate total size of the response
	          */
	         int personListSize=jsonArray.size();
	         /**
	          * Check result validation
	          */
	         if(personListSize<3)
	         {
	        	 resultStatus.append("ERROR");
	         }
	         /**
	          * Getting the first person
	          */
	         JSONObject firstPerson=new JSONObject();
	         firstPerson=(JSONObject)jsonArray.get(0);
	         first_person_id=Integer.parseInt(firstPerson.get("personId").toString());
	         /**
	          * Getting the last person
	          */
	         JSONObject lastPerson=new JSONObject();
	         lastPerson=(JSONObject)jsonArray.get(personListSize-1);
	         last_person_id=Integer.parseInt(lastPerson.get("personId").toString());
	         /**
	          * Formatted output
	          */
	        System.out.println("REQUEST #1 "+"GET"+" "+" "+"Accept:"+"APPLICATION/XML"+ " "+"Content-type:" +"APPLICATION/XML");
	 	    System.out.println("=>Result:"+resultStatus);
	 	    System.out.println("=>Http Status:"+response.getStatus());
		    System.out.println(JSONFormatter.toJsonFormat(personListString));
		    
		    
											    /*************************
											     ********REQUEST #2*******
											     *************************/
		    /**
		     * creating taget of kind /person/{id}
		     */
		    WebTarget personResource=peopleResource.path(String.valueOf(first_person_id));
		    Invocation.Builder request2Builder=personResource.request(MediaType.APPLICATION_JSON);
		    request2Builder.accept(MediaType.APPLICATION_JSON);
		    request2Builder.header("Accept","application/json");
		    request2Builder.header("Content-Type","application/json");
		    Response req2JsonResponse=request2Builder.get();
		    /**
		     * Formatted output
		     */
		    String req2ResponseSrarus=(req2JsonResponse.getStatus()==200)?"OK":"ERROR";
		    System.out.println("REQUEST #2 "+"GET"+" "+" "+"Accept:"+"APPLICATION/JSON"+ " "+"Content-type:" +"APPLICATION/JSON");
	 	    System.out.println("=>Result:" + req2ResponseSrarus);
	 	    System.out.println("=>Http Status:" + response.getStatus());
		    System.out.println(JSONFormatter.toJsonFormat(JSONFormatter.toJsonFormat(req2JsonResponse.readEntity(String.class).toString())));
		    
														    /***********************
														     ********REQUEST #3*****
														     ***********************/
		     Person person=new Person();
		     
		    
ClientResponse req3Response=personResource.request().accept(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class,x);    
		    
		    
	   
	    
	    
	    
	    
	    
	    
	    
	    
									    /*****************************
									     *****REQUEST #1 XML Request**
		     						     *****************************/
	  // Response res =service.path("person").request().accept(MediaType.APPLICATION_XML).get();
	   //System.out.println(res.getHeaders());
	    
	   /**try {
			System.out.println(XMLFormatter.getFormattedXML(resultXML));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} */
	    
										    /*****************************
										     *****REQUEST #1 JSON Request**
											 *****************************/
	    /**
	    String resultJson=service.path("person").request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
	   System.out.println(JSONFormatter.toJsonFormat(resultJson));
	   */
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /**
	    // Get XML
	    System.out.println(service.path("student").request().accept(MediaType.TEXT_HTML).get().readEntity(String.class));
	    // Get XML for application
	    System.out.println(service.path("student").request().accept(MediaType.TEXT_XML).get().readEntity(String.class));
	    // Get JSON for application
	    System.out.println(service.path("student").request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class));
	    */
	  }
}
