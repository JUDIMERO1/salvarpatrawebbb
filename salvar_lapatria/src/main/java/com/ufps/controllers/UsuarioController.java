package com.ufps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.services.*;
import com.ufps.Utils.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	

	@Autowired
	private UsuarioServices usuariosService;

	@GetMapping("/{username}/favoritos")
	public ResponseEntity<?> getFavoriteMangasUser(@PathVariable String username) {
		try {
			return ResponseEntity.ok(usuariosService.getFavoriteMangas(username));
		} catch (RuntimeException e) {
			return ErrorResponseUtil.buildErrorResponse(e);
		} catch (Exception e) {
			return ErrorResponseUtil.buildErrorResponse("Error desconocido", e);
		}
	}

	@DeleteMapping("/{username}/favoritos/{mangaId}")
	public ResponseEntity<?> deleteFavoriteMangaUser(@PathVariable String username, @PathVariable Integer mangaId) {
		try {
			return ResponseEntity.ok(usuariosService.deleteFavoriteManga(username, mangaId));
		} catch (RuntimeException e) {
			return ErrorResponseUtil.buildErrorResponse(e);
		} catch (Exception e) {
			return ErrorResponseUtil.buildErrorResponse("Error desconocido", e);
		}
	}

	@PostMapping("/{username}/favoritos/{mangaId}")
	public ResponseEntity<?> addFavoriteMangaUser(@PathVariable String username, @PathVariable Integer mangaId) {
		try {
			return ResponseEntity.ok(usuariosService.addFavoriteManga(username, mangaId));
		} catch (RuntimeException e) {
			return ErrorResponseUtil.buildErrorResponse(e);
		} catch (Exception e) {
			return ErrorResponseUtil.buildErrorResponse("Error desconocido", e);
		}
	}
}
