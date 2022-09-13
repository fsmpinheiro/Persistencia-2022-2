package pratica_03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Desserializa {
	
	private List<ElementObject> objectsList;
	
	public Desserializa( ) {
		this.objectsList = new ArrayList<ElementObject>();
	}

	
	private void loadSerializedObject( ) {
		ElementObject el = null;
		try {
			FileInputStream f1npt = new FileInputStream( "singElement.ser" );
			
			ObjectInputStream ois1npt = new ObjectInputStream( f1npt );
			el = (ElementObject) ois1npt.readObject();
			
			ois1npt.close();
			f1npt.close();
		} catch ( IOException e ) {
			System.out.println( e.getMessage() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println( el.toString() );
	}
	
	private void loadSerializedList( ) {
		try {
			FileInputStream fileInpu = new FileInputStream( "listOfElements.ser" );
			
			ObjectInputStream objeInpu = new ObjectInputStream( fileInpu );
			this.objectsList = (List<ElementObject>) objeInpu.readObject();
			
			objeInpu.close();
			fileInpu.close();
		} catch (IOException e ) {
			System.out.println( e.getMessage() );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for (ElementObject elObject : objectsList) {
			System.out.println( elObject.toString() + "\n");
		} 
	}
	
	
	public static void main( String[] args ) {
		Desserializa readSerial = new Desserializa( );
		int count = 0;
		Scanner scann = new Scanner(System.in);
		
		while( count != 3 ) {
			System.out.println("1 - Ler objeto único\n" + "2 - Ler lista com objetos\n" + "3 - Sair");
			String inpute = scann.nextLine();
			
			if ( !(inpute == "") ){
				count = Integer.parseInt( inpute );
			}
			
			
			if( count == 1 ) {
				readSerial.loadSerializedObject();
			}
			if( count == 2 ) {
				readSerial.loadSerializedList();
			}
			
		}
		
		
		
	}
}
