package trabalhopratico_01;

public class ElementBuilder {
	
	private SerializeToJSON serial;

	public ElementBuilder( ) {
		this.serial = new SerializeToJSON();
	}
	
	
	
	public static void main( String args[ ] ) {

		ElementBuilder workOne = new ElementBuilder();
		
		workOne.serial.buildGamesList();
		workOne.serial.saveSerializedList();
		
		FromJSONtoCVSXML workTwo = new FromJSONtoCVSXML( );
		workTwo.SerializeToCSV( "listOfChosenSteamApps.JSON" );
		workTwo.SerializeToXML( "listOfChosenSteamApps.JSON" );
			
	}
	
}
