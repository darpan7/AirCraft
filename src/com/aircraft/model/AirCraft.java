package com.aircraft.model;

import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;
/**
 * 
 * @author DARPAN
 *
 */
public class AirCraft {
	private int id;
	private String name;
	private TYPE type;
	private SIZE size;
	
	public AirCraft(TYPE type, SIZE size, String name, int id) {
		super();
		this.type = type;
		this.size = size;
		this.name = name;
		this.id = id;
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
		return "AC{" + id + ", " + (name != null ? name + ", " : "")
				+ (type != null ? type.name() + ", " : "") + (size != null ? size.name() : "") + "}";
	}

//	public String tString() {
//		return "AC{" + id + ", " + name==null?"":(name + ", ") + type.name() + ", " + size.name() + "}";
//	}
	
	
}
