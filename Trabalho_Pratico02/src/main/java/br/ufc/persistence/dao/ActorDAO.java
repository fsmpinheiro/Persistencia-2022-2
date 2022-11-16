package br.ufc.persistence.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.persistence.entity.Actor;

public interface ActorDAO extends JpaRepository<Actor, Integer>{

	public List<Actor> findByName( String name );
	
	@Query( "Select a from Actor a where lower( a.name ) like lower( concat( '%', :name, '%' ) ) ")
	public List<Actor> findByNameLike ( String name );

	public List<Actor> findAllByBirthDateBetween( LocalDate dateStart, LocalDate dateEnd );
	
//	@Query( "select f from Film f right join f.actorsFilm " )
//	public Optional<Film> findByIdWithActorsFilm( int id );
	
//	public List<Film> findByIdWithFilms( int id );

}
