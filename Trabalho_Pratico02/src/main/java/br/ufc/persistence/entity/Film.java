package br.ufc.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table( name = "filmes")
@AllArgsConstructor
public class Film {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String title;
	private int releaseYear;
	
	@ManyToMany( fetch=FetchType.LAZY ) 
	@JoinTable( name = "filme_atores_table", 
			  joinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
	private Set<Actor> actorsFilm;

	public Film( ) {		
	}

	
	public Integer getId( ){
		return id;
	}

	public void setId( int  id_){
		this.id = id_;
	}

	public String getTitle( ){
		return title;
	}

	public void setTitle( String title_ ){
		this.title = title_;
	}

	public int getReleaseYear( ){
		return releaseYear;
	}

	public void setReleaseYear( int releaseYear_ ){
		this.releaseYear = releaseYear_;
	}

	public Set<Actor> getListActors( ){
		return actorsFilm;
	}

	public void setListActors( Set<Actor> actorsList ){
		this.actorsFilm = actorsList;
	}
	
	@Override
	public String toString( ) {
		String result = this.title + " [" + this.releaseYear + "]  - ID: " + this.id;
		return result;
	}
	
}
