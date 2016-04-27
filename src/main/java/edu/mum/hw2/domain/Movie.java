package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


/**
 * @author grsky
 *
 */
@Entity
public class Movie {
	@GeneratedValue
	@Id
	private int id;
	private String name;
	private String rating;
	
	@Lob
	private byte[] cover;
	
	@Enumerated(EnumType.STRING)
	private category category;
	
	private String comment;
	
	@ElementCollection
	private List<Actor> actors  = new ArrayList<Actor>();

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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public category getCategory() {
		return category;
	}

	public void setCategory(category category) {
		this.category = category;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] fileData) {
		this.cover = fileData;
	}
	

}
