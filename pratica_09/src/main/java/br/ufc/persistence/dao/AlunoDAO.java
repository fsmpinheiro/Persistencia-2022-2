package br.ufc.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.persistence.entity.Aluno;

@Repository
public interface  AlunoDAO extends JpaRepository<Aluno, Integer>{
	
	public Aluno findFirstByCpf( String cpf );
	
	@Query( "select a from Aluno a join fetch a.turmas at join fetch at.turma where a.id = :id")
	public Optional<Aluno> findByIdWithTurma( int id );
	
	public Aluno findFirstByMatricula( String matricua );

	@Query("select c from Aluno c where c.nome like :nome%")
	public List<Aluno> buscaPorNomeIniciadoCom(String nome);
	
	
}
