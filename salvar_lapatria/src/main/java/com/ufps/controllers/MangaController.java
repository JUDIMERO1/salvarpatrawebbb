package com.ufps.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufps.entitis.Manga;
import com.ufps.models.MangaDTO;
import com.ufps.services.MangaServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mangas")
public class MangaController {

	@Autowired
	private MangaServices mangaService;

	@GetMapping("/status")
	public Map<String, Object> getStatus() {
		Map<String, Object> status = new HashMap<>();
		status.put("message", "Servidor en funcionamiento");
		status.put("timestamp", LocalDateTime.now());
		return status;
	}

	@GetMapping
	public List<Manga> getAllMangas() {
		return mangaService.getAllMangas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMangaById(@PathVariable Integer id) {

		Manga manga = mangaService.findById(id);
		return ResponseEntity.ok(manga);

	}

	@PostMapping
	public Manga createManga(@RequestBody MangaDTO mangaDTO) {
		return mangaService.createManga(mangaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateManga(@PathVariable Integer id, @RequestBody MangaDTO mangaDetails) {
		try {
			Manga updatedManga = mangaService.updateManga(id, mangaDetails);
			return ResponseEntity.ok(updatedManga);
		} catch (Exception e) {
			Map<String, Object> error = new HashMap<>();
			error.put("error", true);
			error.put("msg", e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteManga(@PathVariable Integer id) {
		try {
			mangaService.deleteManga(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			Map<String, Object> error = new HashMap<>();
			error.put("error", true);
			error.put("msg", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
	}

}