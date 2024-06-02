package com.ufps.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.entitis.Manga;
import com.ufps.entitis.Usuario;
import com.ufps.repositories.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	
	public List<Manga> obtenerMangasFavoritos(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombre(nombreUsuario);
        if (usuario == null) {
            
        }
        return usuario.mangas;
    }

}
