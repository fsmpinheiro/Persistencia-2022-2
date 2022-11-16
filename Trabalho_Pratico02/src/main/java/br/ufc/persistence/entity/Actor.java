package br.ufc.persistence.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table( name = "atores" )
@AllArgsConstructor
public class Actor {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String name;
	private LocalDate birthDate;
	
	@ManyToMany( mappedBy = "actorsFilm", fetch=FetchType.LAZY )
	private Set<Film> films;
	
	public Actor( ) {
	}
	

	public int getId( ){
		return this.id;
	}
	
	public void setId( int id_ ){
		this.id = id_;
	}
	
	public String getName( ){
		return this.name;
	}
	
	public void setName( String name_ ){
		this.name = name_;
	}
	
	public String getbirthDateStr( ){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
		return this.birthDate.format(formatter);
	}
	
	public LocalDate getbirthDate( ) {
		return this.birthDate;
	}
	
	
	public void setbirthDate( LocalDate date_ ){
		this.birthDate = date_;
	}
	
	public Set<Film> getListFilms( ){
		return this.films;
	}
	
	public void setListFilms( Set<Film> listFilms_ ){
		this.films = listFilms_;
	}
	
	@Override
	public String toString( ){
		String result = "ID: " + getId( ) + " - " + getName( ) + ", " + getbirthDateStr( );
		return result;
	}
}
