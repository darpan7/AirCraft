package com.aircraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.json.JSONObject;

import com.aircraft.model.AirCraft;
import com.aircraft.model.OrderedJSON;
import com.aircraft.utils.Covnerter;
import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;
import com.sun.jersey.spi.resource.Singleton;
/**
 * 
 * @author DARPAN
 *
 */
@Singleton
public class Helper {
	private static Queue<AirCraft> largePassenger = new LinkedList<AirCraft>(); 
	private static Queue<AirCraft> smallPassenger = new LinkedList<AirCraft>();
	private static Queue<AirCraft> largeCargo = new LinkedList<AirCraft>();
	private static Queue<AirCraft> smallCargo = new LinkedList<AirCraft>();
	private static String updated = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		String act = "Action:";
		json.put(act, "start");
		json.put("Result:", "System started!");
		json.put("Large Passenger AC:", "");
		json.put("Small Passenger AC:", "");
		json.put("Large Cargo AC:", "");
		json.put("Small Cargo AC:", "");
		//System.out.println("JSON:\n" + json.toString(1));
		
		AirCraft ac = new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "xxx", 1);
		System.out.println(ac);
	}
	
	public static void reboot() {
		largePassenger.clear();
		smallPassenger.clear();
		largeCargo.clear();
		smallCargo.clear();
		updated = null;
	}
	
	public static String print(String act, Object res) {
		OrderedJSON json = new OrderedJSON();
		json.put("Action", act);
		json.put("Who Updated", (res==null)?null:res.toString());
		json.put("Queue of Large Passenger", largePassenger==null?null:largePassenger.toString());
		json.put("Queue of Small Passenger", smallPassenger==null?null:smallPassenger.toString());
		json.put("Queue of Large Cargo", largeCargo==null?null:largeCargo.toString());
		json.put("Queue of Small Cargo", smallCargo==null?null:smallCargo.toString());
		
		return json.toString(1);
	}
	
	public static void enqueue(String type,String size, String name, int id) {
		TYPE en_type = Covnerter.convertToType(type);
		SIZE en_size = Covnerter.convertToSize(size);
		
		switch(en_type) {
		case PASSENGER:
			switch(en_size) {
			case LARGE:
				largePassenger.add(new AirCraft(en_type, en_size, name, id));
				updated = "Large Passenger Queue";
				break;
			case SMALL:
				smallPassenger.add(new AirCraft(en_type, en_size, name, id));
				updated = "Small Passenger Queue";
				break;
			}
		break;
		case CARGO:
			switch(en_size) {
			case LARGE:
				largeCargo.add(new AirCraft(en_type, en_size, name, id));
				updated = "Large Cargo Queue"; 
				break;
			case SMALL:
				smallCargo.add(new AirCraft(en_type, en_size, name, id));
				updated = "Small Cargo Queue"; 
				break;
			}
		break;
			
		}
	}
	
	public static String whoUpdated(){
		return updated;
	}
	public static String all() {
		List<AirCraft> all = new ArrayList<AirCraft>();
		all.addAll(largePassenger);
		all.addAll(smallPassenger);
		all.addAll(largeCargo);
		all.addAll(smallCargo);
		
		Collections.sort(all);
		return "";
	}
	public static void dequeue() {
		if(!largePassenger.isEmpty()) {
			AirCraft removed = largePassenger.poll();
			updated = "Large Passenger Queue::Removed " + removed.toString();
		}else if(!smallPassenger.isEmpty()) {
			AirCraft removed = smallPassenger.poll();
			updated = "Small Passenger Queue::Removed " + removed.toString();
		}else if(!largeCargo.isEmpty()) {
			AirCraft removed = largeCargo.poll();
			updated = "Large Cargo Queue::Removed " + removed.toString();
		}else if(!smallCargo.isEmpty()) {
			AirCraft removed = smallCargo.poll();
			updated = "Small Cargo Queue::Removed " + removed.toString();
		}else {
			updated = "Queue is empty. ";
		}
	}

}
