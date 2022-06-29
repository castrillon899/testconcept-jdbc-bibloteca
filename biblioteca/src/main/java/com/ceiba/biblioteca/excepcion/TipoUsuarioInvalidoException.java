package com.ceiba.biblioteca.excepcion;

public class TipoUsuarioInvalidoException   extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TipoUsuarioInvalidoException(String message) {
		super(message);
	}
}
