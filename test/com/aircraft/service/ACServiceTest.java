package com.aircraft.service;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aircraft.model.AirCraft;
import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;


public class ACServiceTest {
	private ACService service;
	private AirCraft a;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		service = new ACService();
		a = new AirCraft(TYPE.CARGO, SIZE.SMALL, null, 1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEnqueue(){
		service.enqueue(a);
		assertNotNull(service.getQ());
		assert(1 == service.getQ().size());
		assert(a == service.getQ().get(0));
		
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "top", 2));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "--", 3));
		assert(3 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
		
		service.reboot();
		int c = 1;
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.LARGE, "top", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "--", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "--", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "--", ++c));
		
		assert(7 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
	}
	
	@Test
	public void testDequeue(){
		service.enqueue(a);
		assertNotNull(service.getQ());
		assert(1 == service.getQ().size());
		assert(a == service.getQ().get(0));
		
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "top", 2));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "--", 3));
		assert(3 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
		
		service.reboot();
		int c = 1;
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.LARGE, "top", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "top", ++c));
		service.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "--", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "--", ++c));
		service.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "--", ++c));
		
		assert(7 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
	}
	
}
