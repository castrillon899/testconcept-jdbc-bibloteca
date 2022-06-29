package com.ceiba.biblioteca.servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ceiba.biblioteca.entidad.Prestamo;
import com.ceiba.biblioteca.excepcion.InvitadoConUnPrestamoActivoException;
import com.ceiba.biblioteca.excepcion.RecursoNoEncontradoException;
import com.ceiba.biblioteca.excepcion.TipoUsuarioInvalidoException;
import com.ceiba.biblioteca.modelo.ConsultarPrestamoLibroResponse;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroRequest;
import com.ceiba.biblioteca.modelo.SolicitudPrestarLibroResponse;
import com.ceiba.biblioteca.modelo.TipoUsuario;
import com.ceiba.biblioteca.repositorio.BibliotecaRepositorio;

@Component
public class BibliotecaServicio implements BibiliotecaInterfaz {

	private static final String ACTIVO = "ACTIVO";

	private static final String TIPO_USUARIO_INVALIDO = "Tipo de usuario no permitido en la biblioteca";

	private static final String USUARIO_INVITADO_CON_PRESTAMO = "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";

	private static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado";

	private LocalDate fechaPrestamo;

	BibliotecaRepositorio prestamoRepositorio;

	public BibliotecaServicio(BibliotecaRepositorio prestamoRepositorio) {
		this.prestamoRepositorio = prestamoRepositorio;
	}

	@Override
	public SolicitudPrestarLibroResponse solicitarPrestamoUsuario(SolicitudPrestarLibroRequest request) {
		fechaPrestamo = LocalDate.now();
		validarTipoDeUsuario(request);
		validarLibrosPrestadosPorUsuario(request);
		return generarPrestamo(request);
	}

	private SolicitudPrestarLibroResponse generarPrestamo(SolicitudPrestarLibroRequest request) {
		Date fechaDevolucionCalculada = calcularFechaDevolucion(request, fechaPrestamo);
		Prestamo prestamo = new Prestamo();
		prestamo.setEstado(ACTIVO);
		prestamo.setFechaMaximaDevolucion(fechaDevolucionCalculada);
		prestamo.setIdentificacionUsuario(request.getIdentificacionUsuario());
		prestamo.setIsbn(request.getIsbn());
		prestamo.setTipoUsuario(request.getTipoUsuario());

		prestamoRepositorio.save(prestamo);
		SolicitudPrestarLibroResponse ob = new SolicitudPrestarLibroResponse();
		ob.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());
		ob.setId(prestamo.getId());
		return ob;
	}

	private void validarTipoDeUsuario(SolicitudPrestarLibroRequest request) {

		if (!String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.AFILIADO.label.toString())
				&& !String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.EMPLEADO.label.toString())
				&& !String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.INVITADO.label.toString())) {
			throw new TipoUsuarioInvalidoException(TIPO_USUARIO_INVALIDO);
		}

	}

	private void validarLibrosPrestadosPorUsuario(SolicitudPrestarLibroRequest request) {

		if (String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.INVITADO.label.toString())) {
			List<Prestamo> list = prestamoRepositorio
					.buscarPrestamosActivosPorUsuario(request.getIdentificacionUsuario());
			if (list != null && !list.isEmpty()) {

				throw new InvitadoConUnPrestamoActivoException(
						String.format(USUARIO_INVITADO_CON_PRESTAMO, request.getIdentificacionUsuario()));
			}
		}

	}

	private Date calcularFechaDevolucion(SolicitudPrestarLibroRequest request, LocalDate fechaSolicitud) {
		LocalDate result = fechaSolicitud;
		int calculoDiasDevolucion = calcularDiasParaDevolucion(request);
		int addedDays = 0;
		while (addedDays < calculoDiasDevolucion) {
			result = result.plusDays(1);
			if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				++addedDays;
			}
		}
		return Date.from(result.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private int calcularDiasParaDevolucion(SolicitudPrestarLibroRequest request) {
		if (String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.AFILIADO.label.toString())) {
			return 10;
		} else if (String.valueOf(request.getTipoUsuario()).equals(TipoUsuario.EMPLEADO.label.toString())) {
			return 8;
		} else {
			return 7;
		}
	}

	@Override
	public ConsultarPrestamoLibroResponse consultarPrestamoLibros(Long id) {
		Prestamo cc = prestamoRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException(RECURSO_NO_ENCONTRADO));
		ConsultarPrestamoLibroResponse response = new ConsultarPrestamoLibroResponse();
		response.setFechaMaximaDevolucion(cc.getFechaMaximaDevolucion());
		response.setId(cc.getId());
		response.setIdentificacionUsuario(cc.getIdentificacionUsuario());
		response.setIsbn(cc.getIsbn());
		response.setTipoUsuario(cc.getTipoUsuario());
		return response;
	}

}
