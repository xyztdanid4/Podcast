package szakdolgozat.podcast.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import szakdolgozat.podcast.data.podcast.PodcastEpisode;

/**
 * The Class XmlParser.
 * 
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 */
public class XmlParser {

	/** The title. */
	static private String TITLE = "title";

	/** The author. */
	static private String AUTHOR = "author";

	/** The subtitle. */
	static private String SUBTITLE = "subtitle";

	/** The summary. */
	static private String SUMMARY = "summary";

	/** The guid. */
	static private String GUID = "guid";

	/** The pubdate. */
	static private String PUBDATE = "pubDate";

	/** The duration. */
	static private String DURATION = "duration";

	/** The image. */
	static private String IMAGE = "image";

	/** The item. */
	static private String ITEM = "item";

	/** The href. */
	static private String HREF = "href";

	/** The url. */
	final URL url;

	/** The title. */
	private String title;

	/** The author. */
	private String author;

	/** The subtitle. */
	private String subtitle;

	/** The summary. */
	private String summary;

	/** The guid. */
	private String guid;

	/** The pub date. */
	private String pubDate;

	/** The duration. */
	private String duration;

	/** The image. */
	private String image;

	/** The episodes. */
	private List<PodcastEpisode> episodes;

	/** The podcast episode. */
	private PodcastEpisode podcastEpisode;

	/**
	 * Instantiates a new xml parser.
	 *
	 * @param feedUrl
	 *            the feed url
	 */
	public XmlParser(final String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (final MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Read feed.
	 *
	 * @return the list
	 */
	public List<PodcastEpisode> readFeed() {
		this.episodes = new ArrayList<PodcastEpisode>();
		try {
			final XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			final InputStream in = readStream();
			final XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				this.podcastEpisode = new PodcastEpisode();

				if (event.isStartElement()) {
					final StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart() == (ITEM)) {

					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart().equals(TITLE)) {
							event = eventReader.nextEvent();
							try {
								this.title = event.asCharacters().getData();
							} catch (final Exception e) {
								e.printStackTrace();
								continue;
							}
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart().equals(AUTHOR)) {
						event = eventReader.nextEvent();
						try {
							this.author = event.asCharacters().getData();
						} catch (final Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(SUBTITLE)) {
						event = eventReader.nextEvent();
						try {
							this.subtitle = event.asCharacters().getData();
						} catch (final Exception e) {
							e.printStackTrace();
							System.out.println("subtitle exception");
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(SUMMARY)) {
						event = eventReader.nextEvent();
						try {
							this.summary = event.asCharacters().getData();
						} catch (final java.lang.ClassCastException e) {
							this.summary = "";
							e.printStackTrace();
							System.out.println("summary exception");
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(GUID)) {
						event = eventReader.nextEvent();
						try {
							this.guid = event.asCharacters().getData();
						} catch (final Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(PUBDATE)) {
						event = eventReader.nextEvent();
						try {
							this.pubDate = event.asCharacters().getData();
						} catch (final Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(DURATION)) {
						event = eventReader.nextEvent();
						try {
							this.duration = event.asCharacters().getData();
						} catch (final Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart().equals(IMAGE)) {
						event = eventReader.nextEvent();
						final Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							final Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(HREF)) {
								try {
									this.image = attribute.getValue();
								} catch (final Exception e) {
									e.printStackTrace();
									this.image = "Blank.png";
									continue;
								}
							}
						}
						continue;
					}

				}
				if (event.isEndElement()) {
					final EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (ITEM)) {
						this.episodes.add(new PodcastEpisode(this.title, this.author, this.subtitle, this.summary,
								this.guid, this.pubDate, this.duration, this.image));
					}
				}
			}
		} catch (final XMLStreamException e) {
			e.printStackTrace();
			System.out.println("XMLStreamExceptionSajat");
		}
		return this.episodes;
	}

	/**
	 * Read stream.
	 *
	 * @return the input stream
	 */
	private InputStream readStream() {
		try {
			return this.url.openStream();
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
