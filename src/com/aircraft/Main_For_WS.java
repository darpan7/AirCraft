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
import org.json.JSONObject;
 
@Path("/binary")
public class Main_For_WS {
	
	@Path("/start")
	@GET
	@Produces("application/json")
	public Response reboot() throws JSONException{
		System.out.println("....");
		Helper.reboot();
		
		return Response.status(200).entity(Helper.print("start", "System started!")).build();
	}
 
	@Path("/enqueue/{type}/{size}")
	@GET
	@Produces("application/json")
	public Response addAC(@PathParam("type") String type, @PathParam("size") String size) {
		
		Helper.enqueue(type, size, null);
		return Response.status(200).entity(Helper.print("enqueue", Helper.whoUpdated())).build();
	}
	
	@Path("/enqueue/{type}/{size}/{name}")
	@GET
	@Produces("application/json")
	public Response addAC(@PathParam("type") String type, @PathParam("size") String size, @PathParam("name") String name) {
		Helper.enqueue(type, size, name);
		return Response.status(200).entity(Helper.print("enqueue", Helper.whoUpdated())).build();
	}
	
	@Path("/dequeue")
	@GET
	@Produces("application/json")
	public Response popAC() {
		try {
			return Response.status(200).entity(new JSONObject("<ctofservice>").toString(1)).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(200).entity("{}").build();
		}
	}
}
