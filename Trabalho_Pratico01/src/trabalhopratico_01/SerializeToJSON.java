package trabalhopratico_01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import apiAccess.SteamAPP;
import apiAccess.SteamAPPs;
import apiAccess.SteamApplication;
import apiEnums.CountryCode;
import apiEnums.Language;
import apiEnums.SearchMode;

public class SerializeToJSON {
	
	private SteamAPPs resultAppsList;
	private SteamAPPs chosenApps;
	
	
	public SerializeToJSON( ) {
		this.resultAppsList = new SteamAPPs( );
		this.chosenApps 	= new SteamAPPs( );
	}
	
	public void buildGamesList( ) {
		
		String keyWord = "";
		boolean done = false;
		Scanner scann = new Scanner(System.in);
		int numOfresults = 3, intCount = 0;
		SearchMode searchMode = SearchMode.RELEVANCE;
//		CountryCode countcode = CountryCode.BR;
		
		System.out.println("Vamos construis uma list de aplicações.\n"
				+ "Me informa o nome de um jogo");
		
		while( !done ) {
			
			if( intCount > 0 ) {
				System.out.println( "Mais algum? [ Caso não, digite encerra; ]" );
			}
			keyWord = scann.nextLine();
			
			if( keyWord.toLowerCase().equals("encerra") ) {
				done = true;
			} else {
				SteamApplication steamSearch = new SteamApplication(CountryCode.BR, Language.BRAZILIAN);
				this.resultAppsList = steamSearch.searchForGames(keyWord, numOfresults, searchMode);
				
				System.out.println("Foram encontrados estes jogos/estas aplicações:\n");
				this.resultAppsList.printAllApps();
				
				boolean hasChosen = false;
				while( !hasChosen ) {
					System.out.println("Pelo element id, escolha um adicionar na lista: ");
					String choice = scann.nextLine();
					for( SteamAPP gameApp : this.resultAppsList ) {
						if( gameApp.getAppId().equals(choice) ) {
							this.chosenApps.add(gameApp);
							hasChosen = true;
						}
					}
					this.resultAppsList = null;
				}
				
				System.out.println("Foram escolhidos:");
				this.chosenApps.printAllApps();
				
				intCount++;
			}
			
		}
		scann.close();
		SteamApplication steam = new SteamApplication(CountryCode.BR, Language.BRAZILIAN);
		for ( SteamAPP gameApp : this.chosenApps ) {
			steam.fillWithDetails( gameApp );
			System.out.println( gameApp );
		}
		
	}
	
	public void saveSerializedList(  ) {
		
		if( !chosenApps.isEmpty() ) {
			
			try {
				File appsListFile = new File( "listOfChosenSteamApps.JSON" );
				
				JsonMapper mapperJson = new JsonMapper();
				mapperJson.enable(SerializationFeature.INDENT_OUTPUT);
				
				List<SteamAPP> objectsList = new ArrayList<SteamAPP>();
				for( SteamAPP app : this.chosenApps ) {
//					System.out.println( app.getAppId() );
					objectsList.add(app);
				}
				
				mapperJson.writeValue( appsListFile, objectsList );
				
				System.out.println( "JSON Serializado;" );
			
			}  catch ( JsonProcessingException j ) {
				j.printStackTrace();
			}catch (Exception e ){
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Primeiro escolha o que deseja salvar na lista");
		}
	}

}
