package br.ufc.persistence.crud;

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
//public class CRUDFilm implements CommandLineRunner{
public class CRUDFilm {
	
	@Autowired
	private FilmDAO  filmRepository;
	@Autowired
	private ActorDAO actorRepository;

	public static void buildFilme( Film filme ) {
		String title = JOptionPane.showInputDialog( "Título do Filme: ", filme.getTitle( ) );
		String year  = JOptionPane.showInputDialog( "Ano de lançamento do Filme: ", filme.getReleaseYear( ) );
				
		filme.setTitle( title ); filme.setReleaseYear( Integer.parseInt( year ) ); filme.setId( 0 );
	}
	
	public static void editFilm( Film film ) {
		if( film == null ) {
			JOptionPane.showConfirmDialog(null, "Nenhum Filme encontrado", "Error, not found...", JOptionPane.DEFAULT_OPTION );
		} else {
			String title = JOptionPane.showInputDialog( "Título do FIlme: ", film.getTitle( ) );
			String year  = JOptionPane.showInputDialog( "Ano de lançamento do Filme: ", film.getReleaseYear( ) );
			
			film.setTitle( title ); film.setReleaseYear( Integer.parseInt( year ) );
		}
	}
	
	public void listAnFilm( Film film ) {
		JOptionPane.showMessageDialog(null, film == null ? "Nenhum filme com esse id encontrado" : film );		
	}
	
	public void listAllFilms( List<Film> filmList ) {
		StringBuilder listagem = new StringBuilder( );
		for( Film flm : filmList ) {
			listagem.append( flm ).append("\n");
		}
		JOptionPane.showMessageDialog(null,  listagem.length() == 0 ? "Nenhum Filme cadastrado" : listagem );
	}
	
	
	
	public void menu( ) throws Exception {
		String menu = "Menu de Filmes - Escolha uma opção:\n"
				+ "1 - Inserir um Filme\n"
				+ "2 - Listar Filme por Id\n"
				+ "3 - Listar Filme por nome\n"
				+ "4 - Listar Filmes cadastrados\n"
				+ "5 - Editar um Filme cadastrado\n"
				+ "6 - Remover um Filme cadastrado\n"
				+ "8 - Voltar\n"
				+ "";
		
		char opcao;
		do {
			Film flm;
			Actor atr;
			int id;
			
			opcao = JOptionPane.showInputDialog(menu).charAt(0);
			
			switch( opcao ) {
			case '1' :  flm = new Film( );
						buildFilme( flm );
						filmRepository.save( flm );
						break;
			case '2' :	id = Integer.parseInt( JOptionPane.showInputDialog("Id do Filme: ") );
						flm = filmRepository.findById( id ).orElse( null );
						listAnFilm( flm );
						break;
			case '3' :	String name = JOptionPane.showInputDialog("Informe o nome do filme que busca: ");
						listAllFilms( filmRepository.findByTitle( name ) );
						break;
			case '4' :	listAllFilms( filmRepository.findAll() );
						break;
			case '5' :	id = Integer.parseInt( JOptionPane.showInputDialog( "Id do filme: ") );
						flm = filmRepository.findById( id ).orElse( null );
						editFilm( flm );
						if( flm != null ) {
							filmRepository.save( flm );
						}
						break;
			case '6' :	id = Integer.parseInt( JOptionPane.showInputDialog( "Id do Filme: " ) );
						flm = filmRepository.findById( id ).orElse( null );
						if( flm != null ) {
							filmRepository.deleteById( flm.getId( ) );
							JOptionPane.showConfirmDialog(null, "Filme removido com sucesso", "Delete sucessefull...", JOptionPane.DEFAULT_OPTION );
						} else {
							JOptionPane.showConfirmDialog(null, "Não foi possível remover, pois"
									+ " o filme não foi encontrado", "Error, not found...", JOptionPane.DEFAULT_OPTION );
						}
						break;

			case '8' :	break;
			}
		
		} while( opcao != '8' );
	}
	
}
