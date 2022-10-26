package br.ufc.persistence.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;

@Entity
@Table( name = "turmas")
@AllArgsConstructor
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String periodo;
	private String disciplina;
	
	@OneToMany( mappedBy = "turma", cascade = CascadeType.ALL )
	private List<AlunoTurma> turmas;
	
	
	public Turma( ){
	}
	
	public void setId( Integer id_ ) {
		this.id = id_;
	}
	
	public Integer getId( ) {
		return this.id;
	}
	
	public void setPeriodo( String periodo_ ){
		this.periodo = periodo_;
	}
	
	public String getPeriodo( ){
		return this.periodo;
	}
	
	public void setDisciplina( String disciplina_ ){
		this.disciplina = disciplina_;
	}
	
	public String getDisciplina( ){
		return this.disciplina;
	}
	
	public List<AlunoTurma> getTurmas( ){
		return this.turmas;
	}
	
	public void setTurmas( List<AlunoTurma> turmas_ ){
		this.turmas  = turmas_;
	}
	
	@Override
	public String toString( ){
		String tostr =  this.id + " - " + this.disciplina + " - " + this.periodo;
		return tostr;
	}

	
}
