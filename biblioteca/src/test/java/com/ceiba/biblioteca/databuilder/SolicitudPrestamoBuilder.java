package com.ceiba.biblioteca.databuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;
import com.ceiba.biblioteca.modelo.TipoUsuario;

public class SolicitudPrestamoBuilder {
	public SolicitudPrestarLibroRequest builderAfiliado() {
		SolicitudPrestarLibroRequest solicitudBuilder = new SolicitudPrestarLibroRequest();
		solicitudBuilder.setIdentificacionUsuario("1");
		solicitudBuilder.setIsbn("1");
		solicitudBuilder.setTipoUsuario(1);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroRequest builderEmpleado() {
		SolicitudPrestarLibroRequest solicitudBuilder = new SolicitudPrestarLibroRequest();
		solicitudBuilder.setIdentificacionUsuario("1");
		solicitudBuilder.setIsbn("1");
		solicitudBuilder.setTipoUsuario(2);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroRequest builderInvitado() {
		SolicitudPrestarLibroRequest solicitudBuilder = new SolicitudPrestarLibroRequest();
		solicitudBuilder.setIdentificacionUsuario("1");
		solicitudBuilder.setIsbn("1");
		solicitudBuilder.setTipoUsuario(3);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroRequest builderOtro() {
		SolicitudPrestarLibroRequest solicitudBuilder = new SolicitudPrestarLibroRequest();
		solicitudBuilder.setIdentificacionUsuario("1");
		solicitudBuilder.setIsbn("1");
		solicitudBuilder.setTipoUsuario(4);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroResponse solicitudPrestarLibroResponse() {
		SolicitudPrestarLibroResponse solicitudBuilder = new SolicitudPrestarLibroResponse();
		solicitudBuilder.setFechaMaximaDevolucion(new Date(1622825660));
		solicitudBuilder.setId(1L);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroResponse solicitudPrestarLibroAfiliadoResponse() {
		SolicitudPrestarLibroResponse solicitudBuilder = new SolicitudPrestarLibroResponse();
		solicitudBuilder.setFechaMaximaDevolucion(calcularFechaDevolucion("1", LocalDate.now()));
		solicitudBuilder.setId(null);
		return solicitudBuilder;
	}

	public SolicitudPrestarLibroResponse solicitudPrestarLibroEmpleadoResponse() {
		SolicitudPrestarLibroResponse solicitudBuilder = new SolicitudPrestarLibroResponse();
		solicitudBuilder.setFechaMaximaDevolucion(calcularFechaDevolucion("2", LocalDate.now()));
		solicitudBuilder.setId(null);
		return solicitudBuilder;
	}

	
	public SolicitudPrestarLibroResponse solicitudPrestarLibroInvitadoResponse() {
		SolicitudPrestarLibroResponse solicitudBuilder = new SolicitudPrestarLibroResponse();
		solicitudBuilder.setFechaMaximaDevolucion(calcularFechaDevolucion("3", LocalDate.now()));
		solicitudBuilder.setId(null);
		return solicitudBuilder;
	}

	
	
	private Date calcularFechaDevolucion(String tipo, LocalDate fechaSolicitud) {
		LocalDate result = fechaSolicitud;
		int calculoDiasDevolucion = calcularDiasParaDevolucion(tipo);
		int addedDays = 0;
		while (addedDays < calculoDiasDevolucion) {
			result = result.plusDays(1);
			if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				++addedDays;
			}
		}
		return Date.from(result.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private int calcularDiasParaDevolucion(String tipo) {
		if (String.valueOf(tipo).equals(TipoUsuario.AFILIADO.label.toString())) {
			return 10;
		} else if (String.valueOf(tipo).equals(TipoUsuario.EMPLEADO.label.toString())) {
			return 8;
		} else {
			return 7;
		}
	}

}
