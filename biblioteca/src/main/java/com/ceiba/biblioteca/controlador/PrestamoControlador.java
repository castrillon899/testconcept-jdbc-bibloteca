package com.ceiba.biblioteca.controlador;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;
import com.ceiba.biblioteca.servicio.BibiliotecaInterfaz;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

	BibiliotecaInterfaz bibliotecaImp;

	public PrestamoControlador(BibiliotecaInterfaz bibliotecaImp) {
		this.bibliotecaImp = bibliotecaImp;
	}

	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SolicitudPrestarLibroResponse> solicitarPrestamoUsuario(
			@RequestHeader Map<String, String> headers, @RequestBody SolicitudPrestarLibroRequest body) {
		SolicitudPrestarLibroResponse response = bibliotecaImp.solicitarPrestamoUsuario(body);
		return new ResponseEntity<SolicitudPrestarLibroResponse>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsultarPrestamoLibroResponse> obtenerInformacionPrestamo(
			@RequestHeader Map<String, String> headers, @PathVariable Long id) {
		ConsultarPrestamoLibroResponse response = bibliotecaImp.consultarPrestamoLibros(id);
		return new ResponseEntity<ConsultarPrestamoLibroResponse>(response, HttpStatus.OK);
	}
}
