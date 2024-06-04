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
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Manga manga = mangaService.findById(mangaId);

        if (usuario.getMangas().stream().anyMatch(m -> m.getId().equals(mangaId))) {
            throw new RuntimeException("Manga favorito ya se encuentra registrado");
        }

        usuario.getMangas().add(manga);
        manga.getUsuarios().add(usuario);

        usuarioRepository.save(usuario);

        return usuario.getMangas();
    }
}