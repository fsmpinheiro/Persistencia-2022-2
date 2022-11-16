package br.ufc.persistence.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.persistence.dao.ActorDAO;
import br.ufc.persistence.dao.FilmDAO;
import br.ufc.persistence.entity.Actor;
import br.ufc.persistence.entity.Film;

//@ComponentScan("br.ufc.persistence")
@Component
public class CRUDActor{
	
	@Autowired
	private ActorDAO actorRepository;
	
	@Autowired
	private FilmDAO  filmRepository;


	public static void buildActor( Actor ator ) {
		String nome = JOptionPane.showInputDialog( "Nome do Ator", ator.getName( ) );
		String strBirthDate = JOptionPane.showInputDialog("Nascimento do ator\ndia/mês/ano", ator.getbirthDate( ) );
		
		DateTimeFormatter brDatePattern = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
		LocalDate birthDate = LocalDate.parse( strBirthDate, brDatePattern );
		
		ator.setName( nome ); ator.setbirthDate( birthDate ); ator.setId(0);
	}
	
	public static void editActor( Actor ator ) {
		if( ator == null ) {
			JOptionPane.showConfirmDialog(null, "Nenhum Ator encontrado", "Error, not found...", JOptionPane.DEFAULT_OPTION );
		} else {
			String nome = JOptionPane.showInputDialog("Nome", ator.getName() );
			String strBirthDate = JOptionPane.showInputDialog("Nascimento do ator\ndia/mês/ano", ator.getbirthDateStr() );
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
			LocalDate birthDate = LocalDate.parse( strBirthDate, formatter );
			
			ator.setName( nome ); ator.setbirthDate( birthDate );
		}
	}
	
	public static void listAnActor( Actor ator ) {
		JOptionPane.showMessageDialog(null, ator == null ? "Nenhum Ator encontrado" : ator );
	}
	
	public static void listAllActors( List<Actor> atorList ) {
		StringBuilder listagem = new StringBuilder( );
		for( Actor ator : atorList ) {
			listagem.append(ator).append("\n");
		}
		JOptionPane.showMessageDialog( null, listagem.length() == 0 ? "Nenhum Ator cadastrado" : listagem );
	}
	
	

	public void menu( ) throws Exception {
		String menu = "Menu de Atores - Escolha uma opção:\n"
				+ "1 - Inserir Ator\n"
				+ "2 - Listar Ator por Id\n"
				+ "3 - Listar Ator por nome\n"
				+ "4 - Listar Atores cadastrados\n"
				+ "5 - Editar um Ator cadastrado\n"
				+ "6 - remover um Ator cadastrado\n"
				+ "7 - Associar Ator a Filme\n"
				+ "8 - Voltar\n"
				+ "";
				
		char opcao;
		
		do { 
			Actor atr;
			Film flm;
			int id;
			opcao = JOptionPane.showInputDialog(menu).charAt(0); 
			
			switch( opcao ) {
			case '1' : atr = new Actor( );
					   buildActor( atr );
					   actorRepository.save( atr );
					   break;
			
			case '2' : id = Integer.parseInt( JOptionPane.showInputDialog("Id do ator: ") );
					   atr = actorRepository.findById( id ).orElse( null );
					   listAnActor( atr );
					   break;
					   
			case '3' : String name = JOptionPane.showInputDialog( "Informe o nome de quem busca: " );
					   listAllActors( actorRepository.findByName( name ) );
					   break;
					   
			case '4' : listAllActors( actorRepository.findAll() );
					   break;
			case '5' : id = Integer.parseInt( JOptionPane.showInputDialog("Id do ator: ") );
					   atr = actorRepository.findById( id ).orElse( null );
					   editActor( atr );
					   if( atr != null) {
						   actorRepository.save( atr );
					   }
					   break;
			case '6' : id = Integer.parseInt( JOptionPane.showInputDialog("Id do ator: ") );
					   atr = actorRepository.findById( id ).orElse( null );
					   if( atr != null ) {
						   actorRepository.deleteById( atr.getId() );
						   JOptionPane.showConfirmDialog( null, "Ator removido com sucesso", "Delete sucessefull...", JOptionPane.DEFAULT_OPTION );
					   } else {
						   JOptionPane.showConfirmDialog( null, "Não foi possível remover, pois"
						   		+ " o ator não foi encontrado", "Error, not found...", JOptionPane.DEFAULT_OPTION );
					   }
					   break;
			case '7' : id = Integer.parseInt( JOptionPane.showInputDialog( "Id do Ator: " ) );
					   atr = actorRepository.findById( id ).orElse( null );
					   if( atr == null ) {
							JOptionPane.showMessageDialog( null, "Ator não encontrado");
							break;
						}
						id = Integer.parseInt( JOptionPane.showInputDialog( "Id do Filme: " ) );
						flm = filmRepository.findByIdWithActorsFilm( id ).orElse( null );
						if( flm == null ) {
							JOptionPane.showMessageDialog( null, "Filme não encontrado");
						}
						flm.getListActors( ).add( atr );
						filmRepository.save( flm );
						break;
					   
			case '8' : break;
			}
		} while( opcao != '8' );
		
	}
	
	
	
}
