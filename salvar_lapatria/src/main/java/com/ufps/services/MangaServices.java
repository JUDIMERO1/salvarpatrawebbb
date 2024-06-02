package com.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.entitis.Manga;
import com.ufps.entitis.Pais;
import com.ufps.entitis.Tipo;
import com.ufps.models.MangaDTO;
import com.ufps.repositories.MangaRepository;
import com.ufps.repositories.PaisRepository;
import com.ufps.repositories.TipoRepository;


@Service
public class MangaServices {


	@Autowired
	private MangaRepository mangaRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private TipoRepository tipoRepository;

	public List<Manga> getAllMangas() {
		return mangaRepository.findAll();
	}

	public Optional<Manga> findById(Integer id) {
		return mangaRepository.findById(id);
	}

	public Manga createManga(MangaDTO mangaDTO) {
		Manga manga = new Manga();
		manga.setNombre(mangaDTO.getNombre());
		manga.setFechaLanzamiento(mangaDTO.getFechaLanzamiento());
		manga.setTemporadas(mangaDTO.getTemporadas());
		manga.setAnime(mangaDTO.getAnime());
		manga.setJuego(mangaDTO.getJuego());
		manga.setPelicula(mangaDTO.getPelicula());

		Pais pais = paisRepository.findById(mangaDTO.getPaisId())
				.orElseThrow(() -> new RuntimeException("Pais not found"));
		manga.setPais(pais);

		Tipo tipo = tipoRepository.findById(mangaDTO.getTipoId())
				.orElseThrow(() -> new RuntimeException("Tipo not found"));
		manga.setTipo(tipo);

		return mangaRepository.save(manga);
	}

	public Manga updateManga(Integer id, MangaDTO mangaDTO) throws Exception {
		Optional<Manga> optionalManga = mangaRepository.findById(id);

		if (!optionalManga.isPresent()) {
			throw new Exception("Manga not found");
		}

		Manga manga = optionalManga.get();
		manga.setNombre(mangaDTO.getNombre());

		Tipo tipo = tipoRepository.findById(mangaDTO.getTipoId())
				.orElseThrow(() -> new RuntimeException("Tipo not found"));
		manga.setTipo(tipo);

		Pais pais = paisRepository.findById(mangaDTO.getPaisId())
				.orElseThrow(() -> new RuntimeException("Pais not found"));
		manga.setPais(pais);

		return mangaRepository.save(manga);
	}

	public void deleteManga(Integer id) throws Exception {
		if (!mangaRepository.existsById(id)) {
			throw new Exception("Manga not found");
		}

		mangaRepository.deleteById(id);
	}
}
