package com.java.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelList {
	
	@JsonProperty("labels")
	private List<Label> label;

	public List<Label> getLabel() {
		return label;
	}

	public void setLabel(List<Label> label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "LabelList [label=" + label + "]";
	}

}
