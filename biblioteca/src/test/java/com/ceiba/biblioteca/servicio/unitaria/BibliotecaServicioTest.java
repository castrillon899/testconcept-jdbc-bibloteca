package com.ceiba.biblioteca.servicio.unitaria;

import com.ceiba.biblioteca.databuilder.ConsultaPrestamoTestDataBuilder;
import com.ceiba.biblioteca.databuilder.PrestamoTestDataBuilder;
import com.ceiba.biblioteca.databuilder.SolicitudPrestamoBuilder;
import com.ceiba.biblioteca.entidad.Prestamo;
import com.ceiba.biblioteca.excepcion.InvitadoConUnPrestamoActivoException;
import com.ceiba.biblioteca.excepcion.RecursoNoEncontradoException;
import com.ceiba.biblioteca.excepcion.TipoUsuarioInvalidoException;
import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;
import com.ceiba.biblioteca.repositorio.BibliotecaRepositorio;
import com.ceiba.biblioteca.servicio.BibliotecaServicio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BibliotecaServicioTest {

	BibliotecaRepositorio bibliotecaRepositorio = mock(BibliotecaRepositorio.class);

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void buscarPrestamo() {

		// arrange
		PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder();
		Prestamo prestamo = prestamoTestDataBuilder.build();
		Date fecha = null;
		prestamo.setFechaMaximaDevolucion(fecha);
		ConsultarPrestamoLibroResponse consultaResperada = new ConsultaPrestamoTestDataBuilder()
				.consultaExperadaBuilder(fecha);

		when(bibliotecaRepositorio.findById(1L)).thenReturn(Optional.of(prestamo));

		// action
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);
		ConsultarPrestamoLibroResponse consultarLibroPrestado = bibliotecaServicio.consultarPrestamoLibros(1L);
		consultaResperada.getId();
		
		// assert
		assertEquals("ACTIVO", prestamo.getEstado());
		assertEquals(consultaResperada, consultarLibroPrestado);
	}

	@Test
	public void buscarPrestamoNoEncontrado() {

		// arrange
		when(bibliotecaRepositorio.findById(1L)).thenReturn(Optional.empty());

		// action
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);

		// act
		try {
			bibliotecaServicio.consultarPrestamoLibros(1L);

		} catch (Exception e) {

			// assert
			assertTrue(e instanceof RecursoNoEncontradoException);
		}

	}

	@Test
	public void crearPrestamoLibroAflilado() {

		// arrange
		SolicitudPrestarLibroResponse solicitudPrestarLibroExperadaResponse = new SolicitudPrestamoBuilder()
				.solicitudPrestarLibroAfiliadoResponse();
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderAfiliado();

		// act
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);
		SolicitudPrestarLibroResponse solicitudPrestarLibro = bibliotecaServicio
				.solicitarPrestamoUsuario(solicitudPrestamo);
		// assert
		assertEquals(solicitudPrestarLibroExperadaResponse, solicitudPrestarLibro);
	}

	@Test
	public void crearPrestamoLibroEmpleado() {

		// arrange
		SolicitudPrestarLibroResponse solicitudPrestarLibroExperadaResponse = new SolicitudPrestamoBuilder()
				.solicitudPrestarLibroEmpleadoResponse();
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderEmpleado();

		// act
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);
		SolicitudPrestarLibroResponse solicitudPrestarLibro = bibliotecaServicio
				.solicitarPrestamoUsuario(solicitudPrestamo);
		// assert
		assertEquals(solicitudPrestarLibroExperadaResponse, solicitudPrestarLibro);
	}

	@Test
	public void crearPrestamoLibroInvitado() {

		// arrange
		SolicitudPrestarLibroResponse solicitudPrestarLibroExperadaResponse = new SolicitudPrestamoBuilder()
				.solicitudPrestarLibroInvitadoResponse();
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderInvitado();

		// act
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);
		SolicitudPrestarLibroResponse solicitudPrestarLibro = bibliotecaServicio
				.solicitarPrestamoUsuario(solicitudPrestamo);
		// assert
		assertEquals(solicitudPrestarLibroExperadaResponse, solicitudPrestarLibro);
	}

	@Test
	public void crearPrestamoLibroInvitadoConPrestamoVigente() {

		// arrange
		SolicitudPrestarLibroResponse solicitudPrestarLibroExperadaResponse = new SolicitudPrestamoBuilder()
				.solicitudPrestarLibroInvitadoResponse();
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderInvitado();

		Prestamo prestamo = new PrestamoTestDataBuilder().build();
		Date fecha = null;
		prestamo.setFechaMaximaDevolucion(fecha);
		List<Prestamo> prestamosList = new ArrayList<Prestamo>();
		prestamosList.add(prestamo);
		when(bibliotecaRepositorio.buscarPrestamosActivosPorLibro("1")).thenReturn(prestamosList);
		when(bibliotecaRepositorio.buscarPrestamosActivosPorUsuario("1")).thenReturn(prestamosList);

		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);

		// act
		try {
			bibliotecaServicio.solicitarPrestamoUsuario(solicitudPrestamo);
		} catch (Exception e) {

			// assert
			assertTrue(e instanceof InvitadoConUnPrestamoActivoException);
		}
	}

	@Test
	public void crearPrestamoOtro() {
		// arrange
		Prestamo prestamo = new PrestamoTestDataBuilder().build();
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderOtro();

		when(bibliotecaRepositorio.findById(1L)).thenReturn(Optional.of(prestamo));
		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);

		try {

			// act
			SolicitudPrestarLibroResponse solicitudPrestarLibro = bibliotecaServicio
					.solicitarPrestamoUsuario(solicitudPrestamo);

		} catch (Exception e) {
			// assert
			assertTrue(e instanceof TipoUsuarioInvalidoException);
		}

	}

	
	
	@Test
	public void crearPrestamoLibroInvitadoConPrestamoVigente2() {

		// arrange
		
		SolicitudPrestarLibroRequest solicitudPrestamo = new SolicitudPrestamoBuilder().builderInvitado();

		Prestamo prestamo = new PrestamoTestDataBuilder().build();
		Date fecha = new Date();
		prestamo.setFechaMaximaDevolucion(fecha);
		List<Prestamo> prestamosList = new ArrayList<Prestamo>();
		prestamosList.add(prestamo);
		when(bibliotecaRepositorio.buscarPrestamosActivosPorLibro("1")).thenReturn(prestamosList);
		when(bibliotecaRepositorio.buscarPrestamosActivosPorUsuario("1")).thenReturn(prestamosList);

		BibliotecaServicio bibliotecaServicio = new BibliotecaServicio(bibliotecaRepositorio);

		// act
		try {
			bibliotecaServicio.solicitarPrestamoUsuario(solicitudPrestamo);
		} catch (Exception e) {

			// assert
			assertTrue(e instanceof InvitadoConUnPrestamoActivoException);
		}
	}

}
