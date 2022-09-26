package apiAccess;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( {"appId", "appTitle", "price" } )
public class SteamAPP {
	
	private String appId;
	private String appTitle;
	private String price;
	private String discountPercent;
	private String discountPrice;
	private String appRevSummary;
	private ArrayList<String> appPlatforms;
	private String appReleaseDate;
	private String appThumbnailURL;
	
	private String description;
    private String headerImageURL;
    private ArrayList<String> screenshotURLs;
    private String releaseDate;
    private String metascore;
    private ArrayList<String> appDetails;
    private ArrayList<String> tags;
    
    public SteamAPP() {
    }
    
    public SteamAPP( String id, String title, String price, String discountPercent, String discountedPrice, String reviewSummary, ArrayList<String> platforms, String addedOn, String thumbnailURL ) {
    	
    	this.appId = id;

        this.appTitle = title;
        
        this.appPlatforms = platforms;

        if (price.equals("")) {
        	this.price = "?"; } else { this.price = price; }    
        
        
        if (discountPercent.equals("")) {
        	this.discountPercent = "?"; } else { this.discountPercent = discountPercent; }
            
            
        if (discountedPrice.equals("")) {
        	this.discountPrice = "?"; } else { this.discountPrice = discountedPrice; }

        
        if (reviewSummary.equals("")) {
            this.appRevSummary = "?"; } else { this.appRevSummary = reviewSummary; } 
        
        
        if (addedOn.equals("")) {
            this.appReleaseDate = "?"; } else { this.appReleaseDate = addedOn; } 
        
        
        if (thumbnailURL.equals("")) {
            this.appThumbnailURL = "?"; } else { this.appThumbnailURL = thumbnailURL; }
        
        
        // ================== EXTRA ==================
        this.description = "?";
        this.headerImageURL = "?";
        this.screenshotURLs = null;
        this.releaseDate = "?";
        this.metascore = "?";
        this.appDetails = null;
        this.tags = null;
    	
    }

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getAppRevSummary() {
		return appRevSummary;
	}

	public void setAppRevSummary(String appRevSummary) {
		this.appRevSummary = appRevSummary;
	}

	public ArrayList<String> getAppPlatforms() {
		return appPlatforms;
	}

	public void setAppPlatforms(ArrayList<String> appPlatforms) {
		this.appPlatforms = appPlatforms;
	}

	public String getAppReleaseDate() {
		return appReleaseDate;
	}

	public void setAppReleaseDate(String appReleaseDate) {
		this.appReleaseDate = appReleaseDate;
	}

	public String getAppThumbnailURL() {
		return appThumbnailURL;
	}

	public void setAppThumbnailURL(String appThumbnailURL) {
		this.appThumbnailURL = appThumbnailURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeaderImageURL() {
		return headerImageURL;
	}

	public void setHeaderImageURL(String headerImageURL) {
		this.headerImageURL = headerImageURL;
	}

	public ArrayList<String> getScreenshotURLs() {
		return screenshotURLs;
	}

	public void setScreenshotURLs(ArrayList<String> screenshotURLs) {
		this.screenshotURLs = screenshotURLs;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public ArrayList<String> getAppDetails() {
		return appDetails;
	}

	public void setAppDetails(ArrayList<String> appDetails) {
		this.appDetails = appDetails;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
    
    //Print ordenado
	@Override
	public String toString( ) {
		String cOut = "elemnt id: " + appId + ";\n";
		
		cOut += "title: " + appTitle + "\n";

        if (!price.equals("?"))
        	cOut += "price: " + price + "\n";

        if (!discountPrice.equals("?") && !discountPercent.equals("?"))
        	cOut += "discounted price: " + discountPrice + " (" + discountPercent + ")" + "\n";

        if (appPlatforms != null && !appPlatforms.isEmpty())
        	cOut += "platforms: " + appPlatforms + "\n";

        if (!appRevSummary.equals("?"))
        	cOut += "review summary: " + appRevSummary + "\n";

        if (!appReleaseDate.equals("?"))
        	cOut += "added on: " + appReleaseDate + "\n";

        if (!appThumbnailURL.equals("?"))
        	cOut += "thumbnail url: " + appThumbnailURL + "\n";

        // ================== EXTRA ==================
        if (!description.equals("") && !description.equals("?"))
        	cOut += "description: " + description + "\n";

        if (!headerImageURL.equals("") && !headerImageURL.equals("?"))
        	cOut += "header image url: " + headerImageURL + "\n";

//        if (screenshotURLs != null && !screenshotURLs.isEmpty())
//        	cOut += "screenshot urls: " + screenshotURLs + "\n";

        if (!releaseDate.equals("") && !releaseDate.equals("?"))
        	cOut += "release date: " + releaseDate + "\n";

        if (!metascore.equals("") && !metascore.equals("?"))
        	cOut += "metascore: " + metascore + "\n";

        if (appDetails != null && !appDetails.isEmpty())
        	cOut += "details: " + appDetails + "\n";

        if (tags != null && !tags.isEmpty())
        	cOut += "tags: " + tags + "\n";
		
		cOut += "====================endl >" + appId  + "\n";
		return cOut;
	}
	

}
