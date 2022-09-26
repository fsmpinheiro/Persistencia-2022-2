package apiAccess;

import java.util.ArrayList;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import apiEnums.CountryCode;
import apiEnums.Language;
import apiEnums.SearchMode;

public class SteamApplication {
	
	private String cc;
	private String lang;
	
	private final int timeout = 10000;
	
	
	public SteamApplication( CountryCode countryCode,  Language language ) {
		
		this.cc = countryCode.toString().toLowerCase(Locale.ENGLISH);
		this.lang = language.toString().toLowerCase(Locale.ENGLISH);
		
	}
	
	
	public SteamAPPs searchForGames( String gameTitle, int numOfResults, SearchMode searchMode ) {
		
		gameTitle = gameTitle.toLowerCase( Locale.ENGLISH );
		String sortBy = searchMode.getSortBy();
		
		SteamAPPs games = new SteamAPPs();
		
		if( gameTitle.length() < 2) {
			System.out.println("Parâmetro invalido: gameTitle;");
			return games;
		}
		if( numOfResults <= 0 ) {
			System.out.println("Parâmetro invalido: numOfResults;");
			return games;
		}
		
		try {
			int count = 0, page = 0;
			boolean stillFound = true;
			
			while( stillFound ) {
				stillFound = false;
				page++;
				
				Document doc = Jsoup.connect("http://store.steampowered.com/search/?term=" + gameTitle + "&sort_by=" + sortBy + "&page=" + page + "&cc=" + cc + "&l=" + lang)
                        			.timeout(timeout)
                    				.get();
				
				Elements elementsList = doc.getElementsByAttributeValue("id", "search_result_container").select("a");
				
				for( Element element : elementsList ) {
					String id = element.attr("data-ds-appid").trim();
					
					if( id.equals("") )
						continue;
					
					
				//Getting Game Title
					String title = element.getElementsByClass("title").text().trim();
					
				//Getting discount percent
                    String discountPercent = element.getElementsByClass("search_discount").text().trim();

                //Getting price & discounted price
                    String price = "";
                    String discountedPrice = "";
                    
                	if( discountPercent.equals("") ) {
                		price = element.getElementsByClass("search_price").text().trim();
                        discountedPrice = "";
                	} else {
                		Elements priceElm = element.getElementsByClass("discounted");

                			
                        int startIndex = priceElm.toString().indexOf("<br />") + 6;
                        int endIndex = priceElm.toString().indexOf("</div>");

                        price = priceElm.select("strike").text();
                        discountedPrice = priceElm.toString().substring(startIndex, endIndex).trim();
                	}
                	
            	//Getting platforms
                	ArrayList<String> platforms = new ArrayList<String>();
                	Elements platformElementsList = element.select("p").select("span");
                	for( Element platformElement : platformElementsList ) {
                		String platform = platformElement.attr("class").split(" ")[1].trim();
                		platforms.add( platform );
                	}
                	
            	//Getting review summary
                	String reviewSummary = element.getElementsByClass("search_review_summary").attr("data-store-tooltip").trim();
                    if (!reviewSummary.equals("")) {
                        String[] reviewSummaryArray = reviewSummary.split("<br>");
                        reviewSummary = reviewSummaryArray[0] + " (" + reviewSummaryArray[1] + ")";
                    }
                    
            	//Getting added on
                    String addedOn = element.getElementsByClass("search_released").text().trim();
                    
                //Getting thumbnail url
                	String thumbnailURL = element.select("img").attr("src").trim();
                    
                	
            	// Adding GameApp to the ArrayList
                	games.add(new SteamAPP(id, title, price, discountPercent, discountedPrice, reviewSummary, platforms, addedOn, thumbnailURL));
                    ///////////////////////
                	
                	stillFound = true;
                    count++;
                    
                    if (count == numOfResults)
                        break;
				}
				if (count == numOfResults) {
					break;
				}
			}
		} catch ( Exception e) {
			e.printStackTrace();
			games.clear();
		}
		return games;
	}
	
	
	public void fillWithDetails( SteamAPP game ) {
		
		if( game == null || game.getAppId().equals("") ) {
			System.out.println("Game Invalido! ");
			return;
		}
		try {
			Document doc = Jsoup.connect("http://store.steampowered.com/app/" + game.getAppId() + "?l=" + lang + "&cc=" + cc).timeout(timeout)
					.cookie("birthtime", "-2208959999").get();
			
			//Getting description
			String gameDescription = doc.getElementsByClass("game_description_snippet").text().trim();
			game.setDescription( gameDescription );
			
			//Getting headerImageURL
			String gameHeaderImageURL = doc.getElementsByAttributeValue("rel", "image_src").attr("href").trim();
			game.setHeaderImageURL( gameHeaderImageURL );
			
			//Getting screenshotURLs
            ArrayList<String> screenshotURLs = new ArrayList<String>();
            Elements ssUrlElms = doc.getElementsByClass("highlight_screenshot_link");
            for (Element ssUrlElm : ssUrlElms) {
                String screenshotURL = ssUrlElm.attr("href").trim();
                screenshotURLs.add(screenshotURL);
            }
            game.setScreenshotURLs(screenshotURLs);
            
            //Getting release date
            String gameReleaseDate = doc.getElementsByClass("date").text().trim();
            game.setReleaseDate( gameReleaseDate );
            
            //Getting meta score
            String gameMetascore = doc.getElementsByAttributeValue("id", "game_area_metascore").text().trim();
            game.setMetascore( gameMetascore );
            
            //Getting game details
            ArrayList<String> gameDetails = new ArrayList<String>();
            Elements detailElms = doc.getElementsByClass("game_area_details_specs");
            for (Element detailElm : detailElms) {
                String detail = detailElm.text().trim();
                gameDetails.add(detail);
            }
            game.setAppDetails(gameDetails);
            
            //Getting tags
            ArrayList<String> tags = new ArrayList<String>();
            Elements tagElms = doc.getElementsByClass("glance_tags").select("a");
            for (Element tagElm : tagElms) {
                String tag = tagElm.text().trim();
                tags.add(tag);
            }
            game.setTags(tags);
            
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	
}
