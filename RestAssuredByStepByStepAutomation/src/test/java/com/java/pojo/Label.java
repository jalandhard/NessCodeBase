package com.java.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
	
	@JsonProperty("label")
	private String label;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("id")
	private int id;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Label [label=" + label + ", value=" + value + ", id=" + id + "]";
	}

}
