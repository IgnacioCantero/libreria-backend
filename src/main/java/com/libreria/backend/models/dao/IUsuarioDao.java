package com.libreria.backend.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.libreria.backend.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	
}
