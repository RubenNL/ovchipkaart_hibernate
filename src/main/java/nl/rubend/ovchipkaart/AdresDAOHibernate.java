package nl.rubend.ovchipkaart;

import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
	private Session session;
	public AdresDAOHibernate(Session session) {
		this.session=session;
	}
	@Override
	public boolean save(Adres adres) {
		try {
			Query query = session.createSQLQuery("INSERT INTO adres(adres_id,postcode,huisnummer,straat,woonplaats,reiziger_id) VALUES (:adres_id,:postcode,:huisnummer,:straat,:woonplaats,:reiziger_id);");
			query.setParameter("adres_id", adres.getId());
			query.setParameter("postcode", adres.getPostcode());
			query.setParameter("huisnummer", adres.getHuisnummer());
			query.setParameter("straat", adres.getStraat());
			query.setParameter("woonplaats", adres.getWoonplaats());
			query.setParameter("reiziger_id", adres.getReiziger_id());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Adres adres) {
		try {
			Query query = session.createSQLQuery("UPDATE adres SET postcode=:postcode,huisnummer=:huisnummer,straat=:straat,woonplaats=:woonplaats,reiziger_id=:reiziger_id WHERE adres_id=:adres_id;");
			query.setParameter("adres_id", adres.getId());
			query.setParameter("postcode", adres.getPostcode());
			query.setParameter("huisnummer", adres.getHuisnummer());
			query.setParameter("straat", adres.getStraat());
			query.setParameter("woonplaats", adres.getWoonplaats());
			query.setParameter("reiziger_id", adres.getReiziger_id());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Adres adres) {
		try {
			Query query = session.createQuery("DELETE FROM Adres a WHERE a.id=:id");
			query.setParameter("id",adres.getId());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public Adres findByReiziger(Reiziger reiziger) throws SQLException {
		return null;
	}

	@Override
	public List<Adres> findAll() throws SQLException {
		return session.createQuery("SELECT a FROM Adres a", Adres.class).getResultList();
	}
}
