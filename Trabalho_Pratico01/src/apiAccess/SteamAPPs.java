package apiAccess;

import java.util.ArrayList;

public class SteamAPPs extends ArrayList<SteamAPP> {

	public SteamAPPs( ) {
		
	}
	
	public void printAllApps( ) {
		if( this.isEmpty() ) {
			System.out.println( "No Applications found!" );
		} else {
			for ( SteamAPP app : this ) {
				System.out.println( app );
			}
		}
	}
}
