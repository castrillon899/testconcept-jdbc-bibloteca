package com.ceiba.biblioteca.excepcion;

public class InvitadoConUnPrestamoActivoException   extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public InvitadoConUnPrestamoActivoException(String message) {
		super(message);
	}
}
