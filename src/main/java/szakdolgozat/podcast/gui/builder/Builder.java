package szakdolgozat.podcast.gui.builder;

/**
 * The Interface Builder.
 *
 * @author Daniel Toth
 * @version 0.0.1
 * @since 0.0.1
 * 
 *        This is a basic interface for the builder objects. Only contains one
 *        method, cause the other common methods are static. Basically it is a
 *        wrapper class that makes easier to make instance of objects.
 */

public interface Builder {

	/**
	 * Builds the object after we assembled them in the derived objects.
	 *
	 * @return the object
	 */
	public Object build();
}
