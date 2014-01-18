package org.test.persistance.test;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.test.persistance.jpa.Address;
import org.test.persistance.jpa.Event;
import org.test.persistance.jpa.Guest;
import org.test.persistance.jpa.Item;
import org.test.persistance.jpa.User;
import org.test.persistance.utils.HibernateUtils;

public class Main {
	private static Session s = null;

	public static void main(String[] args) {
		// Ouverture d'une session Hibernate
		s = HibernateUtils.getSession();

		// Lancement des tests successifs
		testCreate();

		// Fermeture de la session Hibernate
		s.close();
	}

	// Test de la persistance d'objets comportant une association
	public static void testCreate() {
		// Cr�ation des objets � rendre persistants
		Event e = new Event("Titre de l'�v�nement", "description", true);
		Address a = new Address("Nom de l'adresse", "24 rue des cerisiers",
				"75001", "Paris");

		User u = new User("mistra", "mistra@mistra.fr", "formation");
		User u2 = new User("John Doe", "john.doe@mistra.fr", "password");

		Event e2 = new Event("Event sans adresse", "", false);

		Item i = new Item("jus d'orange", 5, 2);
		Item i2 = new Item("chips", 3, 0);
		Item i3 = new Item("pistaches", 4, 1);

		Guest g = new Guest("Invit� 1", "invite@mistra.fr");
		Guest g2 = new Guest("Invit� 2", "guest@mistra.fr");

		// Les trois Items sont li�s � l'Event e2
		i.setEvent(e2);
		i2.setEvent(e2);
		i3.setEvent(e2);
		// L�Event e poss�de l'Address a et l'User u
		e.setAddress(a);
		e.setUser(u);
		// L'Event e2 poss�de l'User u mais pas d'adresse
		e2.setUser(u);
		// L'Event e2 poss�de les trois Items instanci�s (bidirectionnel)
		ArrayList<Item> itemList = new ArrayList<Item>();
		itemList.add(i);
		itemList.add(i2);
		itemList.add(i3);
		e2.setItemList(itemList);
		// L�User u poss�de les deux Events instanci�s (bidirectionnel)
		ArrayList<Event> eventList = new ArrayList<Event>();
		eventList.add(e);
		eventList.add(e2);
		u.setEventList(eventList);
		// Le Guest g est invit� aux deux Events
		g.setEvents(eventList);
		// Le Guest g2 est invit� � l'Event e
		ArrayList<Event> eventList2 = new ArrayList<Event>();
		eventList2.add(e);
		g2.setEvents(eventList2);
		// L'Event e a pour invit� g2 (bidirectionnel)
		ArrayList<Guest> guestList = new ArrayList<Guest>();
		guestList.add(g2);
		e.setGuestList(guestList);
		// L'Event e2 a pour invit� g et g2 (bidirectionnel)
		ArrayList<Guest> guestList2 = new ArrayList<Guest>();
		guestList2.add(g);
		guestList2.add(g2);
		e2.setGuestList(guestList2);

		// Enregistrements
		Transaction tx = s.beginTransaction();
		s.persist(i);
		s.persist(i2);
		s.persist(i3);
		s.persist(g);
		s.persist(g2);

		s.persist(e);
		s.persist(e2);
		s.persist(u2);
		tx.commit();

		// Affichage du contenu des tables
		printEvents();
		printAddresses();
		printUsers();
		printItems();
		printGuests();
	}

	// Affiche le contenu de la table EVENTS
	@SuppressWarnings({ "unchecked", "deprecation" })
	private static void printEvents() {
		System.out.println("---Events---");
		// Cr�ation de la requ�te
		Query q = s.createQuery("from Event");
		ArrayList<Event> list = (ArrayList<Event>) q.list();
		// Affichage de la liste de r�sultats
		for (Event e : list) {
			System.out.println("[id] = " + e.getId() + "\t" + "[title] = "
					+ e.getTitle() + "\t" + "[desc] = " + e.getDescription()
					+ "\t" + "[date] = "
					+ e.getBeginDate().getTime().toLocaleString() + "\t"
					+ "[allDay] = " + e.isAllDay());

			if (e.getAddress() != null)
				System.out.println("[addressID] = " + e.getAddress().getId());
			if (e.getUser() != null)
				System.out.println("[userID] = " + e.getUser().getId());

			System.out.print("[items ID ]: ");
			if (e.getItemList() != null) {
				for (Item i : e.getItemList())
					System.out.print(i.getId() + " ");
			}
			System.out.println();
			System.out.print("[guests ID ]: ");
			if (e.getGuestList() != null) {
				for (Guest g : e.getGuestList())
					System.out.print(g.getId() + " ");
			}
			System.out.println();
		}
	}

	// Affiche le contenu de la table USERS
	@SuppressWarnings("unchecked")
	private static void printUsers() {
		System.out.println("---Users---");
		// Cr�ation de la requ�te
		Query q = s.createQuery("from User");
		ArrayList<User> list = (ArrayList<User>) q.list();
		// Affichage de la liste de r�sultats
		for (User u : list) {
			System.out.println("[id] = " + u.getId() + "\t" + "[login] = "
					+ u.getLogin() + "\t" + "[email] = " + u.getEmail() + "\t"
					+ "[pass] = " + u.getPass());
			System.out.print("[Events ID ]: ");
			if (u.getEventList() != null) {
				for (Event e : u.getEventList())
					System.out.print(e.getId() + " ");
			}
			System.out.println();
		}
	}

	// Affiche le contenu de la table ADDRESSES
	@SuppressWarnings("unchecked")
	private static void printAddresses() {
		System.out.println("---Addresses---");
		// Cr�ation de la requ�te
		Query q = s.createQuery("from Address");
		ArrayList<Address> list = (ArrayList<Address>) q.list();
		// Affichage de la liste de r�sultats
		for (Address a : list) {
			System.out.println("[id] = " + a.getId() + "\t" + "[name] = "
					+ a.getName() + "\t" + "[street] = " + a.getStreet() + "\t"
					+ "[comments] = " + a.getComments() + "\t" + "[zipCode] = "
					+ a.getZipCode() + "\t" + "[city] = " + a.getCity());
		}
	}

	// Affiche le contenu de la table ITEMS
	@SuppressWarnings("unchecked")
	private static void printItems() {
		System.out.println("---Items---");
		// Cr�ation de la requ�te
		Query q = s.createQuery("from Item");
		ArrayList<Item> list = (ArrayList<Item>) q.list();
		// Affichage de la liste de r�sultats
		for (Item i : list) {
			System.out.println("[id] = " + i.getId() + "\t" + "[name] = "
					+ i.getName() + "\t" + "[neededQty] = "
					+ i.getNeededQuantity() + "\t" + "[currentQty] = "
					+ i.getCurrentQuantity());
			if (i.getEvent() != null)
				System.out.println("[eventID] = " + i.getEvent().getId());
		}
	}

	// Affiche le contenu de la table GUESTS
	@SuppressWarnings("unchecked")
	private static void printGuests() {
		System.out.println("---Guests---");
		// Cr�ation de la requ�te
		Query q = s.createQuery("from Guest");
		ArrayList<Guest> list = (ArrayList<Guest>) q.list();
		// Affichage de la liste de r�sultats
		for (Guest g : list) {
			System.out.println("[id] = " + g.getId() + "\t" + "[name] = "
					+ g.getName() + "\t" + "[email] = " + g.getEmail());
			System.out.print("[Events ID ]: ");
			if (g.getEvents() != null) {
				for (Event e : g.getEvents())
					System.out.print(e.getId() + " ");
			}
			System.out.println();
		}
	}
}