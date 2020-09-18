package com.github.rshtishi.demo.redistemplate;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = -8243145429438016231L;
	
	private String id;
	private String fullName;
	private int age;
	
	
	
	public Person() {
		super();
	}
	public Person(String id, String fullName, int age) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", fullName=" + fullName + ", age=" + age + "]";
	}
	
	

}
