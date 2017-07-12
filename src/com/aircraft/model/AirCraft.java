package com.aircraft.model;

import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;

public class AirCraft {
	private TYPE type;
	private SIZE size;
	private String name;
	
	public AirCraft(TYPE type, SIZE size, String name) {
		super();
		this.type = type;
		this.size = size;
		this.name = name;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public SIZE getSize() {
		return size;
	}
	public void setSize(SIZE size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return " AC{" + name + ", " + type.name() + ", " + size.name() + "}";
	}
	
	
}
