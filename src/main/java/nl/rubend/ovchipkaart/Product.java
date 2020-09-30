package nl.rubend.ovchipkaart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
	@Id
	@Column(name="product_nummer")
	private int product_nummer;
	private String naam;
	private String beschrijving;
	private double prijs;
	@ManyToMany
	@JoinTable(
			name = "ov_chipkaart_product",
			joinColumns = @JoinColumn(name = "kaart_nummer"),
			inverseJoinColumns = @JoinColumn(name = "product_nummer"))
	private List<OVChipkaart> ovchipkaarten;
	public Product(int product_nummer,String naam,String beschrijving, double prijs) {
		this.product_nummer=product_nummer;
		this.naam=naam;
		this.beschrijving=beschrijving;
		this.prijs=prijs;
	}

	public Product() {

	}

	public int getProduct_nummer() {
		return product_nummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public void setProduct_nummer(int product_nummer) {
		this.product_nummer = product_nummer;
	}

	public List<OVChipkaart> getOvchipkaarten() {
		return ovchipkaarten;
	}

	public void setOvchipkaarten(List<OVChipkaart> ovchipkaarten) {
		this.ovchipkaarten = ovchipkaarten;
	}

	@Override
	public String toString() {
		return "Product{" +
				"product_nummer=" + product_nummer +
				", naam='" + naam + '\'' +
				", beschrijving='" + beschrijving + '\'' +
				", prijs=" + prijs +
				", ovchipkaarten=" + ovchipkaarten +
				'}';
	}
}
