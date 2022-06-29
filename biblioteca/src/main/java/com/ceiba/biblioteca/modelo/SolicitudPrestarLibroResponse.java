package com.ceiba.biblioteca.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SolicitudPrestarLibroResponse {
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaMaximaDevolucion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public Date getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}
	public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}
	@Override
	public String toString() {
		return "SolicitudPrestarLibroResponse [id=" + id + ", fechaMaximaDevolucion=" + fechaMaximaDevolucion + "]";
	}
	
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitudPrestarLibroResponse other = (SolicitudPrestarLibroResponse) obj;
		if (fechaMaximaDevolucion == null) {
			if (other.fechaMaximaDevolucion != null)
				return false;
		} else if (!fechaMaximaDevolucion.equals(other.fechaMaximaDevolucion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
   

}
