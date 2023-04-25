package es.atos.club.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
	
	
	private static final long serialVerionUID = 1L;
	
	
	public ElementNotFoundException(long id) {
		super("No se puede encontrar el elemento con id=" + id);
	}
	
	public ElementNotFoundException(int id) {
		super("No se puede encontrar el elemento con id=" + id);
	}
	
	public ElementNotFoundException(String id) {
		super("No se puede encontrar el elemento con id=" + id);
	}
}
