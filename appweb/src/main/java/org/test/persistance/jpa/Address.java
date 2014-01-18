package org.test.persistance.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="adresses")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="adresses_id")
	private Integer id;
	@Column(name="adresses_name", nullable=false)
	private String name;
	@Column(name="adresses_street")
	private String street;
	@Column(name="adresses_comments")
	private String comments;
	@Column(name="adresses_zipcode")
	private String zipCode;
	@Column(name="adresses_city")
	private String city;

	@OneToOne(mappedBy = "address")
	private Event event;
	
	public Address(){
	}
	
	public Address(String name, String street, String zipCode, String city) {
		this.name = name;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public boolean equals(Address a){
		boolean returnValue = true;
		
		if (a != null && this.id != null
				&& this.name != null 	&& this.street != null 
				&& this.zipCode != null && this.city != null) {
			if ((!this.id.equals(a.getId()))
					|| (!this.name.equals(a.getName()))
					|| (!this.street.equals(a.getStreet()))
					|| (!this.zipCode.equals(a.getZipCode()))
					|| (!this.city.equals(a.getCity()))) {
				
				returnValue = false;
			}
			else if (this.comments != null && a.comments != null) {
				if (!this.comments.equals(a.getComments())) {
					return false;
				}
			}
		}
		else
			returnValue = false;
			
		return returnValue;	
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}

