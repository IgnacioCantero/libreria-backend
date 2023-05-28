package com.libreria.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.libreria.backend.models.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {
	
}
