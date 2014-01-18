package org.test.persistance.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="guests")
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="guests_id")
	private Integer id;
	@Column(name="guests_name")
	private String name;
	@Column(name="guests_email")
	private String email;
	
	@ManyToMany(
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        joinColumns=@JoinColumn(name="guestId"),
        inverseJoinColumns=@JoinColumn(name="eventId")
    )
	private List<Event> events;
	
	public Guest() {}
	
	public Guest (String name, String email) {
		this.name = name;
		this.email = email;
		events = new ArrayList<Event>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
