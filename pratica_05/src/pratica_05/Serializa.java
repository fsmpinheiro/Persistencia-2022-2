package pratica_05;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Serializa {

	private List<ElementObject> objectsList;	
	
	public Serializa( ) {
		this.objectsList = new ArrayList<ElementObject>();
	}
	
	public void setElementObject(ElementObject elObj, String elTitle, String elYear ) {
		elObj.setName( elTitle );
		int realeaseDate = Integer.parseInt( elYear );
		elObj.setYear( realeaseDate );
	}
//	
//	DOOONE
//	
	
	
	public void buildCastList( ElementObject object, Scanner scann ) {
		
		if( object.getCastList() == null) {
			object.setFreshCastList();
		}
		
		boolean done = false;
		String actorName = "";
		int count = 0;
		
		System.out.println("Nome do Ator/ da Atriz");
		while ( !done ) {
			
			if( count > 0) {
				System.out.println("Mais alguém? [ Caso não, digite: exit ]");
			}
			actorName = scann.nextLine();
			if( actorName.toLowerCase().equals("exit") ) {
				done = true;
			}else {
				object.addActor(  new Actor( actorName )  );
				count = count + 1;
			}
		}
	}
	
	
	
	
	private void saveSerializedList( List<ElementObject> objList ) {
		
		try {
			
			File listFile = new File( "elementList.JSON" );
			
			String jsonString = new ObjectMapper().writeValueAsString( objList );
			
			System.out.println( jsonString );
			
		} catch ( IOException e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		Serializa makeSerializeJSON = new Serializa();
		
		Scanner scann = new Scanner(System.in);
		boolean done = false;
		int count = 0;
		String kind = "", title = "", year = "", hasCast = "" ;
		
		while( !done ) {
			System.out.println( "Se quiser encerrar digite exit" );
			
			if( count == 0 ) {
				System.out.println("Informe o tipo de objeto: Filme / Serie");
				kind = scann.nextLine();
				
				if( kind.equalsIgnoreCase("exit") ) {
					done = true; count = 5;
				}else {
					count = 1;
				}
			}
			if( count == 1 ) {
				System.out.println("Informe o título: ");
				title = scann.nextLine();
				
				if ( title.equalsIgnoreCase("exit") ) {
					done = true; count = 5;
				}else {
					count = 2;
				}
				
			}
			if( count == 2 ) {
				System.out.println("Informe o ano de release: ");
				year = scann.nextLine();
				
				if ( year.equalsIgnoreCase("exit") ) {
					done = true; count = 5;
				}else {
					count = 3;
				}
			}
			
			if( count == 3 ) {
				System.out.println( "Deseja informar o elenco? [ Sim / Não ]" );
				hasCast = scann.nextLine(); 
				hasCast = hasCast.toLowerCase();
				
					if( hasCast.equalsIgnoreCase("exit") ) {
						done = true; count = 5;
					}else {
						count = 4;
					}
			}
			
			
			if( !done && count == 4 ) {
				ElementObject el = new ElementObject( kind );
					makeSerializeJSON.setElementObject(el, title, year ); 
				
				if (hasCast.startsWith( "s" ) ) {
					makeSerializeJSON.buildCastList( el, scann);
				}
				
					makeSerializeJSON.objectsList.add( el );
				count = 0;
			}
		}
		
		scann.close( );
		makeSerializeJSON.saveSerializedList( makeSerializeJSON.objectsList );
	}

}
