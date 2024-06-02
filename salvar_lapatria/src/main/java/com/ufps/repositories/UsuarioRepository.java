package com.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ufps.entitis.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByNombre(String nombre);

}
