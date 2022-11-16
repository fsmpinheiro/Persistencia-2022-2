package br.ufc.persistence.pratico02;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.ufc.persistence.crud.CRUDActor;
import br.ufc.persistence.crud.CRUDFilm;
import br.ufc.persistence.dao.ActorDAO;
import br.ufc.persistence.dao.FilmDAO;
import br.ufc.persistence.entity.Actor;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@SpringBootApplication
@EntityScan("br.ufc.persistence.entity")
@ComponentScan("br.ufc.persistence")
@EnableJpaRepositories("br.ufc.persistence.dao")
public class TrabalhoPratico02Application implements CommandLineRunner{
	
	@Autowired
	private CRUDFilm 	crFilmes;
	@Autowired
	private CRUDActor 	crAtores;
	
	@Autowired
	private FilmDAO 	filmRepository;
	@Autowired
	private ActorDAO	actorRepository;

	public static void main( String[] args ) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder( TrabalhoPratico02Application.class ); 
		
		
		builder.headless(false).run(args).close();
		
	}
	
	private Actor chooseActor( List<Actor> atores ) {
		Actor atorEscolhido = new Actor( );
		StringBuilder listagem = new StringBuilder( );
		for( Actor ator : atores ) {
			listagem.append( ator.getId( ) ).append( " - " ).append( ator.getName( ) ).append( "\n" );
		}
		if( listagem.length() != 0 ) {
			String id = JOptionPane.showInputDialog( "Encontrados ( Escolha pelo id ):\n" + listagem );
			for( Actor ator : atores ) {
				if ( ator.getId() == Integer.parseInt( id ) ) {
					atorEscolhido = ator;
					return atorEscolhido;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum ator encontrado...");
		}
				
		return atorEscolhido;
	}

	
	private static void listAllActors( List<Actor> atores ) {
		StringBuilder listagem = new StringBuilder( );
		for( Actor ator : atores ) {
			listagem.append( ator ).append( "\n");
		}
		JOptionPane.showMessageDialog( null, listagem.length( ) == 0 ? "Nenhum ator nascido nessa \nano está cadastrado" : listagem );
	}
	
	
	@Override
	public void run(String... args) throws Exception{
		String menu = "Escolha uma opção:\n"
				+ "1 - Menu de Atores\n"
				+ "2 - Menu de Filmes\n"
//				+ "3 - Listar Filmes de um Ator\n"
//				+ "4 - Listar Atores de um Filme\n"
				+ "5 - Listar Filmes do ano: \n"
				+ "6 - Listar Filmes por string:\n"
				+ "7 - Listar Atores nascidos no ano:\n"
				+ "8 - Mostrar qtd filmes cadastrados\n"
				+ "9 - Sair\n";
		
		char opcao;
		
		do {
			String ano;
			Actor atr;
			opcao = JOptionPane.showInputDialog(menu).charAt(0);
			
			switch( opcao ) {
				case '1' :	crAtores.menu( );
							break;
				case '2' :	crFilmes.menu( );
							break;
				case '3' :	ano = JOptionPane.showInputDialog(null, "Digite o nome ou parte dele:" );
							atr = chooseActor( actorRepository.findByNameLike( ano ) );
							if( atr == null ) {
								break;
							} else {
								StringBuilder listagem = new StringBuilder( ); 
								listagem.append( "Filmes de " + atr.getName() + ":\n");
								
//								actorRepository.findByIdFilms( atr.getId() );
								JOptionPane.showMessageDialog(null, listagem  );
							}
							break;
							
//				case '4' :	
//							break;
				case '5' :	ano = JOptionPane.showInputDialog( "Digite o ano para pesquisa:");
							crFilmes.listAllFilms( filmRepository.findByReleaseYear( Integer.parseInt(ano) ) );
							ano = "";
							
							break;
				case '6' :	String trech = JOptionPane.showInputDialog( "Digite uma string pra pesquisar filmes:" );
							crFilmes.listAllFilms( filmRepository.findByTitleLike( trech) );
				
							break;
				case '7' :	ano = JOptionPane.showInputDialog( "Digite o ano para pesquisa:");

							LocalDate instance = LocalDate.now().withYear( Integer.parseInt( ano) );
							LocalDate dateStart = instance.withMonth(01);
							LocalDate dateEnd = instance.withMonth(12);
							listAllActors( actorRepository.findAllByBirthDateBetween( dateStart, dateEnd) );
				
							break;
				case '8' :	int qtd = filmRepository.findAll( ).size( );
							JOptionPane.showMessageDialog(null, "Existem " + qtd + " Filmes cadastrados no banco;");
							break;
				case '9' : 	break;
				default : JOptionPane.showMessageDialog(null, "Opção invalida" );
						break;
			}
		} while ( opcao != '9');
	}

}
