package com.example.signinapp;

public class Visitor {
	private long id;
	private String firstName, lastName;
	
	public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}
}
