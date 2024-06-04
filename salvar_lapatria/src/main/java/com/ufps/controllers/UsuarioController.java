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
    UsuarioServices usuarioServices;

	 @GetMapping("/{username}/favoritos")
	    public ResponseEntity<?> getFavoriteMangasUser(@PathVariable String username) {
	        ResponseEntity<?> response;
	        try {
	            response = ResponseEntity.ok(usuarioServices.getFavoriteMangas(username));
	        } catch (Exception e) {
	            response = ErrorResponseUtil.buildErrorResponse(e);
	        }
	        return response;
	    }

	 @DeleteMapping("/{username}/favoritos/{mangaId}")
	    public ResponseEntity<?> deleteFavoriteMangaUser(@PathVariable String username, @PathVariable Integer mangaId) {
	        ResponseEntity<?> response;
	        try {
	            response = ResponseEntity.ok(usuarioServices.deleteFavoriteManga(username, mangaId));
	        } catch (Exception e) {
	            response = ErrorResponseUtil.buildErrorResponse(e);
	        }
	        return response;
	    }
    
	 @PostMapping("/{username}/favoritos/{mangaId}")
	    public ResponseEntity<?> addFavoriteMangaUser(@PathVariable String username, @PathVariable Integer mangaId) {
	        ResponseEntity<?> response;
	        try {
	            response = ResponseEntity.ok(usuarioServices.addFavoriteManga(username, mangaId));
	        } catch (Exception e) {
	            response = ErrorResponseUtil.buildErrorResponse(e);
	        }
	        return response;
	    }
}
