package es.atos.club.dto;

import java.util.Objects;



public class UserDto {
	
	private String username;
	private String name;
	private String email;
	private String role;
	
	
	
	public UserDto() {
		super();
	}



	public UserDto(String username, String name, String email, String role) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	


	@Override
	public int hashCode() {
		return Objects.hash(username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(username, other.username);
	}
	
	
}
	
