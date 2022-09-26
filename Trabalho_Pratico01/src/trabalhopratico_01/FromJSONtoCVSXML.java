package trabalhopratico_01;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;

import apiAccess.SteamAPP;
import apiAccess.SteamAPPs;

public class FromJSONtoCVSXML {

		
		public FromJSONtoCVSXML( ) {	
		}
		
		public void SerializeToCSV( String inputFile ) {
			
			try {
				CsvMapper csvMapper = new CsvMapper( );
				CsvSchema csvSchema = csvMapper.schemaFor( SteamAppsForCSV.class ).withHeader();
				csvMapper.addMixIn( SteamAPP.class, SteamAppsForCSV.class );
				
				SteamAPP[] steamApps = new ObjectMapper().readValue( new File( inputFile ), SteamAPP[].class);

				csvMapper.writerFor( SteamAPP[].class )
					.with( csvSchema )
					.writeValue( new File( "steamAppProperties.csv"), steamApps );
				
				System.out.println("CVS gerado com sucesso;");
				
				
//				JsonNode jsonTree = new ObjectMapper().readTree( new File( fileName ) );
				
//				Builder csvSchemaBuilder = CsvSchema.builder();
//				JsonNode firstObject = jsonTree.elements().next();
//				firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
//				CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

//				CsvMapper csvMapper = new CsvMapper( );
//				csvMapper.addMixIn( SteamAPP.class, OrderLineCSV.class );
//				
//				csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue( new File( "orderLines.csv"), jsonTree );
//				System.out.println("CVS gerado com sucesso;");
				
			} catch ( Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void SerializeToXML( String inputFile ) {
			
			try {
				FileReader jsonFile = new FileReader( inputFile, StandardCharsets.UTF_8  );
				
				ObjectMapper jsonMapper = new ObjectMapper();
				SteamAPPs jNode = jsonMapper.readValue(jsonFile, SteamAPPs.class);
				
				XmlMapper xMapper = new XmlMapper();
						  			xMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
						  			xMapper.configure(Feature.WRITE_XML_DECLARATION, true);
						  			xMapper.configure(Feature.WRITE_XML_1_1, true);
						  
				xMapper.writeValue( new File( "steamAppProperties.xml" ) , jNode );
				System.out.println("XML gerado com sucesso;");
				
				
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		
}
