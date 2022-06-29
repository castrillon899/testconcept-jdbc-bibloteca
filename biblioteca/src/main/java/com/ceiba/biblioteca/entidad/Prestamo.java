package com.ceiba.biblioteca.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity()
@Table(name = "PRESTAMO")
@NamedQuery(name = "Prestamo.buscarPrestamosActivosPorLibro", query = "SELECT c FROM Prestamo c where c.estado='ACTIVO' and c.isbn= :isbn")
@NamedQuery(name = "Prestamo.buscarPrestamosActivosPorUsuario", query = "SELECT c FROM Prestamo c where c.estado='ACTIVO' and c.identificacionUsuario= :identificacionUsuario")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "FECHAMAXIMADEVOLUCION")
	private Date fechaMaximaDevolucion;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "IDENTIFICACIONUSUARIO")
	private String identificacionUsuario;

	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "TIPOUSUARIO")
	private int tipoUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaMaximaDevolucion() {
		return fechaMaximaDevolucion;
	}

	public void setFechaMaximaDevolucion(Date fechaMaximaDevolucion) {
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	

}
