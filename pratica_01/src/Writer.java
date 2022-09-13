import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Writer {
	/**
	Crie uma aplica��o Java para receber via entrada de teclado um caminho 
	ou nome de arquivo texto e dois n�meros inteiros (n1 e n2).
	
	A aplica��o deve exibir as linhas do arquivo entre 	n1 e n2, incluindo 
	as linhas n1 e n2. Se n1 n�o for definido, deve-se exibir as linhas do 
	arquivo a partir da primeira linha at� n2. 
	
	Se n2 n�o for definido, deve-se exibir as linhas do arquivo a partir de 
	n1 at� o final do arquivo. **/
	

	
	public static void main(String[] args) {
		
		List<Element> arrayNs = new ArrayList<Element>();

		Scanner scan = new Scanner( System.in );
		System.out.println("Informe o nome do arquivo: ");
		String sPath = scan.nextLine();
		System.out.println( "O arquivo informado foi: \"" + sPath + "\""  );
		
		
		try {
			InputStream inpStream = new FileInputStream(  sPath );
			InputStreamReader irStream = new InputStreamReader( inpStream, "UTF-8" );
			try (BufferedReader bfReader = new BufferedReader( irStream )) {
				String value = "";
				
				while( value != null ) {
					value = bfReader.readLine();
					
					if ( value != null) {
						if( !value.isBlank() ) {
//							System.out.println("value not blank;");
							
							int locVal = Integer.parseInt(value);
							Element ell = new Element(locVal);
							arrayNs.add(ell);
							
						}else {
							System.out.println("value is blank;");
						}
					}else{
						 break;
					}

				}
								
				bfReader.close();
				
			}
			
			
		} catch (IOException e) {
			System.out.println( e.getMessage() );
		}
		for( Element el : arrayNs  ) {
			System.out.println("N�mero: " + el.getValue());
		}
		scan.close();
	}

}