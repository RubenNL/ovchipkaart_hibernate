package nl.rubend.ovchipkaart;

import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
	private final Session session;
	private final ReizigerDAO rdao;
	public OVChipkaartDAOHibernate(Session session,ReizigerDAO rdao) {
		this.session=session;
		this.rdao=rdao;
	}
	@Override
	public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
		Query query=session.createQuery("SELECT a FROM OVChipkaart a WHERE a.reiziger=:reiziger", OVChipkaart.class);
		query.setParameter("reiziger",reiziger);
		return query.getResultList();
	}

	@Override
	public List<OVChipkaart> findByReizigerId(int id) throws SQLException {
		Query query=session.createQuery("SELECT a FROM OVChipkaart a WHERE a.reiziger=:reiziger", Reiziger.class);
		query.setParameter("reiziger",rdao.findById(id));
		return query.getResultList();
	}

	@Override
	public boolean save(OVChipkaart ovChipkaart) {
		try {
			Query query = session.createSQLQuery("INSERT INTO ov_chipkaart(kaart_nummer,geldig_tot,klasse,saldo,reiziger_id) VALUES (:kaart_nummer,:geldig_tot,:klasse,:saldo,:reiziger_id);");
			query.setParameter("kaart_nummer", ovChipkaart.getKaart_nummer());
			query.setParameter("geldig_tot", ovChipkaart.getGeldig_tot());
			query.setParameter("klasse", ovChipkaart.getKlasse());
			query.setParameter("saldo", ovChipkaart.getSaldo());
			query.setParameter("reiziger_id", ovChipkaart.getReiziger().getId());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(OVChipkaart ovChipkaart) {
		try {
			Query query = session.createSQLQuery("UPDATE ov_chipkaart SET geldig_tot=:geldig_tot,klasse=:klasse,saldo=:saldo,reiziger_id=:reiziger_id WHERE kaart_nummer=:kaart_nummer");
			query.setParameter("kaart_nummer", ovChipkaart.getKaart_nummer());
			query.setParameter("geldig_tot", ovChipkaart.getGeldig_tot());
			query.setParameter("klasse", ovChipkaart.getKlasse());
			query.setParameter("saldo", ovChipkaart.getSaldo());
			query.setParameter("reiziger_id", ovChipkaart.getReiziger().getId());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(OVChipkaart ovChipkaart) {
		try {
			Query query = session.createQuery("DELETE FROM OVChipkaart a WHERE a.kaart_nummer=:id");
			query.setParameter("id", ovChipkaart.getKaart_nummer());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<OVChipkaart> findAll() throws SQLException {
		return session.createQuery("SELECT a FROM OVChipkaart a", OVChipkaart.class).getResultList();
	}
}
