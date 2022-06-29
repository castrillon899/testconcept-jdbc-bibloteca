package com.ceiba.biblioteca.databuilder;

import java.util.Date;

import com.ceiba.biblioteca.entidad.Prestamo;

public class PrestamoTestDataBuilder {

	
	
	
	public Prestamo build() {
		Prestamo prestamo=new Prestamo();
		prestamo.setId(1L);
		prestamo.setIdentificacionUsuario("1");
		prestamo.setFechaMaximaDevolucion(new Date());
		prestamo.setTipoUsuario(3);
		prestamo.setEstado("ACTIVO");
		prestamo.setIsbn("1");
		return prestamo;
	}
}


