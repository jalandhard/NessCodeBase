package com.java.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelsList {
	
	@JsonProperty("labels")
	private List<Labels> labels;

	public List<Labels> getLabels() {
		return labels;
	}

	public void setLabels(List<Labels> labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "LabelsList [labels=" + labels + "]";
	}

}
