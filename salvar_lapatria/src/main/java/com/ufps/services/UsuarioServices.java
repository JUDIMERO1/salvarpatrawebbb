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

	@Autowired
    private MangaServices mangaService;

    public List<Manga> getFavoriteMangas(String username) {
        Usuario usuario = usuarioRepository.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getMangas();
    }

    public List<Manga> deleteFavoriteManga(String username, Integer mangaId) {
        Usuario usuario = usuarioRepository.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Manga mangaToRemove = usuario.getMangas().stream()
                .filter(manga -> manga.getId().equals(mangaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Manga favorito no encontrado"));

        usuario.getMangas().remove(mangaToRemove);

        
        mangaToRemove.getUsuarios().remove(usuario);

        usuarioRepository.save(usuario);

        return usuario.getMangas();
    }

    public List<Manga> addFavoriteManga(String username, Integer mangaId) {
        Manga manga = mangaService.findById(mangaId);

        Usuario usuario = usuarioRepository.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getMangas().contains(manga)) {
            throw new RuntimeException("Manga favorito ya se encuentra registrado");
        }

        usuario.getMangas().add(manga);

        
        manga.getUsuarios().add(usuario);

        usuarioRepository.save(usuario);

        return usuario.getMangas();
    }
}

