package com.example.ams.entities;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity

public class commande {
	
	
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "Date Commande")
	private LocalDate DateCommande;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+5:30")

	@Column(name = "prixunit")

	private Double prixunit;
	@Column(name = "quantite Demandee")
	private int quantiteDemandee;
	


	 public commande(long id, LocalDate dateCommande, Double prixunit, int quantiteDemandee, long idBook,
			long idClient) {
		super();
		this.id = id;
		DateCommande = dateCommande;
		this.prixunit = prixunit;
		this.quantiteDemandee = quantiteDemandee;
		this.idBook = idBook;
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return "commande [id=" + id + ", DateCommande=" + DateCommande + ", prixunit=" + prixunit
				+ ", quantiteDemandee=" + quantiteDemandee + ", idBook=" + idBook + ", idClient=" + idClient + "]";
	}
	public commande() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDateCommande() {
		return DateCommande;
	}
	public void setDateCommande(LocalDate dateCommande) {
		DateCommande = dateCommande;
	}
	public Double getPrixunit() {
		return prixunit;
	}
	public void setPrixunit(Double prixunit) {
		this.prixunit = prixunit;
	}
	public int getQuantiteDemandee() {
		return quantiteDemandee;
	}
	public void setQuantiteDemandee(int quantiteDemandee) {
		this.quantiteDemandee = quantiteDemandee;
	}
	public long getIdBook() {
		return idBook;
	}
	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}
	public long getIdClient() {
		return idClient;
	}
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	private long idBook;
	 private long idClient;
	
	
	
	
	

	
}
