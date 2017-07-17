package com.aircraft;
/**
 * 
 * @author DARPAN
 *
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.aircraft.model.AirCraft;
import com.aircraft.service.ACService;
import com.aircraft.utils.Covnerter;
import com.sun.jersey.spi.resource.Singleton;
 
@Path("/binary")
@Singleton
public class AqmRequestProcess_For_WebService {
	
	private int counter = 0;
	private ACService ac =new ACService();
	@Path("/start")
	@GET
	@Produces("application/json")
	public Response reboot() throws JSONException{
		ac.reboot();
		counter = 0;
		return Response.status(200).entity(ac.print("start", "System started!")).build();
	}
 
	@Path("/enqueue/{type}/{size}")
	@GET
	@Produces("application/json")
	public Response addAC(@PathParam("type") String type, @PathParam("size") String size) {
		ac.enqueue(new AirCraft(Covnerter.convertToType(type), Covnerter.convertToSize(size), null, ++counter));
		return Response.status(200).entity(ac.print("enqueue", null)).build();
	}
	
	@Path("/enqueue/{type}/{size}/{name}")
	@GET
	@Produces("application/json")
	public Response addAC(@PathParam("type") String type, @PathParam("size") String size, @PathParam("name") String name) {
		ac.enqueue(new AirCraft(Covnerter.convertToType(type), Covnerter.convertToSize(size), name, ++counter));
		return Response.status(200).entity(ac.print("enqueue", null)).build();
	}
	
	@Path("/dequeue")
	@GET
	@Produces("application/json")
	public Response popAC() {
		return Response.status(200).entity(ac.print("dequeue", ac.dequeue())).build();
	}
}
