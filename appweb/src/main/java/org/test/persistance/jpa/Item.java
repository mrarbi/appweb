package org.test.persistance.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="items_id")
	private Integer id;
	@Column(name="items_name")
	private String name;
	@Column(name="items_neededQuantity")
	private Integer neededQuantity;
	@Column(name="items_currentQuantity")
	private Integer currentQuantity;
	@ManyToOne(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	)	
	@JoinColumn(name="items_eventId")
	private Event event;
	
	public Item() {	}
	
	public Item(String name, Integer neededQuantity, Integer currentQuantity){
		this.name = name;
		this.neededQuantity = neededQuantity;
		this.currentQuantity = currentQuantity;
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

	public Integer getNeededQuantity() {
		return neededQuantity;
	}

	public void setNeededQuantity(Integer neededQuantity) {
		this.neededQuantity = neededQuantity;
	}

	public Integer getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
	
	
