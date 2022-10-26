package br.ufc.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.persistence.entity.AlunoTurma;

@Repository
public interface AlunoTurmaDAO extends JpaRepository<AlunoTurma, Integer>{

	List<AlunoTurma> findAllByAlunoId(int id);

	

}
