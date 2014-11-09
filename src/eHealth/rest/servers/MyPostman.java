package eHealth.rest.servers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class MyPostman {
	public static void main(String[] args) {
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(UriBuilder.fromUri("http://localhost:8089/Lab6Exercises/sdelab/rest/").build());
	    
	    // Get XML
	    System.out.println(service.path("student").request().accept(MediaType.TEXT_HTML).get().readEntity(String.class));
	    // Get XML for application
	    System.out.println(service.path("student").request().accept(MediaType.TEXT_XML).get().readEntity(String.class));
	    // Get JSON for application
	    System.out.println(service.path("student").request().accept(MediaType.APPLICATION_JSON).get().readEntity(String.class));
	  }
}
