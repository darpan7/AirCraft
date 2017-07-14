package com.aircraft.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
	private boolean typef;
	private boolean sizef;
	
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
		service.reboot();
		
		List<AirCraft> aa = dummyACs(10);
		for(AirCraft a: aa) {
			service.enqueue(a);
		}

		assert(10 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
		
		service.reboot();
		List<AirCraft> aba = dummyACs(20);
		for(AirCraft a: aba) {
			service.enqueue(a);
		}
		
		assert(20 == service.getQ().size());
		assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		assert(SIZE.LARGE == service.getQ().get(0).getSize());
	}
	
	@Test
	public void testDequeue(){
		
		List<AirCraft> aa = dummyACs(10);
		for(AirCraft a: aa) {
			service.enqueue(a);
		}
		assert(10 == service.getQ().size());
		AirCraft ad = service.dequeue();
		assert(TYPE.PASSENGER == ad.getType());
		assert(SIZE.LARGE == ad.getSize());
		//assert(TYPE.PASSENGER == service.getQ().get(0).getType());
		//assert(SIZE.LARGE == service.getQ().get(0).getSize());
	}
	
	public List<AirCraft> dummyACs(int limit){
		List<AirCraft> acs = new ArrayList<AirCraft>(limit);
		int c = 0;
		for(int i=0; i<limit; i++) {
			acs.add(new AirCraft(randomType(), randomSize(), "", ++c));
		}
		return acs;
	}
	public TYPE randomType() {
		if(typef) {
			typef = false;
			return TYPE.PASSENGER;
		}else {
			typef = true;
			return TYPE.CARGO;
		}
	}
	
	public SIZE randomSize() {
		if(sizef) {
			sizef = false;
			return SIZE.LARGE;
		}else {
			sizef = true;
			return SIZE.SMALL;
		}
	}
}
