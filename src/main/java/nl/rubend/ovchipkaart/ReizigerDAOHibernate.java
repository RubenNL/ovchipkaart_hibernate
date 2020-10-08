package nl.rubend.ovchipkaart;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
	private final Session session;
	public ReizigerDAOHibernate(Session session) {
		this.session=session;
	}
	@Override
	public boolean save(Reiziger reiziger) {
		try {
			Query query = session.createSQLQuery("INSERT INTO reiziger (reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum) VALUES (:reiziger_id,:voorletters,:tussenvoegsel,:achternaam,:geboortedatum)");
			query.setParameter("reiziger_id", reiziger.getId());
			query.setParameter("voorletters", reiziger.getVoorletters());
			query.setParameter("tussenvoegsel", reiziger.getTussenvoegsel());
			query.setParameter("achternaam", reiziger.getAchternaam());
			query.setParameter("geboortedatum", reiziger.getGeboortedatum());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean update(Reiziger reiziger) {
		try {
			Query query = session.createSQLQuery("UPDATE reiziger A SET voorletters=:voorletters,tussenvoegsel=:tussenvoegsel,achternaam=:achternaam,geboortedatum=:geboortedatum WHERE A.reiziger_id=:reiziger_id");
			query.setParameter("reiziger_id", reiziger.getId());
			query.setParameter("voorletters", reiziger.getVoorletters());
			query.setParameter("tussenvoegsel", reiziger.getTussenvoegsel());
			query.setParameter("achternaam", reiziger.getAchternaam());
			query.setParameter("geboortedatum", reiziger.getGeboortedatum());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Reiziger reiziger) {
		try {
			Query query = session.createQuery("DELETE FROM Reiziger a WHERE a.id=:id");
			query.setParameter("id", reiziger.getId());
			query.executeUpdate();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public Reiziger findById(int id) {
		Query query=session.createQuery("SELECT a FROM Reiziger a WHERE a.id=:id", Reiziger.class);
		query.setParameter("id",id);
		return (Reiziger) query.getSingleResult();
	}

	@Override
	public List<Reiziger> findByGebDatum(String datum) {
		Query query=session.createQuery("SELECT a FROM Reiziger a WHERE a.geboortedatum=:gebdatum", Reiziger.class);
		query.setParameter("gebdatum",java.sql.Date.valueOf(datum));
		return query.getResultList();
	}

	@Override
	public List<Reiziger> findAll() {
		return session.createQuery("SELECT a FROM Reiziger a", Reiziger.class).getResultList();
	}
}
