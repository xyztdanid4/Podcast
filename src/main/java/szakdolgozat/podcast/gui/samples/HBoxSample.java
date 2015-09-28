package szakdolgozat.podcast.gui.samples;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HBoxSample extends HBox {
	private final int DEAFUALTSPACING = 15;

	public HBoxSample() {
		setSpacing(DEAFUALTSPACING);
		// setPadding(new Insets(5, 0, 5, 5));
	}

	public HBoxSample(Node component1) {
		setSpacing(DEAFUALTSPACING);
		getChildren().add(component1);
		// setPadding(new Insets(5, 0, 5, 5));
		setAlignment(Pos.CENTER_LEFT);
	}

	public HBoxSample(Node component1, Node component2) {
		setSpacing(DEAFUALTSPACING);
		getChildren().add(component1);
		getChildren().add(component2);
		// setPadding(new Insets(5, 0, 5, 5));
		setAlignment(Pos.CENTER_LEFT);
	}

	public HBoxSample(Node component1, Node component2, Node component3) {
		setSpacing(DEAFUALTSPACING);
		getChildren().add(component1);
		getChildren().add(component2);
		getChildren().add(component3);
		// setPadding(new Insets(30, 30, 30, 30));
		setAlignment(Pos.CENTER_LEFT);
	}

	public HBoxSample(Node component1, Node component2, Node component3,
			Node component4) {
		setSpacing(DEAFUALTSPACING);
		getChildren().add(component1);
		getChildren().add(component2);
		getChildren().add(component3);
		getChildren().add(component4);
		// setPadding(new Insets(5, 5, 5, 30));
		setAlignment(Pos.CENTER_LEFT);
	}
}
