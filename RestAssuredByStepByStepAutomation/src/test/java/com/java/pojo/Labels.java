package com.java.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Labels {
	
	private String label;
	
	@JsonProperty("values")
	private List<Values> values;
	
	private int id;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Values> getValues() {
		return values;
	}

	public void setValues(List<Values> values) {
		this.values = values;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Labels [label=" + label + ", values=" + values + ", id=" + id + "]";
	}
	
}
