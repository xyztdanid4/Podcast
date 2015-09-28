package szakdolgozat.podcast.gui.samples;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewSample extends TreeView {
	private final int DEAFAULTPREFWIDTH = 800;
	private final int DEFAULTMAXWIDTH = 800;

	public TreeViewSample(TreeItem hboxSample) {
		super(hboxSample);
		setPrefWidth(800);
		setMaxWidth(800);
		setShowRoot(false);
	}
}
