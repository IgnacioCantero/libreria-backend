package com.libreria.backend.models.services;

import com.libreria.backend.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
