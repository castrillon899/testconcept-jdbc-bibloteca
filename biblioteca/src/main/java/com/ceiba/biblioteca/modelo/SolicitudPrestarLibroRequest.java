package com.ceiba.biblioteca.modelo;

public class SolicitudPrestarLibroRequest {

	private String isbn;
	private String identificacionUsuario;
	private int tipoUsuario;

	

	public String getIsbn() {
		return isbn;
	}

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	

}
