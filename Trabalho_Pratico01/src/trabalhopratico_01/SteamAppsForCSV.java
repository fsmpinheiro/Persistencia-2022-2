package trabalhopratico_01;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( {"id","price","finalprice","discount","discount","name","releasedate","searchtags" } )
public abstract class SteamAppsForCSV {
	
	@JsonProperty("id")
	public String appId;
	
	@JsonProperty("price")
	public String price;
	
	@JsonProperty("finalprice")
	public String discountPrice;
	
	@JsonProperty("discount")
	public String discountPercent;
	
	@JsonProperty("name")
	public String appTitle;
	
	@JsonProperty("releasedate")
	public String appReleaseDate;
	
	@JsonProperty("searchtags")
	private ArrayList<String> tags;
	
	@JsonIgnore
	private String appRevSummary;
	@JsonIgnore
	private ArrayList<String> appPlatforms;
	@JsonIgnore
	private String appThumbnailURL;
	
	@JsonIgnore
	private String description;
	@JsonIgnore
    private String headerImageURL;
	@JsonIgnore
    private ArrayList<String> screenshotURLs;
	@JsonIgnore
    private String releaseDate;
	@JsonIgnore
    private String metascore;
	@JsonIgnore
    private ArrayList<String> appDetails;
	
}
