package nl.rubend.ovchipkaart;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ov_chipkaart")
public class OVChipkaart {
	@Id
	private int kaart_nummer;
	private Date geldig_tot;
	private int klasse;
	private double saldo;
	@ManyToOne
	@JoinColumn(name="reiziger_id")
	private Reiziger reiziger;
	@ManyToMany
	@JoinTable(
			name = "ov_chipkaart_product",
			joinColumns = @JoinColumn(name = "kaart_nummer"),
			inverseJoinColumns = @JoinColumn(name = "product_nummer"))
	private List<Product> producten;
	public OVChipkaart(int kaart_nummer,Date geldig_tot,int klasse,double saldo,Reiziger reiziger) {
		this.kaart_nummer=kaart_nummer;
		this.geldig_tot=geldig_tot;
		this.klasse=klasse;
		this.saldo=saldo;
		this.reiziger=reiziger;
	}

	public OVChipkaart() {

	}

	public int getKaart_nummer() {
		return kaart_nummer;
	}

	public void setKaart_nummer(int kaart_nummer) {
		this.kaart_nummer = kaart_nummer;
	}

	public Date getGeldig_tot() {
		return geldig_tot;
	}

	public void setGeldig_tot(Date geldig_tot) {
		this.geldig_tot = geldig_tot;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Reiziger getReiziger() {
		return reiziger;
	}

	public void setReiziger_id(Reiziger reiziger) {
		this.reiziger = reiziger;
	}

	public void setProducten(List<Product> producten) {
		this.producten=producten;
	}

	public List<Product> getProducten() {
		return this.producten;
	}

	public void addProduct(Product product) {
		this.producten.add(product);
	}
	public void removeProduct(Product product) {
		this.producten.remove(product);
	}

	@Override
	public String toString() {
		return "OVChipkaart{" +
				"kaart_nummer=" + kaart_nummer +
				", geldig_tot=" + geldig_tot +
				", klasse=" + klasse +
				", saldo=" + saldo +
				", reiziger=" + reiziger +
				", producten=" + producten +
				'}';
	}
}
