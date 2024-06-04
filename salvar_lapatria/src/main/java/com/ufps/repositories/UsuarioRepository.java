package com.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.ufps.entitis.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);

}
