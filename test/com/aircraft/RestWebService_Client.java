package com.aircraft;

import com.aircraft.service.ACServiceTest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author DARPAN
 * Server needs to be running in order to get rest response.
 */
public class RestWebService_Client {
	public static void main(String[] args) {
		
		RestWebService_Client client = new RestWebService_Client();
		client.getDequeueResponse();
		//client.getEnqueueResponse(10);
		
		client.getStartResponse();
		client.getEnqueueResponse(10);
		client.getDequeueResponse();
	}
 
	private void getStartResponse() {
		try {
 
			Client client = Client.create();
			WebResource webResource2 = client.resource("http://localhost:8087/AC/rest/binary/start");
			ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
			}
 
			String output2 = response2.getEntity(String.class);
			System.out.println("\n============Response============");
			System.out.println(output2);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void getEnqueueResponse(int count) {
		try {
 
			Client client = Client.create();
			for(int i=0; i<count; i++) {
				WebResource webResource2 = client.resource("http://localhost:8087/AC/rest/binary/enqueue/" + ACServiceTest.randomType() + "/" + ACServiceTest.randomSize());
				ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
				if (response2.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
				}
				
				String output2 = response2.getEntity(String.class);
				System.out.println("\n============EnqueueResponse============");
				System.out.println(output2);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDequeueResponse() {
		try {
 
			Client client = Client.create();
			WebResource webResource2 = client.resource("http://localhost:8087/AC/rest/binary/dequeue");
			ClientResponse response2 = webResource2.accept("application/json").get(ClientResponse.class);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
			}
 
			String output2 = response2.getEntity(String.class);
			System.out.println("\n============DequeueResponse============");
			System.out.println(output2);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	
}
