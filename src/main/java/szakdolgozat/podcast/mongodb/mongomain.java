package szakdolgozat.podcast.mongodb;

public class mongomain {
	public static void main(String[] args) {
		MongoDBTest mongoDBTest = new MongoDBTest();
		// System.out.println("insert");
		// mongoDBTest.insertOne("dani", "1");
		// mongoDBTest.insertOne("fanni", "2");
		// mongoDBTest.insertOne("balazs", "3");

		System.out.println("databases:");
		mongoDBTest.displayDBs();
		System.out.println("collection first element:");
		mongoDBTest.displayFirstCollection();
		System.out.println("collection elements:");
		mongoDBTest.displayCollection();
		System.out.println("insertOneDoc");
		mongoDBTest.insertOneDoc();
	}
}
