package com.example.ams.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "LastName")
	private String LastName;
	@Column(name = "FirstName")
	private String FirstName;
	@Column(name = "email")
	private String email;
	@Column(name = "tel")

	private String tel;
	@Column(name = "adress")
	private float adress;
	@Column(name = "password")
	private float password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public float getAdress() {
		return adress;
	}

	public void setAdress(float adress) {
		this.adress = adress;
	}

	public float getPassword() {
		return password;
	}

	public void setPassword(float password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", LastName=" + LastName + ", FirstName=" + FirstName + ", email=" + email
				+ ", tel=" + tel + ", adress=" + adress + ", password=" + password + "]";
	}

	public Client(long id, String lastName, String firstName, String email, String tel, float adress, float password) {
		super();
		this.id = id;
		LastName = lastName;
		FirstName = firstName;
		this.email = email;
		this.tel = tel;
		this.adress = adress;
		this.password = password;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

}
