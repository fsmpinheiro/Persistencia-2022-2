package br.ufc.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.persistence.entity.Turma;

@Repository
public interface TurmaDAO extends JpaRepository<Turma, Integer>{
	
	public Turma findFirstById( int id);

}
