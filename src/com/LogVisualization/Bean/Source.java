package com.LogVisualization.Bean;

public class Source {
	private int id;
	private String name;
	private String url;
	private String state;
	private String username;
	
	public Source(int id, String name, String url, String state, String username) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.state = state;
		this.username = username;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
