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

public class XmlParser {

	static private String TITLE = "title";
	static private String AUTHOR = "author";
	static private String SUBTITLE = "subtitle";
	static private String SUMMARY = "summary";
	static private String GUID = "guid";
	static private String PUBDATE = "pubDate";
	static private String DURATION = "duration";
	static private String IMAGE = "image";
	static private String ITEM = "item";
	static private String HREF = "href";
	final URL url;
	private String title;
	private String author;
	private String subtitle;
	private String summary;
	private String guid;
	private String pubDate;
	private String duration;
	private String image;
	private List<PodcastEpisode> episodes;
	private PodcastEpisode podcastEpisode;

	public XmlParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<PodcastEpisode> readFeed() {
		episodes = new ArrayList<PodcastEpisode>();

		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			InputStream in = readStream();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				podcastEpisode = new PodcastEpisode();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart() == (ITEM)) {

					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(TITLE)) {
							event = eventReader.nextEvent();
							try {
								title = event.asCharacters().getData();
							} catch (Exception e) {
								e.printStackTrace();
								continue;
							}
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(AUTHOR)) {
						event = eventReader.nextEvent();
						try {
							author = event.asCharacters().getData();
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(SUBTITLE)) {
						event = eventReader.nextEvent();
						try {
							subtitle = event.asCharacters().getData();
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(SUMMARY)) {
						event = eventReader.nextEvent();
						try {
							summary = event.asCharacters().getData();
						} catch (java.lang.ClassCastException e) {
							summary = "";
							e.printStackTrace();

							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(GUID)) {
						event = eventReader.nextEvent();
						try {
							guid = event.asCharacters().getData();
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(PUBDATE)) {
						event = eventReader.nextEvent();
						try {
							pubDate = event.asCharacters().getData();
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(DURATION)) {
						event = eventReader.nextEvent();
						try {
							duration = event.asCharacters().getData();
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(IMAGE)) {
						event = eventReader.nextEvent();
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(HREF)) {
								try {
									image = attribute.getValue();
								} catch (Exception e) {
									e.printStackTrace();
									image = "Blank.png";
									continue;
								}
							}
						}
						continue;
					}

				}
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (ITEM)) {
						episodes.add(new PodcastEpisode(title, author,
								subtitle, summary, guid, pubDate, duration,
								image));
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return episodes;
	}

	private InputStream readStream() {
		try {
			return url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
