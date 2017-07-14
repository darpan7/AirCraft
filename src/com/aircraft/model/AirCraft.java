package com.aircraft.model;

import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;
/**
 * 
 * @author DARPAN
 *
 */
public class AirCraft implements Comparable<AirCraft>{
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
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AC{" + id + ", " + (name != null ? name + ", " : "")
				+ (type != null ? type.name() + ", " : "") + (size != null ? size.name() : "") + "}";
	}
	@Override
	public int compareTo(AirCraft o) {
		return this.id - o.id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirCraft other = (AirCraft) obj;
		if (id != other.id)
			return false;
		if (size != other.size)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

//	public String tString() {
//		return "AC{" + id + ", " + name==null?"":(name + ", ") + type.name() + ", " + size.name() + "}";
//	}
	
	
}
