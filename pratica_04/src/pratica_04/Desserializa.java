package pratica_04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Desserializa {

	private List<ElementObject> objectsList;
	
	public Desserializa( ) {
		this.objectsList = new ArrayList<ElementObject>();
	}
	
	private void loadSerializedList( ) {

		try {
			FileInputStream listFile = new FileInputStream( "elementslist.xml" ); 
			
			XmlMapper xmlMapper = new XmlMapper( );
			XMLInputFactory xInpFact = XMLInputFactory.newInstance( );
			
			try {
				XMLStreamReader xmlStreamReader = xInpFact.createXMLStreamReader( listFile );
				
				while( xmlStreamReader.hasNext( ) ) {
					xmlStreamReader.next();
					if( xmlStreamReader.getEventType() == xmlStreamReader.START_ELEMENT  ) {

						if( "item".equals(xmlStreamReader.getLocalName() ) ) {
							ElementObject el = xmlMapper.readValue(xmlStreamReader, ElementObject.class );
							this.objectsList.add( el );
						}
					}
				}
				
			} catch (Exception e) {
				System.out.println("XMLStreamReader error: " + e.getMessage() );
			}
						
			for (ElementObject elObjc : objectsList) {
				System.out.println(elObjc.toString() + " !\n");
			}
			
		} catch ( IOException e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	
	
	public static void main( String[] args ) {
		
		Desserializa readSerialized = new Desserializa( );
		
		readSerialized.loadSerializedList();
	}
	
}
