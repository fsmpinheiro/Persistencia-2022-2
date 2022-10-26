package br.ufc.persistence.ui;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.persistence.dao.TurmaDAO;
import br.ufc.persistence.entity.Turma;

@Component
public class CRUDTurmas {
	
	@Autowired
	private TurmaDAO baseTurmas;
	
	public static void obterTurma( Turma tur ){
		String periodo = JOptionPane.showInputDialog("Periodo: ", tur.getPeriodo( ));
		String disciplina = JOptionPane.showInputDialog("Disciplina: ", tur.getDisciplina( ));
		
		tur.setPeriodo( periodo );	tur.setDisciplina( disciplina ); tur.setId( 0 );
	}
	
	public static void listarTurmas( List<Turma> turmasList ) {
		StringBuilder listagem = new StringBuilder( );
		for( Turma tur : turmasList ) {
			listagem.append(tur).append("\n");
		}
		JOptionPane.showMessageDialog( null, listagem.length() == 0 ? "Nenhuma Turma cadastrada" : listagem );
	}
	
	public static void editarTurma( Turma tur ) {
		String periodo = JOptionPane.showInputDialog("Periodo: ", tur.getPeriodo( ));
		String disciplina = JOptionPane.showInputDialog("Disciplina: ", tur.getDisciplina( ));
		
		tur.setPeriodo( periodo );	tur.setDisciplina( disciplina );
	}
	
	
	
	public void menu(  ) throws Exception{
		String menu = "Escolha uma opção:\n"
				+ "1 - Cadastrar turma\n"
				+ "2 - Editar turma\n"
				+ "3 - Exibir turmas cadastradas\n"
				+ "4 - Excluir turma por id\n"
				+ "5 - Sair";
		char opcao;
		
		do {
			Turma tur;
			String idStr;
			opcao = JOptionPane.showInputDialog(menu).charAt( 0 );
			
			switch( opcao ) {
			case '1': tur = new Turma( );
				      obterTurma( tur );
				      baseTurmas.save( tur );
				      break;
			case '2': idStr = JOptionPane.showInputDialog("Digite o ID da turma a ser editada");
					  tur = baseTurmas.findFirstById( Integer.parseInt( idStr ));
					  editarTurma( tur );
					  baseTurmas.save( tur );
					  break;
			case '3': listarTurmas( baseTurmas.findAll() );
					  break;
			case '4': idStr = JOptionPane.showInputDialog("ID da turma a excluir: ");
					  tur = baseTurmas.findFirstById( Integer.parseInt( idStr ));
					  if( tur != null ) {
						  baseTurmas.deleteById( tur.getId() );
					  } else {
						  JOptionPane.showMessageDialog(null, "Não foi possível remover turma, " + idStr + " não encontrado;");
					  }
					  break;
			case '5': break;
			default: JOptionPane.showMessageDialog( null, "Opção inválida" );
			  		  break;
			}
			
		} while( opcao != '5' );
	}
}
