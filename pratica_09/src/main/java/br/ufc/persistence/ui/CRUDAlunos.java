package br.ufc.persistence.ui;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.persistence.dao.AlunoDAO;
import br.ufc.persistence.entity.Aluno;


//@ComponentScan("br.ufc.persistence")
@Component
public class CRUDAlunos {

	@Autowired
	private AlunoDAO baseAlunos;
	
	public static void obterAluno( Aluno alu ) {
		String nome = JOptionPane.showInputDialog("Nome", alu.getNome() );
		String cpf = JOptionPane.showInputDialog("CPF", alu.getCpf() );
		String matricula = JOptionPane.showInputDialog("Matricula", alu.getMatricula() );
		String email = JOptionPane.showInputDialog("Email", alu.getEmail() );
		String telefone = JOptionPane.showInputDialog("Telefone", alu.getTelefone() );
		
		alu.setNome(nome); alu.setCPF(cpf); alu.setMatricula(matricula);
		alu.setEmail(email); alu.setTelefone(telefone); alu.setId( 0 );
	}
	
	public static void editarAluno( Aluno alu ) {
		String nome = JOptionPane.showInputDialog("Nome", alu.getNome() );
		String cpf = JOptionPane.showInputDialog("CPF", alu.getCpf() );
		String matricula = JOptionPane.showInputDialog("Matricula", alu.getMatricula() );
		String email = JOptionPane.showInputDialog("Email", alu.getEmail() );
		String telefone = JOptionPane.showInputDialog("Telefone", alu.getTelefone() );
		
		alu.setNome(nome); alu.setCPF(cpf); alu.setMatricula(matricula);
		alu.setEmail(email); alu.setTelefone(telefone);
		
	}
	
	public static void listaAlunos( List<Aluno> alunosList ) {
		StringBuilder listagem = new StringBuilder( );
		for ( Aluno al : alunosList ) {
			listagem.append(al).append("\n");
		}
		JOptionPane.showMessageDialog( null, listagem.length() == 0 ? "Nenhum Aluno encontrado" : listagem );
	}
	
	public static void ListaAluno( Aluno al ) {
		System.out.println( al.getTurmas() );
		JOptionPane.showMessageDialog( null, al == null ? "Nenhum Aluno encontrato" : al );
	}
	
	public void menu(  ) throws Exception{
		String menu = "Escolha uma opção:\n"
				+ "1 - Inserir\n"
				+ "2 - Atualizar por CPF\n"
				+ "3 - Remover por CPF\n"
				+ "4 - Exibir por CPF\n"
				+ "5 - Exibir por id\n"
				+ "6 - Exibir todos\n"
				+ "7 - Exibir todos que contem determinado nome\n"
				+ "8 - Sair";
		char opcao;
		
		do { 
			Aluno al;
			String cpf;
			opcao = JOptionPane.showInputDialog(menu).charAt(0);
			
			switch( opcao ) {
			case '1': al = new Aluno( );
					  obterAluno( al );
					  baseAlunos.save( al );
					  break;
			case '2': cpf = JOptionPane.showInputDialog("Digite o CPF do aluno a ser alterado");
					  al = baseAlunos.findFirstByCpf( cpf );
					  editarAluno( al );
					  baseAlunos.save( al );
					  break;
			case '3': cpf = JOptionPane.showInputDialog("CPF");
					  al = baseAlunos.findFirstByCpf( cpf );
					  if( al != null ) {
						  baseAlunos.deleteById( al.getId() );
					  } else {
						  JOptionPane.showMessageDialog( null, "Não foi possível remover, pois o aluno não foi encontrado");
					  }
					  break;
			case '4': cpf = JOptionPane.showInputDialog("CPF");
					  al = baseAlunos.findFirstByCpf( cpf );
					  ListaAluno( al );
					  break;
			case '5': int id = Integer.parseInt( JOptionPane.showInputDialog("Id") );
					  al = baseAlunos.findByIdWithTurma( id ).orElse(null);
					  ListaAluno( al );
					  break;
			case '6': listaAlunos( baseAlunos.findAll() );
					  break;
			case '7': String nome = JOptionPane.showInputDialog("Nome");
					  listaAlunos( baseAlunos.buscaPorNomeIniciadoCom( nome ));
					  break;
			case '8': break;
			
			default: JOptionPane.showMessageDialog( null, "Opção inválida" );
					  break;
			}
			
		
		} while(opcao != '8');
	}
	
}
