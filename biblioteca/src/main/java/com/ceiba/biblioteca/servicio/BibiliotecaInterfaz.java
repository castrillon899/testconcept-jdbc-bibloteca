package com.ceiba.biblioteca.servicio;

import org.springframework.stereotype.Component;

import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;


@Component
public interface BibiliotecaInterfaz {
	
	SolicitudPrestarLibroResponse solicitarPrestamoUsuario(SolicitudPrestarLibroRequest request) ;
	
	ConsultarPrestamoLibroResponse consultarPrestamoLibros(Long id);

}
