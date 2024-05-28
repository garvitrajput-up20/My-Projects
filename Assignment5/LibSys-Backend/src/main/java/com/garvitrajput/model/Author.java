package com.garvitrajput.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorid;
	private String authorname;
	private String authid = Integer.toString(authorid);
	
	
	public String getAuthid() {
		return authid;
	}
	
	public void setAuthid(String authid) {
		this.authid = authid;
	}

	public int getAuthorid() {
		return authorid;
	}
	


	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	@JsonProperty("authorname")
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public Author(int authorid, String authorname, String authid) {
		super();
		this.authorid = authorid;
		this.authorname = authorname;
		this.authid = authid;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
