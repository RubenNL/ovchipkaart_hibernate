package nl.rubend.ovchipkaart;

import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
	private static Session session;
	public ProductDAOHibernate(Session session) {
		this.session=session;
	}
	@Override
	public boolean save(Product product) {
		try {
			Query query = session.createSQLQuery("INSERT INTO product (product_nummer,naam,beschrijving,prijs) VALUES(:product_nummer,:naam,:beschrijving,:prijs)");
			query.setParameter("product_nummer", product.getProduct_nummer());
			query.setParameter("naam", product.getNaam());
			query.setParameter("beschrijving", product.getBeschrijving());
			query.setParameter("prijs", product.getPrijs());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			Query query = session.createSQLQuery("UPDATE product SET naam=:naam,beschrijving=:beschrijving,prijs=:prijs WHERE product_nummer=:product_nummer;");
			query.setParameter("naam", product.getNaam());
			query.setParameter("beschrijving", product.getBeschrijving());
			query.setParameter("prijs", product.getPrijs());
			query.setParameter("product_nummer", product.getProduct_nummer());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		try {
			Query query = session.createQuery("DELETE FROM Product a WHERE a.product_nummer=:product_nummer");
			query.setParameter("product_nummer", product);
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
		Query query=session.createQuery("SELECT a FROM Product a WHERE :ovchipkaart IN a.ovchipkaarten", Reiziger.class);
		query.setParameter("ovchipkaart",ovChipkaart);
		return query.getResultList();
	}

	@Override
	public List<Product> findAll() throws SQLException {
		return session.createQuery("SELECT a FROM Product a", Product.class).getResultList();
	}
}
