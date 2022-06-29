
package com.ceiba.biblioteca.servicio.unitaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.biblioteca.controlador.PrestamoControlador;
import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;
import com.ceiba.biblioteca.servicio.BibiliotecaInterfaz;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BibliotecaControladorTest {

	BibiliotecaInterfaz bibliotecaControlar = mock(BibiliotecaInterfaz.class);

	@Test
	public void solicitarPrestamoUsuarioTest() {
		// arrange
		SolicitudPrestarLibroRequest body = new SolicitudPrestarLibroRequest();
		SolicitudPrestarLibroResponse servicioEjecutado = new SolicitudPrestarLibroResponse();
		servicioEjecutado.setFechaMaximaDevolucion(new Date());
		servicioEjecutado.setId(1L);

		ResponseEntity<SolicitudPrestarLibroResponse> experado = ResponseEntity.ok(servicioEjecutado);
		when(bibliotecaControlar.solicitarPrestamoUsuario(body)).thenReturn(servicioEjecutado);

		// action
		PrestamoControlador controller = new PrestamoControlador(bibliotecaControlar);
		ResponseEntity<SolicitudPrestarLibroResponse> resultado = controller.solicitarPrestamoUsuario(null, body);

		// assert
		assertEquals(experado, resultado);

	}
	
	
	
	@Test
	public void consultarPrestamoUsuarioTest() {
		// arrange
		
		ConsultarPrestamoLibroResponse servicioEjecutado = new ConsultarPrestamoLibroResponse();
		servicioEjecutado.setFechaMaximaDevolucion(new Date());
		servicioEjecutado.setId(1L);
		servicioEjecutado.setIdentificacionUsuario("123");
		servicioEjecutado.setIsbn("123");
		servicioEjecutado.setTipoUsuario(1);

		ResponseEntity<ConsultarPrestamoLibroResponse> experado = ResponseEntity.ok(servicioEjecutado);
		when(bibliotecaControlar.consultarPrestamoLibros(1L)).thenReturn(servicioEjecutado);

		// action
		PrestamoControlador controller = new PrestamoControlador(bibliotecaControlar);
		ResponseEntity<ConsultarPrestamoLibroResponse> resultado = controller.obtenerInformacionPrestamo(null, 1L);

		// assert
		assertEquals(experado, resultado);

	}


}
