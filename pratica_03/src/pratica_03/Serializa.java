package pratica_03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serializa {
	
	private List<ElementObject> objectsList;

	
	public Serializa( ) {
		this.objectsList = new ArrayList<ElementObject>();	
	}
	
	
	private void saveSerializedObject( ElementObject objeto ) {
		try {
			FileOutputStream f0utp = new FileOutputStream( "singElement.ser" );
			
			ObjectOutputStream oos0utp = new ObjectOutputStream( f0utp );
			oos0utp.writeObject( objeto );
			
			oos0utp.close();
			f0utp.close();
			System.out.println("Serializado; ");
		} catch ( IOException e ) {
			System.out.println( e.getMessage() );

		}
	}

	private void saveSerializedList( List<ElementObject> objList  ) {
		try {
			FileOutputStream fileOutp = new FileOutputStream( "listOfElements.ser" );
			
			ObjectOutputStream objeOutp = new ObjectOutputStream( fileOutp );
			objeOutp.writeObject( objList );
			
			objeOutp.close();
			fileOutp.close();
			System.out.println("Lista serializada; ");
		} catch ( IOException e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	
	public static void main( String[] args ) {
		
		Serializa makeSerial = new Serializa();
		Scanner scann = new Scanner(System.in);
		int operat = 0;
		
		while( operat != 3 ) {
			System.out.println("1 - Guardar objeto único\n" + "2 - Guardar lista com objetos\n" + "3 - Sair");
			String inpute = scann.nextLine();
			
			if ( !(inpute == "") ){
				operat = Integer.parseInt( inpute );
			}
			
			if( operat == 1 ) {
				System.out.println("Informe o tipo de objeto: Filme / Serie");
				String kind  = scann.nextLine();
				System.out.println("Informe o título: ");
				String title = scann.nextLine();
				System.out.println("Informe o ano de release: ");
				String year  = scann.nextLine();
				
				ElementObject el = new ElementObject( kind );
				el.setName(title); 
				int relDate = Integer.parseInt( year );
				el.setYear( relDate );
				
				makeSerial.saveSerializedObject( el );
				operat = 3;
			}
				
			if( operat == 2 ) {
				boolean done = false;
				int count = 0;
				String kind = "" , title = "", year = "";
				
				while( !done ) {
					System.out.println("Se quiser encerrar digite exit");
					
					if ( count == 0 ) {
						System.out.println("Informe o tipo de objeto: Filme / Serie");
						kind = scann.nextLine();
						if ( kind.equalsIgnoreCase("exit") ) {
							done = true; count = 4; operat = 3;
						}else {
							count = 1;
						}

					}
					
					if ( count == 1 ) {
						System.out.println("Informe o título: ");
						title = scann.nextLine();
						if ( title.equalsIgnoreCase("exit") ) {
							done = true; count = 4; operat = 3;
						}else {
							count = 2;
						}
						
					}
					if ( count == 2 ) {
						System.out.println("Informe o ano de release: ");
						year = scann.nextLine();
						if ( year.equalsIgnoreCase("exit") ) {
							done = true; count = 4; operat = 3;
						}else {
							count = 3;
						}
					}
					
					
					if ( !done && count == 3 ) {
						ElementObject el = new ElementObject( kind );
						el.setName(title);
						int realeaseDate = Integer.parseInt( year );
						el.setYear( realeaseDate );
						
						makeSerial.objectsList.add(el);
						count = 0;
					}
				}
				scann.close();
				makeSerial.saveSerializedList( makeSerial.objectsList );
			}
		}
	}
	
}
