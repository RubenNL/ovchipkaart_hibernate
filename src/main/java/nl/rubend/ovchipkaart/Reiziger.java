package nl.rubend.ovchipkaart;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="reiziger")
public class Reiziger {
	@Id
	@Column(name="reiziger_id")
	private int id;
	private String voorletters;
	private String tussenvoegsel;
	private String achternaam;
	private Date geboortedatum;
	@OneToOne(mappedBy="reiziger")
	private Adres adres;
	public Reiziger(int id,String voorletters,String tussenvoegsel,String achternaam,Date geboortedatum) {
		this.id=id;
		this.voorletters=voorletters;
		this.tussenvoegsel=tussenvoegsel;
		this.achternaam=achternaam;
		this.geboortedatum=geboortedatum;
	}

	public Reiziger() {

	}

	public int getId() {return this.id;}
	public void setId(int id) {this.id=id;}
	public String getVoorletters() {return this.voorletters;}
	public void setVoorletters(String voorletters)  {this.voorletters=voorletters;}
	public String getTussenvoegsel() {return this.tussenvoegsel;}
	public void setTussenvoegsel(String tussenvoegsel)  {this.tussenvoegsel=tussenvoegsel;}
	public String getAchternaam() {return this.achternaam;}
	public void setAchternaam(String achternaam) {this.achternaam=achternaam;}
	public Date getGeboortedatum() {return geboortedatum;}
	public void setGeboortedatum(Date geboortedatum) {this.geboortedatum=geboortedatum;}
	public Adres getAdres() {return this.adres;}
	public void setAdres(Adres adres) {this.adres=adres;}
	public String getNaam() {return this.voorletters+" "+this.tussenvoegsel+" "+this.achternaam;}
	public String toString() {return "reiziger #"+this.id+": "+this.voorletters+". "+(this.tussenvoegsel==null?"":this.tussenvoegsel+" ")+this.achternaam+" ("+this.geboortedatum+") adres:"+this.adres;}
}