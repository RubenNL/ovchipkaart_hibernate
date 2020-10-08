package nl.rubend.ovchipkaart;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
	// Creëer een factory voor Hibernate sessions.
	private static final SessionFactory factory;

	static {
		try {
			// Create a Hibernate session factory
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Retouneer een Hibernate session.
	 *
	 * @return Hibernate session
	 * @throws HibernateException
	 */
	private static Session getSession() throws HibernateException {
		return factory.openSession();
	}

	public static void main(String[] args) throws SQLException {
		//testFetchAll();
		ReizigerDAO rdao=new ReizigerDAOHibernate(getSession());
		testReizigerDAO(rdao);
		//testAdresDAO(new AdresDAOHibernate(getSession()),rdao);
	}

	/**
	 * P6. Haal alle (geannoteerde) entiteiten uit de database.
	 */
	private static void testFetchAll() {
		Session session = getSession();
		try {
			Metamodel metamodel = session.getSessionFactory().getMetamodel();
			System.out.println(metamodel);
			for (EntityType<?> entityType : metamodel.getEntities()) {
				System.out.println(entityType);
				Query query = session.createQuery("from " + entityType.getName());

				System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
				for (Object o : query.list()) {
					System.out.println("  " + o);
				}
				System.out.println();
			}
		} finally {
			session.close();
		}
	}
	private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
		System.out.println("\n---------- Test ReizigerDAO -------------");

		// Haal alle reizigers op uit de database
		List<Reiziger> reizigers = rdao.findAll();
		System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
		for (Reiziger r : reizigers) {
			System.out.println(r);
		}
		System.out.println();

		// Maak een nieuwe reiziger aan en persisteer deze in de database
		String gbdatum = "1981-03-14";
		Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
		System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
		rdao.save(sietske);
		reizigers = rdao.findAll();
		System.out.println(reizigers.size() + " reizigers\n");

		System.out.println("[Test]Oude geboortedatum:"+sietske.getGeboortedatum());
		sietske.setGeboortedatum(java.sql.Date.valueOf("1991-03-14"));
		rdao.update(sietske);

		//Zou de nieuwe geboortedatum moeten geven
		System.out.println("[Test]Nieuwe geboortedatum:"+rdao.findById(77).getGeboortedatum());

		System.out.println("[Test]zoeken op geboortedatum:" + rdao.findByGebDatum("1991-03-14"));

		//En verwijderen.
		rdao.delete(sietske);
		//Nu zou hij niet meer te vinden moeten zijn?
		System.out.println("[Test]Zou leeg moeten zijn: "+rdao.findById(77));
	}
	private static void testAdresDAO(AdresDAO adao,ReizigerDAO rdao) throws SQLException {
		//ReizigerDAO toegevoegd om de zoeken op reiziger te testen, wat hieronder valt.
		System.out.println("\n---------- Test ReizigerDAO -------------");
		//sietske toevoegen van vorige opdracht:
		rdao.save(new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf("1981-03-14")));
		// Haal alle reizigers op uit de database
		List<Adres> adressen = adao.findAll();
		System.out.println("[Test] AdresDAO.findAll() geeft de volgende adresssen:");
		for (Adres a : adressen) System.out.println(a);
		System.out.println();

		// Maak een nieuwe reiziger aan en persisteer deze in de database
		Adres sietskeAdres = new Adres(99, "1234AB", "12", "huisstraat", "plaat", new Reiziger());//aangepast
		System.out.print("[Test] Eerst " + adressen.size() + " adressen, na ReizigerDAO.save() ");
		adao.save(sietskeAdres);
		adressen = adao.findAll();
		System.out.println(adressen.size() + " reizigers\n");

		System.out.println("[Test]Oud huisnummer:" + sietskeAdres.getHuisnummer());
		sietskeAdres.setHuisnummer("34A");
		adao.update(sietskeAdres);

		//Zou het nieuwe adres moeten geven
		System.out.println("[Test]Nieuw huisnummer:" + adao.findByReiziger(rdao.findById(77)).getHuisnummer());

		//En verwijderen.
		adao.delete(sietskeAdres);
		//Nu zou hij niet meer te vinden moeten zijn?
		System.out.println("[Test]Zou leeg moeten zijn: " + adao.findByReiziger(rdao.findById(77)));
	}

}
