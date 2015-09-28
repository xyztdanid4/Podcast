package szakdolgozat.podcast.morphia;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

public class main {
	public static void main(String[] args) {
		Morphia morphia = new Morphia();
		// morphia.mapPackage("morphia.Emberek");
		final Datastore datastore = morphia.createDatastore(new MongoClient(),
				"testDB");
		datastore.ensureIndexes();

		// INSERT
		// Ember ember = new Ember("dani", 27, "Bp", "developer");
		// datastore.save(ember);

		// SELECT* to OBJECTS
		Query<Ember> query = datastore.createQuery(Ember.class);
		List<Ember> emberList = query.asList();
		System.out.println(emberList);

		// SELECT - ELLENÖRZI H VAN E ILYEN
		boolean check = datastore.createQuery(Ember.class).field("varos")
				.equals("bp");
		System.out.println(check);

		// SELECT
		List<Ember> query2 = datastore.createQuery(Ember.class)
				.filter("kor = ", 25).filter("kor = ", 26).asList();
		System.out.println(query2);

		List<Ember> query3 = datastore.createQuery(Ember.class)
				.filter("stringek =", 25).asList();
		System.out.println(query3);

	}
}
