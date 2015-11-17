package szakdolgozat.podcast.data.podcast;

import java.io.Serializable;
import java.util.List;

/**
 * The podcast class. Contains information about Podcasts.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 *
 */
public class Podcast implements Serializable {
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
		return this.podcastEpisodes;
	}

	/**
	 * @param podcastEpisode
	 *            the podcastEpisode to set
	 */
	public void setPodcastEpisode(final List<PodcastEpisode> podcastEpisode) {
		this.podcastEpisodes = podcastEpisode;
	}

	/**
	 * @return the wrapperType
	 */
	public String getWrapperType() {
		return this.wrapperType;
	}

	/**
	 * @param wrapperType
	 *            the wrapperType to set
	 */
	public void setWrapperType(final String wrapperType) {
		this.wrapperType = wrapperType;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return this.kind;
	}

	/**
	 * @param kind
	 *            the kind to set
	 */
	public void setKind(final String kind) {
		this.kind = kind;
	}

	/**
	 * @return the collectionId
	 */
	public String getCollectionId() {
		return this.collectionId;
	}

	/**
	 * @param collectionId
	 *            the collectionId to set
	 */
	public void setCollectionId(final String collectionId) {
		this.collectionId = collectionId;
	}

	/**
	 * @return the trackId
	 */
	public String getTrackId() {
		return this.trackId;
	}

	/**
	 * @param trackId
	 *            the trackId to set
	 */
	public void setTrackId(final String trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return this.artistName;
	}

	/**
	 * @param artistName
	 *            the artistName to set
	 */
	public void setArtistName(final String artistName) {
		this.artistName = artistName;
	}

	/**
	 * @return the collectionName
	 */
	public String getCollectionName() {
		return this.collectionName;
	}

	/**
	 * @param collectionName
	 *            the collectionName to set
	 */
	public void setCollectionName(final String collectionName) {
		this.collectionName = collectionName;
	}

	/**
	 * @return the trackName
	 */
	public String getTrackName() {
		return this.trackName;
	}

	/**
	 * @param trackName
	 *            the trackName to set
	 */
	public void setTrackName(final String trackName) {
		this.trackName = trackName;
	}

	/**
	 * @return the collectionCensoredName
	 */
	public String getCollectionCensoredName() {
		return this.collectionCensoredName;
	}

	/**
	 * @param collectionCensoredName
	 *            the collectionCensoredName to set
	 */
	public void setCollectionCensoredName(final String collectionCensoredName) {
		this.collectionCensoredName = collectionCensoredName;
	}

	/**
	 * @return the trackCensoredName
	 */
	public String getTrackCensoredName() {
		return this.trackCensoredName;
	}

	/**
	 * @param trackCensoredName
	 *            the trackCensoredName to set
	 */
	public void setTrackCensoredName(final String trackCensoredName) {
		this.trackCensoredName = trackCensoredName;
	}

	/**
	 * @return the collectionViewUrl
	 */
	public String getCollectionViewUrl() {
		return this.collectionViewUrl;
	}

	/**
	 * @param collectionViewUrl
	 *            the collectionViewUrl to set
	 */
	public void setCollectionViewUrl(final String collectionViewUrl) {
		this.collectionViewUrl = collectionViewUrl;
	}

	/**
	 * @return the feedUrl
	 */
	public String getFeedUrl() {
		return this.feedUrl;
	}

	/**
	 * @param feedUrl
	 *            the feedUrl to set
	 */
	public void setFeedUrl(final String feedUrl) {
		this.feedUrl = feedUrl;
	}

	/**
	 * @return the trackViewUrl
	 */
	public String getTrackViewUrl() {
		return this.trackViewUrl;
	}

	/**
	 * @param trackViewUrl
	 *            the trackViewUrl to set
	 */
	public void setTrackViewUrl(final String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}

	/**
	 * @return the artworkUrl
	 */
	public String getArtworkUrl() {
		return this.artworkUrl;
	}

	/**
	 * @param artworkUrl
	 *            the artworkUrl to set
	 */
	public void setArtworkUrl(final String artworkUrl) {
		this.artworkUrl = artworkUrl;
	}

	/**
	 * @return the artworkUrl60
	 */
	public String getArtworkUrl60() {
		return this.artworkUrl60;
	}

	/**
	 * @param artworkUrl60
	 *            the artworkUrl60 to set
	 */
	public void setArtworkUrl60(final String artworkUrl60) {
		this.artworkUrl60 = artworkUrl60;
	}

	/**
	 * @return the artworkUrl100
	 */
	public String getArtworkUrl100() {
		return this.artworkUrl100;
	}

	/**
	 * @param artworkUrl100
	 *            the artworkUrl100 to set
	 */
	public void setArtworkUrl100(final String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}

	/**
	 * @return the collectionPrice
	 */
	public String getCollectionPrice() {
		return this.collectionPrice;
	}

	/**
	 * @param collectionPrice
	 *            the collectionPrice to set
	 */
	public void setCollectionPrice(final String collectionPrice) {
		this.collectionPrice = collectionPrice;
	}

	/**
	 * @return the trackPrice
	 */
	public String getTrackPrice() {
		return this.trackPrice;
	}

	/**
	 * @param trackPrice
	 *            the trackPrice to set
	 */
	public void setTrackPrice(final String trackPrice) {
		this.trackPrice = trackPrice;
	}

	/**
	 * @return the trackRentalPrice
	 */
	public String getTrackRentalPrice() {
		return this.trackRentalPrice;
	}

	/**
	 * @param trackRentalPrice
	 *            the trackRentalPrice to set
	 */
	public void setTrackRentalPrice(final String trackRentalPrice) {
		this.trackRentalPrice = trackRentalPrice;
	}

	/**
	 * @return the collectionHdPrice
	 */
	public String getCollectionHdPrice() {
		return this.collectionHdPrice;
	}

	/**
	 * @param collectionHdPrice
	 *            the collectionHdPrice to set
	 */
	public void setCollectionHdPrice(final String collectionHdPrice) {
		this.collectionHdPrice = collectionHdPrice;
	}

	/**
	 * @return the trackHdPrice
	 */
	public String getTrackHdPrice() {
		return this.trackHdPrice;
	}

	/**
	 * @param trackHdPrice
	 *            the trackHdPrice to set
	 */
	public void setTrackHdPrice(final String trackHdPrice) {
		this.trackHdPrice = trackHdPrice;
	}

	/**
	 * @return the trackHdRentalPrice
	 */
	public String getTrackHdRentalPrice() {
		return this.trackHdRentalPrice;
	}

	/**
	 * @param trackHdRentalPrice
	 *            the trackHdRentalPrice to set
	 */
	public void setTrackHdRentalPrice(final String trackHdRentalPrice) {
		this.trackHdRentalPrice = trackHdRentalPrice;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * @param releaseDate
	 *            the releaseDate to set
	 */
	public void setReleaseDate(final String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the collectionExplicitness
	 */
	public String getCollectionExplicitness() {
		return this.collectionExplicitness;
	}

	/**
	 * @param collectionExplicitness
	 *            the collectionExplicitness to set
	 */
	public void setCollectionExplicitness(final String collectionExplicitness) {
		this.collectionExplicitness = collectionExplicitness;
	}

	/**
	 * @return the trackExplicitness
	 */
	public String getTrackExplicitness() {
		return this.trackExplicitness;
	}

	/**
	 * @param trackExplicitness
	 *            the trackExplicitness to set
	 */
	public void setTrackExplicitness(final String trackExplicitness) {
		this.trackExplicitness = trackExplicitness;
	}

	/**
	 * @return the trackCount
	 */
	public String getTrackCount() {
		return this.trackCount;
	}

	/**
	 * @param trackCount
	 *            the trackCount to set
	 */
	public void setTrackCount(final String trackCount) {
		this.trackCount = trackCount;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * @return the primaryGenreName
	 */
	public String getPrimaryGenreName() {
		return this.primaryGenreName;
	}

	/**
	 * @param primaryGenreName
	 *            the primaryGenreName to set
	 */
	public void setPrimaryGenreName(final String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}

	/**
	 * @return the radioStationUrl
	 */
	public String getRadioStationUrl() {
		return this.radioStationUrl;
	}

	/**
	 * @param radioStationUrl
	 *            the radioStationUrl to set
	 */
	public void setRadioStationUrl(final String radioStationUrl) {
		this.radioStationUrl = radioStationUrl;
	}

	/**
	 * @return the artworkUrl600
	 */
	public String getArtworkUrl600() {
		return this.artworkUrl600;
	}

	/**
	 * @param artworkUrl600
	 *            the artworkUrl600 to set
	 */
	public void setArtworkUrl600(final String artworkUrl600) {
		this.artworkUrl600 = artworkUrl600;
	}

	/**
	 * @return the genreIds
	 */
	public List<String> getGenreIds() {
		return this.genreIds;
	}

	/**
	 * @param genreIds
	 *            the genreIds to set
	 */
	public void setGenreIds(final List<String> genreIds) {
		this.genreIds = genreIds;
	}

	/**
	 * @return the genres
	 */
	public List<String> getGenres() {
		return this.genres;
	}

	/**
	 * @param genres
	 *            the genres to set
	 */
	public void setGenres(final List<String> genres) {
		this.genres = genres;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.artistName == null) ? 0 : this.artistName.hashCode());
		result = prime * result + ((this.artworkUrl == null) ? 0 : this.artworkUrl.hashCode());
		result = prime * result + ((this.artworkUrl100 == null) ? 0 : this.artworkUrl100.hashCode());
		result = prime * result + ((this.artworkUrl60 == null) ? 0 : this.artworkUrl60.hashCode());
		result = prime * result + ((this.artworkUrl600 == null) ? 0 : this.artworkUrl600.hashCode());
		result = prime * result + ((this.collectionCensoredName == null) ? 0 : this.collectionCensoredName.hashCode());
		result = prime * result + ((this.collectionExplicitness == null) ? 0 : this.collectionExplicitness.hashCode());
		result = prime * result + ((this.collectionHdPrice == null) ? 0 : this.collectionHdPrice.hashCode());
		result = prime * result + ((this.collectionId == null) ? 0 : this.collectionId.hashCode());
		result = prime * result + ((this.collectionName == null) ? 0 : this.collectionName.hashCode());
		result = prime * result + ((this.collectionPrice == null) ? 0 : this.collectionPrice.hashCode());
		result = prime * result + ((this.collectionViewUrl == null) ? 0 : this.collectionViewUrl.hashCode());
		result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
		result = prime * result + ((this.currency == null) ? 0 : this.currency.hashCode());
		result = prime * result + ((this.feedUrl == null) ? 0 : this.feedUrl.hashCode());
		result = prime * result + ((this.genreIds == null) ? 0 : this.genreIds.hashCode());
		result = prime * result + ((this.genres == null) ? 0 : this.genres.hashCode());
		result = prime * result + ((this.kind == null) ? 0 : this.kind.hashCode());
		result = prime * result + ((this.podcastEpisodes == null) ? 0 : this.podcastEpisodes.hashCode());
		result = prime * result + ((this.primaryGenreName == null) ? 0 : this.primaryGenreName.hashCode());
		result = prime * result + ((this.radioStationUrl == null) ? 0 : this.radioStationUrl.hashCode());
		result = prime * result + ((this.releaseDate == null) ? 0 : this.releaseDate.hashCode());
		result = prime * result + ((this.trackCensoredName == null) ? 0 : this.trackCensoredName.hashCode());
		result = prime * result + ((this.trackCount == null) ? 0 : this.trackCount.hashCode());
		result = prime * result + ((this.trackExplicitness == null) ? 0 : this.trackExplicitness.hashCode());
		result = prime * result + ((this.trackHdPrice == null) ? 0 : this.trackHdPrice.hashCode());
		result = prime * result + ((this.trackHdRentalPrice == null) ? 0 : this.trackHdRentalPrice.hashCode());
		result = prime * result + ((this.trackId == null) ? 0 : this.trackId.hashCode());
		result = prime * result + ((this.trackName == null) ? 0 : this.trackName.hashCode());
		result = prime * result + ((this.trackPrice == null) ? 0 : this.trackPrice.hashCode());
		result = prime * result + ((this.trackRentalPrice == null) ? 0 : this.trackRentalPrice.hashCode());
		result = prime * result + ((this.trackViewUrl == null) ? 0 : this.trackViewUrl.hashCode());
		result = prime * result + ((this.wrapperType == null) ? 0 : this.wrapperType.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Podcast other = (Podcast) obj;
		if (this.artistName == null) {
			if (other.artistName != null) {
				return false;
			}
		} else if (!this.artistName.equals(other.artistName)) {
			return false;
		}
		if (this.artworkUrl == null) {
			if (other.artworkUrl != null) {
				return false;
			}
		} else if (!this.artworkUrl.equals(other.artworkUrl)) {
			return false;
		}
		if (this.artworkUrl100 == null) {
			if (other.artworkUrl100 != null) {
				return false;
			}
		} else if (!this.artworkUrl100.equals(other.artworkUrl100)) {
			return false;
		}
		if (this.artworkUrl60 == null) {
			if (other.artworkUrl60 != null) {
				return false;
			}
		} else if (!this.artworkUrl60.equals(other.artworkUrl60)) {
			return false;
		}
		if (this.artworkUrl600 == null) {
			if (other.artworkUrl600 != null) {
				return false;
			}
		} else if (!this.artworkUrl600.equals(other.artworkUrl600)) {
			return false;
		}
		if (this.collectionCensoredName == null) {
			if (other.collectionCensoredName != null) {
				return false;
			}
		} else if (!this.collectionCensoredName.equals(other.collectionCensoredName)) {
			return false;
		}
		if (this.collectionExplicitness == null) {
			if (other.collectionExplicitness != null) {
				return false;
			}
		} else if (!this.collectionExplicitness.equals(other.collectionExplicitness)) {
			return false;
		}
		if (this.collectionHdPrice == null) {
			if (other.collectionHdPrice != null) {
				return false;
			}
		} else if (!this.collectionHdPrice.equals(other.collectionHdPrice)) {
			return false;
		}
		if (this.collectionId == null) {
			if (other.collectionId != null) {
				return false;
			}
		} else if (!this.collectionId.equals(other.collectionId)) {
			return false;
		}
		if (this.collectionName == null) {
			if (other.collectionName != null) {
				return false;
			}
		} else if (!this.collectionName.equals(other.collectionName)) {
			return false;
		}
		if (this.collectionPrice == null) {
			if (other.collectionPrice != null) {
				return false;
			}
		} else if (!this.collectionPrice.equals(other.collectionPrice)) {
			return false;
		}
		if (this.collectionViewUrl == null) {
			if (other.collectionViewUrl != null) {
				return false;
			}
		} else if (!this.collectionViewUrl.equals(other.collectionViewUrl)) {
			return false;
		}
		if (this.country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!this.country.equals(other.country)) {
			return false;
		}
		if (this.currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!this.currency.equals(other.currency)) {
			return false;
		}
		if (this.feedUrl == null) {
			if (other.feedUrl != null) {
				return false;
			}
		} else if (!this.feedUrl.equals(other.feedUrl)) {
			return false;
		}
		if (this.genreIds == null) {
			if (other.genreIds != null) {
				return false;
			}
		} else if (!this.genreIds.equals(other.genreIds)) {
			return false;
		}
		if (this.genres == null) {
			if (other.genres != null) {
				return false;
			}
		} else if (!this.genres.equals(other.genres)) {
			return false;
		}
		if (this.kind == null) {
			if (other.kind != null) {
				return false;
			}
		} else if (!this.kind.equals(other.kind)) {
			return false;
		}
		if (this.podcastEpisodes == null) {
			if (other.podcastEpisodes != null) {
				return false;
			}
		} else if (!this.podcastEpisodes.equals(other.podcastEpisodes)) {
			return false;
		}
		if (this.primaryGenreName == null) {
			if (other.primaryGenreName != null) {
				return false;
			}
		} else if (!this.primaryGenreName.equals(other.primaryGenreName)) {
			return false;
		}
		if (this.radioStationUrl == null) {
			if (other.radioStationUrl != null) {
				return false;
			}
		} else if (!this.radioStationUrl.equals(other.radioStationUrl)) {
			return false;
		}
		if (this.releaseDate == null) {
			if (other.releaseDate != null) {
				return false;
			}
		} else if (!this.releaseDate.equals(other.releaseDate)) {
			return false;
		}
		if (this.trackCensoredName == null) {
			if (other.trackCensoredName != null) {
				return false;
			}
		} else if (!this.trackCensoredName.equals(other.trackCensoredName)) {
			return false;
		}
		if (this.trackCount == null) {
			if (other.trackCount != null) {
				return false;
			}
		} else if (!this.trackCount.equals(other.trackCount)) {
			return false;
		}
		if (this.trackExplicitness == null) {
			if (other.trackExplicitness != null) {
				return false;
			}
		} else if (!this.trackExplicitness.equals(other.trackExplicitness)) {
			return false;
		}
		if (this.trackHdPrice == null) {
			if (other.trackHdPrice != null) {
				return false;
			}
		} else if (!this.trackHdPrice.equals(other.trackHdPrice)) {
			return false;
		}
		if (this.trackHdRentalPrice == null) {
			if (other.trackHdRentalPrice != null) {
				return false;
			}
		} else if (!this.trackHdRentalPrice.equals(other.trackHdRentalPrice)) {
			return false;
		}
		if (this.trackId == null) {
			if (other.trackId != null) {
				return false;
			}
		} else if (!this.trackId.equals(other.trackId)) {
			return false;
		}
		if (this.trackName == null) {
			if (other.trackName != null) {
				return false;
			}
		} else if (!this.trackName.equals(other.trackName)) {
			return false;
		}
		if (this.trackPrice == null) {
			if (other.trackPrice != null) {
				return false;
			}
		} else if (!this.trackPrice.equals(other.trackPrice)) {
			return false;
		}
		if (this.trackRentalPrice == null) {
			if (other.trackRentalPrice != null) {
				return false;
			}
		} else if (!this.trackRentalPrice.equals(other.trackRentalPrice)) {
			return false;
		}
		if (this.trackViewUrl == null) {
			if (other.trackViewUrl != null) {
				return false;
			}
		} else if (!this.trackViewUrl.equals(other.trackViewUrl)) {
			return false;
		}
		if (this.wrapperType == null) {
			if (other.wrapperType != null) {
				return false;
			}
		} else if (!this.wrapperType.equals(other.wrapperType)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.artistName + " " + this.feedUrl + " " + this.podcastEpisodes;
	}

}