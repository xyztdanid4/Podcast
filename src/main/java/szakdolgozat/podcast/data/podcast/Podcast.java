package szakdolgozat.podcast.data.podcast;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

@Entity("Podcast")
public class Podcast {

	private String wrapperType;
	private String kind;
	private String collectionId;
	private String trackId;
	private String artistName;
	private String collectionName;
	private String trackName;
	private String collectionCensoredName;
	private String trackCensoredName;
	private String collectionViewUrl;
	private String feedUrl;
	private String trackViewUrl;
	private String artworkUrl;
	private String artworkUrl60;
	private String artworkUrl100;
	private String collectionPrice;
	private String trackPrice;
	private String trackRentalPrice;
	private String collectionHdPrice;
	private String trackHdPrice;
	private String trackHdRentalPrice;
	private String releaseDate;
	private String collectionExplicitness;
	private String trackExplicitness;
	private String trackCount;
	private String country;
	private String currency;
	private String primaryGenreName;
	private String radioStationUrl;
	private String artworkUrl600;
	private List<String> genreIds;
	private List<String> genres;
	private List<PodcastEpisode> podcastEpisodes;

	/**
	 * @return the podcastEpisode
	 */
	public List<PodcastEpisode> getPodcastEpisode() {
		return podcastEpisodes;
	}

	/**
	 * @param podcastEpisode
	 *            the podcastEpisode to set
	 */
	public void setPodcastEpisode(List<PodcastEpisode> podcastEpisode) {
		this.podcastEpisodes = podcastEpisode;
	}

	/**
	 * @return the wrapperType
	 */
	public String getWrapperType() {
		return wrapperType;
	}

	/**
	 * @param wrapperType
	 *            the wrapperType to set
	 */
	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind
	 *            the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @return the collectionId
	 */
	public String getCollectionId() {
		return collectionId;
	}

	/**
	 * @param collectionId
	 *            the collectionId to set
	 */
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	/**
	 * @return the trackId
	 */
	public String getTrackId() {
		return trackId;
	}

	/**
	 * @param trackId
	 *            the trackId to set
	 */
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @param artistName
	 *            the artistName to set
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * @return the collectionName
	 */
	public String getCollectionName() {
		return collectionName;
	}

	/**
	 * @param collectionName
	 *            the collectionName to set
	 */
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	/**
	 * @return the trackName
	 */
	public String getTrackName() {
		return trackName;
	}

	/**
	 * @param trackName
	 *            the trackName to set
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	/**
	 * @return the collectionCensoredName
	 */
	public String getCollectionCensoredName() {
		return collectionCensoredName;
	}

	/**
	 * @param collectionCensoredName
	 *            the collectionCensoredName to set
	 */
	public void setCollectionCensoredName(String collectionCensoredName) {
		this.collectionCensoredName = collectionCensoredName;
	}

	/**
	 * @return the trackCensoredName
	 */
	public String getTrackCensoredName() {
		return trackCensoredName;
	}

	/**
	 * @param trackCensoredName
	 *            the trackCensoredName to set
	 */
	public void setTrackCensoredName(String trackCensoredName) {
		this.trackCensoredName = trackCensoredName;
	}

	/**
	 * @return the collectionViewUrl
	 */
	public String getCollectionViewUrl() {
		return collectionViewUrl;
	}

	/**
	 * @param collectionViewUrl
	 *            the collectionViewUrl to set
	 */
	public void setCollectionViewUrl(String collectionViewUrl) {
		this.collectionViewUrl = collectionViewUrl;
	}

	/**
	 * @return the feedUrl
	 */
	public String getFeedUrl() {
		return feedUrl;
	}

	/**
	 * @param feedUrl
	 *            the feedUrl to set
	 */
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	/**
	 * @return the trackViewUrl
	 */
	public String getTrackViewUrl() {
		return trackViewUrl;
	}

	/**
	 * @param trackViewUrl
	 *            the trackViewUrl to set
	 */
	public void setTrackViewUrl(String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}

	/**
	 * @return the artworkUrl
	 */
	public String getArtworkUrl() {
		return artworkUrl;
	}

	/**
	 * @param artworkUrl
	 *            the artworkUrl to set
	 */
	public void setArtworkUrl(String artworkUrl) {
		this.artworkUrl = artworkUrl;
	}

	/**
	 * @return the artworkUrl60
	 */
	public String getArtworkUrl60() {
		return artworkUrl60;
	}

	/**
	 * @param artworkUrl60
	 *            the artworkUrl60 to set
	 */
	public void setArtworkUrl60(String artworkUrl60) {
		this.artworkUrl60 = artworkUrl60;
	}

	/**
	 * @return the artworkUrl100
	 */
	public String getArtworkUrl100() {
		return artworkUrl100;
	}

	/**
	 * @param artworkUrl100
	 *            the artworkUrl100 to set
	 */
	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}

	/**
	 * @return the collectionPrice
	 */
	public String getCollectionPrice() {
		return collectionPrice;
	}

	/**
	 * @param collectionPrice
	 *            the collectionPrice to set
	 */
	public void setCollectionPrice(String collectionPrice) {
		this.collectionPrice = collectionPrice;
	}

	/**
	 * @return the trackPrice
	 */
	public String getTrackPrice() {
		return trackPrice;
	}

	/**
	 * @param trackPrice
	 *            the trackPrice to set
	 */
	public void setTrackPrice(String trackPrice) {
		this.trackPrice = trackPrice;
	}

	/**
	 * @return the trackRentalPrice
	 */
	public String getTrackRentalPrice() {
		return trackRentalPrice;
	}

	/**
	 * @param trackRentalPrice
	 *            the trackRentalPrice to set
	 */
	public void setTrackRentalPrice(String trackRentalPrice) {
		this.trackRentalPrice = trackRentalPrice;
	}

	/**
	 * @return the collectionHdPrice
	 */
	public String getCollectionHdPrice() {
		return collectionHdPrice;
	}

	/**
	 * @param collectionHdPrice
	 *            the collectionHdPrice to set
	 */
	public void setCollectionHdPrice(String collectionHdPrice) {
		this.collectionHdPrice = collectionHdPrice;
	}

	/**
	 * @return the trackHdPrice
	 */
	public String getTrackHdPrice() {
		return trackHdPrice;
	}

	/**
	 * @param trackHdPrice
	 *            the trackHdPrice to set
	 */
	public void setTrackHdPrice(String trackHdPrice) {
		this.trackHdPrice = trackHdPrice;
	}

	/**
	 * @return the trackHdRentalPrice
	 */
	public String getTrackHdRentalPrice() {
		return trackHdRentalPrice;
	}

	/**
	 * @param trackHdRentalPrice
	 *            the trackHdRentalPrice to set
	 */
	public void setTrackHdRentalPrice(String trackHdRentalPrice) {
		this.trackHdRentalPrice = trackHdRentalPrice;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the collectionExplicitness
	 */
	public String getCollectionExplicitness() {
		return collectionExplicitness;
	}

	/**
	 * @param collectionExplicitness
	 *            the collectionExplicitness to set
	 */
	public void setCollectionExplicitness(String collectionExplicitness) {
		this.collectionExplicitness = collectionExplicitness;
	}

	/**
	 * @return the trackExplicitness
	 */
	public String getTrackExplicitness() {
		return trackExplicitness;
	}

	/**
	 * @param trackExplicitness
	 *            the trackExplicitness to set
	 */
	public void setTrackExplicitness(String trackExplicitness) {
		this.trackExplicitness = trackExplicitness;
	}

	/**
	 * @return the trackCount
	 */
	public String getTrackCount() {
		return trackCount;
	}

	/**
	 * @param trackCount
	 *            the trackCount to set
	 */
	public void setTrackCount(String trackCount) {
		this.trackCount = trackCount;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the primaryGenreName
	 */
	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	/**
	 * @param primaryGenreName
	 *            the primaryGenreName to set
	 */
	public void setPrimaryGenreName(String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}

	/**
	 * @return the radioStationUrl
	 */
	public String getRadioStationUrl() {
		return radioStationUrl;
	}

	/**
	 * @param radioStationUrl
	 *            the radioStationUrl to set
	 */
	public void setRadioStationUrl(String radioStationUrl) {
		this.radioStationUrl = radioStationUrl;
	}

	/**
	 * @return the artworkUrl600
	 */
	public String getArtworkUrl600() {
		return artworkUrl600;
	}

	/**
	 * @param artworkUrl600
	 *            the artworkUrl600 to set
	 */
	public void setArtworkUrl600(String artworkUrl600) {
		this.artworkUrl600 = artworkUrl600;
	}

	/**
	 * @return the genreIds
	 */
	public List<String> getGenreIds() {
		return genreIds;
	}

	/**
	 * @param genreIds
	 *            the genreIds to set
	 */
	public void setGenreIds(List<String> genreIds) {
		this.genreIds = genreIds;
	}

	/**
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * @param genres
	 *            the genres to set
	 */
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResultItem [wrapperType=" + wrapperType + ", kind="
				+ kind + ", collectionId=" + collectionId + ", trackId="
				+ trackId + ", artistName=" + artistName + ", collectionName="
				+ collectionName + ", trackName=" + trackName
				+ ", collectionCensoredName=" + collectionCensoredName
				+ ", trackCensoredName=" + trackCensoredName
				+ ", collectionViewUrl=" + collectionViewUrl + ", feedUrl="
				+ feedUrl + ", trackViewUrl=" + trackViewUrl + ", artworkUrl="
				+ artworkUrl + ", artworkUrl60=" + artworkUrl60
				+ ", artworkUrl100=" + artworkUrl100 + ", collectionPrice="
				+ collectionPrice + ", trackPrice=" + trackPrice
				+ ", trackRentalPrice=" + trackRentalPrice
				+ ", collectionHdPrice=" + collectionHdPrice
				+ ", trackHdPrice=" + trackHdPrice + ", trackHdRentalPrice="
				+ trackHdRentalPrice + ", releaseDate=" + releaseDate
				+ ", collectionExplicitness=" + collectionExplicitness
				+ ", trackExplicitness=" + trackExplicitness + ", trackCount="
				+ trackCount + ", country=" + country + ", currency="
				+ currency + ", primaryGenreName=" + primaryGenreName
				+ ", radioStationUrl=" + radioStationUrl + ", artworkUrl600="
				+ artworkUrl600 + ", genreIds=" + genreIds + ", genres="
				+ genres + ", podcastEpisode=" + podcastEpisodes + "]";
	}

}