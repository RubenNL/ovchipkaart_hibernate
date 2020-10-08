package nl.rubend.ovchipkaart;

import javax.persistence.*;

@Entity
@Table(name="adres")
public class Adres {
	@Id
	@Column(name="adres_id")
	private int id;
	private String postcode;
	private String huisnummer;
	private String straat;
	private String woonplaats;
		@OneToOne
		@JoinColumn(name="reiziger_id")
		private Reiziger reiziger;

	public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
		this.id = id;
		this.postcode = postcode;
		this.huisnummer = huisnummer;
		this.straat = straat;
		this.woonplaats = woonplaats;
		this.reiziger = reiziger;
	}

	public Adres() {

	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getPostcode() {return postcode;}
	public void setPostcode(String postcode) {this.postcode = postcode;}
	public String getHuisnummer() {return huisnummer;}
	public void setHuisnummer(String huisnummer) {this.huisnummer = huisnummer;}
	public String getStraat() {return straat;}
	public void setStraat(String straat) {this.straat = straat;}
	public String getWoonplaats() {return woonplaats;}
	public void setWoonplaats(String woonplaats) {this.woonplaats = woonplaats;}
	public Reiziger getReiziger_id() {return reiziger;}
	public void setReiziger_id(Reiziger reiziger) {this.reiziger = reiziger;}

	@Override
	public String toString() {
		return "Adres{" +
				"id=" + id +
				", postcode='" + postcode + '\'' +
				", huisnummer='" + huisnummer + '\'' +
				", straat='" + straat + '\'' +
				", woonplaats='" + woonplaats + '\'' + //Ik heb hier reiziger weggehaald, om geen stackoverflow error te krijgen.
				'}';
	}
}
