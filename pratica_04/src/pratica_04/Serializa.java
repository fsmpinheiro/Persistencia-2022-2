package pratica_04;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;




public class Serializa {

	private List<ElementObject> objectsList;

	
	public Serializa( ) {
		this.objectsList = new ArrayList<ElementObject>();	
	}
	
	private void saveSerializedList(List<ElementObject> objList) {

		try {
			
			File listFile = new File( "elementslist.xml" );
			
			XmlMapper xMapper = new XmlMapper( );
			xMapper.enable(SerializationFeature.INDENT_OUTPUT);
			xMapper.writeValue( listFile, objList  ); 
			
			System.out.println("XML Serializado; ");
		} catch( IOException e ) {
			System.out.println( e.getMessage() );
		}
		
	}
		
	
	
	public static void main( String[] args ) {
		
		Serializa makeSerialize = new Serializa();
		
		Scanner scann = new Scanner(System.in);
		boolean done = false;
		int count = 0;
		String kind = "", title = "", year = "" ; 
		
		while( !done ) {
			System.out.println("Se quiser encerrar digite exit");
			
			if( count == 0 ) {
				System.out.println("Informe o tipo de objeto: Filme / Serie");
				kind = scann.nextLine();
				
				if( kind.equalsIgnoreCase("exit") ) {
					done = true; count = 4;
				}else {
					count = 1;
				}
			}
			if( count == 1 ) {
				System.out.println("Informe o título: ");
				title = scann.nextLine();
				if ( title.equalsIgnoreCase("exit") ) {
					done = true; count = 4;
				}else {
					count = 2;
				}
				
			}
			if( count == 2 ) {
				System.out.println("Informe o ano de release: ");
				year = scann.nextLine();
				if ( year.equalsIgnoreCase("exit") ) {
					done = true; count = 4;
				}else {
					count = 3;
				}
			}
			
			if(  !done && count == 3 ) {
				ElementObject el = new ElementObject( kind );
				el.setName(title);
				int realeaseDate = Integer.parseInt( year );
				el.setYear( realeaseDate );
				
				makeSerialize.objectsList.add(el);
				count = 0;
			}
		}
		scann.close();
		makeSerialize.saveSerializedList( makeSerialize.objectsList );
		
	}
	



	
}
