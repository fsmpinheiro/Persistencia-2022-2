package apiEnums;

public enum SearchMode {
	
	RELEVANCE("_ASC"),
    RELEASEDATE("Released_DESC"),
    NAME("Name_ASC"),
    LOWESTPRICE("Price_ASC"),
    HIGHESTPRICE("Price_DESC"),
    REVIEWS("Reviews_DESC");

    private final String sortBy;

    SearchMode(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

}
