package br.ufc.persistence.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;

@Entity
@Table( name = "alunos" )
@AllArgsConstructor
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String cpf;
	private String matricula;
	private String nome;
	private String email;
	private String telefone;
	
	@OneToMany( mappedBy = "aluno", cascade = CascadeType.ALL )
	private List<AlunoTurma> turmas;
	
	public Aluno( ){
	}
	
	public int getId( ){
		return id;
	}
	
	public void setId( int id ){
		this.id = id;
	}
	
	public String getCpf( ){
		return cpf;
	}
	
	public void setCPF( String cpf ){
		this.cpf = cpf;
	}
	
	public String getMatricula( ){
		return matricula;
	}
	
	public void setMatricula( String matrícula ){
		this.matricula = matrícula;
	}
	
	public String getNome( ){
		return nome;
	}
	
	public void setNome( String nome ){
		this.nome = nome;
	}
	
	public String getEmail( ){
		return email;
	}
	
	public void setEmail( String email ){
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone( String telefone ){
		this.telefone = telefone;
	}
	
	public List<AlunoTurma> getTurmas( ){
		return this.turmas;
	}
	
	public void setTurmas( List<AlunoTurma> turmas ){
		this.turmas  = turmas;
	}

	@Override
	public String toString( ){
		String result =  this.id + " - " + "Aluno: " + this.nome + " CPF: " + 
						 this.cpf + " Matrícula: " + this.matricula + " " +
						 this.telefone + " " + this.email;
		return result;
	}

}
