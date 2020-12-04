package com.vermeg.ams.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.vermeg.ams.entities.Role;

//user is the new client
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "firstName")
	// @NotBlank (message="required")
	private String firstName;

	@Column(name = "lastName")
	// @NotBlank (message="required")
	private String lastName;

	@Column(name = "adress")
	// @NotBlank (message="required")
	private String adress;

	@Column(name = "email")

	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	// @NotBlank (message="required")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	@Column(name = "active")
	private int active;
	@Column(name = "phoneNumber")
//@NotBlank (message="required")
	private int phoneNumber;
	@Column(name = "IsAdmin")
//@NotBlank (message="required")
	private boolean isAdmin;

/*
 * @OneToMany(mappedBy = "user_u", fetch = FetchType.LAZY, cascade = {
 * CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
 * CascadeType.REFRESH }) private List<Order> orders;
 * 
 @ManyToMany(cascade = CascadeType.ALL)
 
 @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"),
 inverseJoinColumns = @JoinColumn(name = "id")) private Set<Role> roles;*/
	
	@ManyToMany
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roleList;
 
	public int getIdUser() {
		return id;
	}

	public void setIdUser(int idUser) {
		this.id = idUser;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int idUser, String firstName, String lastName, String adress,
			@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password,
			int active, int phoneNumber, boolean isAdmin, List<Order> orders, Set<Role> roles) {
		super();
		this.id = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.password = password;
		this.active = active;
		this.phoneNumber = phoneNumber;
		this.isAdmin = isAdmin;
		
	}

}
