package com.ufps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.entitis.Manga;
import com.ufps.services.UsuarioServices;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioService;

    @GetMapping("/{nombreUsuario}/favoritos")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable String nombreUsuario) {
        try {
            List<Manga> mangasFavoritos = usuarioService.obtenerMangasFavoritos(nombreUsuario);
            return ResponseEntity.ok(mangasFavoritos);
        } catch (Error e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }


}