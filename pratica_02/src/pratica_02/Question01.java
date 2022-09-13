package pratica_02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
	Crie uma aplicação Java para receber via argumento de linha de comando 
	um nome de arquivo texto e uma string S. A aplicação deve exibir todas 
	as linhas que tenham S como substring, independente dos caracteres 
	estarem em caixa alta ou baixa (case-insensitive). 
	
	argumentos:
	arquivo.txt
	Lambda
	**/


public class Question01 {
	
	public static void main(String[] args) {
		
		System.out.println("O arquivo informado foi: " + args[0] );
		System.out.println("A string  informada foi: " + args[1] + "\n");
		
		
		try {
			InputStream inpStream = new FileInputStream( args[0] );
			InputStreamReader irStream = new InputStreamReader( inpStream, "UTF-8" );
			
			try ( BufferedReader bfReader = new BufferedReader( irStream )){
				String stringLine = "";
				
				while( stringLine != null ) {
					stringLine = bfReader.readLine();
					
					if( stringLine != null ) {
						if ( !stringLine.isBlank() ) {
							
							String keyphrase = stringLine.toLowerCase();
							String keyword = args[1].toLowerCase();
							
							if( keyphrase.contains(keyword) ) {
								System.out.println("  //" + stringLine);
							}							
						} else {
							System.out.println( "  stringLine is blank;\n");
						}
					} else {
						break;
					}
				}
				bfReader.close();
			}
		} catch ( IOException e) {
			System.out.println( e.getMessage() );
		}
		
	}

}
