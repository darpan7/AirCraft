package com.aircraft.utils;

public class Covnerter {
	
	public static TYPE convertToType(String type) {
		if("Passenger".equalsIgnoreCase(type)) {
			return TYPE.PASSENGER;
		}
		if("Cargo".equalsIgnoreCase(type)) {
			return TYPE.CARGO;
		}
		return null;
	}
	
	public static SIZE convertToSize(String size) {
		if("Large".equalsIgnoreCase(size)) {
			return SIZE.LARGE;
		}
		if("Small".equalsIgnoreCase(size)) {
			return SIZE.SMALL;
		}
		return null;
	}
}
