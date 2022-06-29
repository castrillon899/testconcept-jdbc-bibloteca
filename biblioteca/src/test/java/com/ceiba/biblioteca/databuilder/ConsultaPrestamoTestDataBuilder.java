package com.ceiba.biblioteca.databuilder;

import java.util.Date;

import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;

public class ConsultaPrestamoTestDataBuilder {

	public ConsultarPrestamoLibroResponse consultaExperadaBuilder(Date fecha) {
		ConsultarPrestamoLibroResponse consultaResperada = new ConsultarPrestamoLibroResponse();
		consultaResperada.setFechaMaximaDevolucion(fecha);
		consultaResperada.setId(1L);
		consultaResperada.setIdentificacionUsuario("1");
		consultaResperada.setIsbn("1");
		consultaResperada.setTipoUsuario(3);
		return consultaResperada;
	}

}
