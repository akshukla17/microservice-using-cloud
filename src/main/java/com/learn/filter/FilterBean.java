package com.learn.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("BeanFilter")
public class FilterBean {

	private int id;
	private String name;
//	@JsonIgnore  // use to ignore this filter from all FilterBean response (static filtering)
	private String description;
	
	public FilterBean(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
