package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "resrvations")
public class Reservation implements Serializable{

	@Id
	@Column (name="ID_RESERVATION")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idReservation;
	
	@Column (name="NOM_CLIENT")
	private String nomClient;
	
	private double prix;

	public Reservation() {
		super();
	}
	public Reservation(String nomClient, double prix) {
		super();
		this.nomClient = nomClient;
		this.prix = prix;
	}

	public Long getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", nomClient=" + nomClient + ", prix=" + prix + "]";
	}

	
	
}