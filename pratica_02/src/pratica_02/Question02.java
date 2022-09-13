package pratica_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
	Escreva uma aplicação Java para receber via argumentos de linha de 
	comando três nomes de arquivos. Deve-se gerar um novo arquivo a 
	partir da concatenação dos dois primeiros arquivos. O nome do novo 
	arquivo gerado é o último dos três nomes de arquivos recebidos 
	como entrada.
	
	argumentos:
	input_0a.txt
	input_0b.txt
	input_0c.txt
	output_1a.txt
	**/


public class Question02 {

	public static void main(String[] args) {
		System.out.println("Arquivos informados: " + args[0] + " / " + args[1] + " / " + args[2] + " / " + args[3] + "\n" );
		String supaString = "";

		for ( String fileName : args ) {
			try {
				InputStream firstIsStream = new FileInputStream( fileName );
				InputStreamReader firstIrStream = new InputStreamReader( firstIsStream, "UTF-8" );
				
				try (BufferedReader bfReader = new BufferedReader( firstIrStream )){
					String sampleString = "";
					
					while( sampleString != null ) {
						sampleString = bfReader.readLine();
						if( sampleString != null ) {
							supaString += sampleString + "\n\n";
						}
					}
					bfReader.close();					
				}
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		System.out.println(supaString); 
		
		try {
			OutputStream outpStream = new FileOutputStream( args[3] );
			OutputStreamWriter owriterStream = new OutputStreamWriter( outpStream );
			BufferedWriter bufWriter = new BufferedWriter( owriterStream );
			
			bufWriter.write(supaString);
			bufWriter.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
