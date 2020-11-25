package com.example.ams.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "picture")
	private byte[] picture;
	@Column(name = "author")
	private String author;
	@Column(name = "releaseDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+5:30")
	@DateTimeFormat(pattern = "yyyy-dd-MM")
//	@Temporal(TemporalType.DATE)
	private LocalDate  releaseDate;
	@Column(name = "price")
	private float price;
	
	
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", picture=" + picture + ", author=" + author + ", releaseDate="
				+ releaseDate + ", price=" + price + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(long id, String title,  byte[] picture, String author, LocalDate releaseDate, float price) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.author = author;
		this.releaseDate = releaseDate;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public  byte[] getPicture() {
		return picture;
	}
	public void setPicture( byte[] picture) {
		this.picture = picture;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


}
