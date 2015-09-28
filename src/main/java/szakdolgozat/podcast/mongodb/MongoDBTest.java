package szakdolgozat.podcast.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoDBTest {

	private MongoClient mongoClient;
	private MongoDatabase database; // database
	private MongoCollection<Document> table; // table //collection

	public MongoDBTest() {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("testDB");
		table = database.getCollection("testCollection");
		// db.createCollection("testCollection");
	}

	void insertOne(String key, String value) {
		table.insertOne(new Document(key, value));
	}

	void displayDBs() {
		MongoIterable<String> DBs = mongoClient.listDatabaseNames();
		for (String db : DBs) {
			System.out.println(db);
		}
	}

	void displayFirstCollection() {
		Document myDoc = table.find().first();
		System.out.println(myDoc.toJson());
	}

	void displayCollection() {
		MongoCursor<Document> cursor = table.find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
	}

	void insertOneDoc() {
		Document doc = new Document("aaa", "1").append("aaaa", "2");
		table.insertOne(doc);
	}

}
