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
	UsuarioRepository usuarioRepository;

	@Autowired
	MangaServices mangaService;

	
    public List<Manga> getFavoriteMangas(String username) {
        return usuarioRepository.findByUsername(username)
                .map(Usuario::getMangas)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<Manga> deleteFavoriteManga(String username, Integer mangaId) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Manga mangaToRemove = usuario.getMangas().stream()
                .filter(manga -> manga.getId().equals(mangaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Favorito no encontrado"));

        usuario.getMangas().remove(mangaToRemove);
        mangaToRemove.getUsuarios().remove(usuario);

        usuarioRepository.save(usuario);

        return usuario.getMangas();
    }

	public List<Manga> addFavoriteManga(String username, Integer mangaId) {
		Manga manga = mangaService.findById(mangaId);

		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

		if (usuario.getMangas().contains(manga)) {
			throw new RuntimeException("Favorito ya se encuentra registrado");
		}

		usuario.getMangas().add(manga);

		// Asegurarse de que el manga también tenga el usuario agregado a su lista de usuarios
        manga.getUsuarios().add(usuario);

        usuarioRepository.save(usuario);

		return getFavoriteMangas(username);
	}
}