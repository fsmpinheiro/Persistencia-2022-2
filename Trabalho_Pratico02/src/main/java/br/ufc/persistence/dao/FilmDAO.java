package br.ufc.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.persistence.entity.Film;

public interface FilmDAO extends JpaRepository<Film, Integer> {

	public List<Film> findByTitle( String title ); //nativa do spring
	
	@Query( "select f from Film f where lower(f.title) like lower( concat( '%', :trecho, '%' ) )")
	public List<Film> findByTitleLike(String trecho); //consulta JPQL

	@Query( "select f from Film f left join fetch f.actorsFilm where f.id = :id" )
	public Optional<Film> findByIdWithActorsFilm( int id ); //consulta JPQL

	public List<Film> findByReleaseYear( int parseInt );
	
}
