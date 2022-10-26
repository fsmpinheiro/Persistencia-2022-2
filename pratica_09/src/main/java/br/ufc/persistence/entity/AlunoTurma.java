package br.ufc.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Entity
@Table( name = "alunos_turmas")
@AllArgsConstructor
public class AlunoTurma {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private Float nota_final;
	private Integer qtd_faltas;
	
	public AlunoTurma( ){
	}
	
	@ManyToOne
	@JoinColumn( name = "id_aluno", nullable = false )
	private Aluno aluno;
	
	public Aluno getAluno( ){	
		return this.aluno;
	}
	
	public void setAluno( Aluno aluno_ ){
		this.aluno = aluno_ ;
	}
	
	@ManyToOne
	@JoinColumn( name = "id_turma", nullable = false )
	private Turma turma;
	
	public Turma getTurma( ){
		return this.turma;
	}
	
	public void setTurma( Turma turma_ ){
		this.turma = turma_ ;
	}
	
	
	public int getId( ){
		return id;
	}
	
	public void setId( int id ){
		this.id = id;
	}
	
	
	public void setNotaFinal( float nota ){
		this.nota_final = nota;
	}
	
	public float getNotaFinal( ){
		return this.nota_final;
	}
	
	public void setQtdFaltas( Integer value ){
		this.qtd_faltas = value;
	}
	
	public Integer getQtdFaltas( ) {
		return this.qtd_faltas;
	}
	
	@Override
	public String toString( ) {
		String result = this.getTurma() + " Faltas: " + this.qtd_faltas + " Nota: " + this.nota_final ;
		return result;
	}
}
