package com.ceiba.biblioteca.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultarPrestamoLibroResponse {
	private Long id;
	private String isbn;
	private String identificacionUsuario;
	private int tipoUsuario;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaMaximaDevolucion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Date getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}

	public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}

	@Override
	public String toString() {
		return "ConsultarPrestamoLibroResponse [id=" + id + ", isbn=" + isbn + ", identificacionUsuario="
				+ identificacionUsuario + ", tipoUsuario=" + tipoUsuario + ", fechaMaximaDevolucion="
				+ fechaMaximaDevolucion + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ConsultarPrestamoLibroResponse libroResponse = (ConsultarPrestamoLibroResponse) o;
		return id == libroResponse.id && isbn.equals(libroResponse.getIsbn())
				&& identificacionUsuario.equals(libroResponse.getIdentificacionUsuario())
				&& tipoUsuario == libroResponse.getTipoUsuario();
	}

}
