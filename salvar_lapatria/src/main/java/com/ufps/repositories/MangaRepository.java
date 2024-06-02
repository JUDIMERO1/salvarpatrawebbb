package com.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.entitis.Manga;

public interface MangaRepository extends JpaRepository<Manga,  Integer> {

}
