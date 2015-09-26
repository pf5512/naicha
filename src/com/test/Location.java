package com.test;

import java.util.Arrays;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="location")
public class Location {
	@Id
	private String id;
	private double[] position;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Location(String id, double[] position) {
		super();
		this.id = id;
		this.position = position;
	}
	public Location(String id, double d, double e) {
		this.id = id;
		this.position[0]=d;
		this.position[1]=d;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(position);
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
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(position, other.position))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", position=" + Arrays.toString(position)
				+ "]";
	}

}
