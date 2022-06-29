package com.ceiba.biblioteca.repositorio;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.biblioteca.entidad.Prestamo;

@Repository
public interface BibliotecaRepositorio extends CrudRepository<Prestamo, Long>{
	
	List<Prestamo> buscarPrestamosActivosPorLibro(@Param("isbn") String isbn);

	List<Prestamo> buscarPrestamosActivosPorUsuario(@Param("identificacionUsuario") String identificacionUsuario);

}
