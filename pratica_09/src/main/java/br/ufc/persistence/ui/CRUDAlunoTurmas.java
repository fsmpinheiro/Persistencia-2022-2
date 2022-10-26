package br.ufc.persistence.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import br.ufc.persistence.dao.AlunoDAO;
import br.ufc.persistence.dao.AlunoTurmaDAO;
import br.ufc.persistence.dao.TurmaDAO;
import br.ufc.persistence.entity.Aluno;
import br.ufc.persistence.entity.AlunoTurma;
import br.ufc.persistence.entity.Turma;

@Component
public class CRUDAlunoTurmas {
	
	@Autowired
	private TurmaDAO baseTurmas;
	@Autowired
	private AlunoDAO baseAlunos;
	@Autowired
	private AlunoTurmaDAO baseAlunoTurma;
	
	
	
	public void buildAlunoTurma( AlunoTurma setAlTu , Aluno alu, Turma tur ) {
		setAlTu.setAluno( alu );
		setAlTu.setTurma( tur );
		setAlTu.setNotaFinal( 0 ); setAlTu.setQtdFaltas( 0 ); setAlTu.setId( 0 );
	}
	
	public void editAlunoTurma( ) {
		
	}
	public void listarAlunoTurmas( List<AlunoTurma> list_AT ) {
		StringBuilder listagem = new StringBuilder( );
		for( AlunoTurma altur : list_AT ) {
			listagem.append( altur.getAluno( ).getNome( ) + " - " )
					.append( altur.getAluno( ).getMatricula( ) + " - "  )
					.append( altur.getTurma( ).getDisciplina( ) + " - "  )
					.append( altur.getTurma( ).getPeriodo( ) + " " )
					.append( "\n" );
		}
		JOptionPane.showMessageDialog( null, listagem.length() == 0 ? "Nenhuma associação Aluno-Turma cadastrada" : listagem );
	}
	
	
	
	
	public void alunoInsertSetAlTu( AlunoTurma setAlTu , Aluno alu ) {
//		List<AlunoTurma> listTurmas = alu.getTurmas( );
//		if ( listTurmas.isEmpty() ) {
//			System.out.println( "entrou");
//		}
		List<AlunoTurma> listTurmas = new ArrayList<AlunoTurma>( );
		listTurmas.add( setAlTu );
		alu.setTurmas( listTurmas );
	}
	
	public void turmaInsertSetAlTu( AlunoTurma setAlTu , Turma tur ) {
		List<AlunoTurma> listTurmas = new ArrayList<AlunoTurma>( );
		listTurmas.add( setAlTu );
		tur.setTurmas( listTurmas );
	}
	 
	public void listTurmasDoAlunos( Aluno alu ){
		StringBuilder listagem = new StringBuilder( );
		listagem.append( alu.getNome() + " - " + alu.getMatricula( ) ).append( "\n" );
		
		List<AlunoTurma> listurmas = baseAlunoTurma.findAllByAlunoId( alu.getId() );  
		
		for( AlunoTurma altur : listurmas ) {
			listagem.append( altur.getTurma( ).getDisciplina( ) + " - " + altur.getTurma( ).getPeriodo( ) + "\n" );
		}
		JOptionPane.showMessageDialog( null, listagem.length() == 0 ? "Aluno não tem associação Aluno-Turma cadastrada" : listagem );
	}
	
	public void listAlunosDaTurma( Turma tur ) {
		
	}
	
	public void menu(  ) throws Exception{ 
		String menu = "Escolha uma opção:\n"
				+ "1 - associar aluno a turma\n"
				+ "2 - listar turmas do aluno\n"
				+ "3 - listar todas as associações\n"
				+ "5 - sair";
		char opcao;
		
		do {
			AlunoTurma setAluTur;
			Aluno alu;
			String idStr;
			opcao = JOptionPane.showInputDialog(menu).charAt( 0 );
			
			switch( opcao ) {
			case '1': idStr = JOptionPane.showInputDialog("Digite o ID do Aluno pra vincula a turma:" );
					  alu = baseAlunos.findById( Integer.parseInt( idStr ) ).orElse( null );
					  if( alu == null ) {
						  JOptionPane.showMessageDialog(null, "Aluno não encontrado");
						  break;
					  }
				
					  idStr = JOptionPane.showInputDialog("Digite o ID da turma pra vinculada ao aluno" );
					  Turma tur = baseTurmas.findFirstById( Integer.parseInt( idStr ) );
					  if( tur == null ) {
						  JOptionPane.showMessageDialog(null, "Turma não encontrada");
						  break;
					  }
	
					  setAluTur = new AlunoTurma( );
				      buildAlunoTurma( setAluTur, alu, tur );
					    if( setAluTur != null ) {
						    baseAlunoTurma.save( setAluTur );
						    alunoInsertSetAlTu( setAluTur, alu );						    
					  }
					  alu = null;
					  tur = null;
					  break;   
					  
			case '2': idStr = JOptionPane.showInputDialog("ID do aluno pra listar suas turmas: ");
					  alu = baseAlunos.findById( Integer.parseInt( idStr ) ).orElse( null );
					  if( alu == null ) {
					    	JOptionPane.showMessageDialog(null, "Aluno não encontrado");
						    break;
				  	  }
					  listTurmasDoAlunos( alu );
					  break;
					
			case '3': listarAlunoTurmas( baseAlunoTurma.findAll() );
					  break;
					  
			case '5': break;
			default: JOptionPane.showMessageDialog( null, "Opção inválida" );
			  		  break;
			}
		
		} while( opcao != '5' );
	}
}
