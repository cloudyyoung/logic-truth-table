package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GeneratorApp extends Application {
    
	/**
	 * The entry point of the program.
	 */
	public void start(Stage primaryStage) throws Exception {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Window.fxml"));
			BorderPane pane = new BorderPane();
			pane.setTop(root);
			pane.setPadding(new Insets(0, 30, 0, 30));

			Scene scene = new Scene(pane);
			scene.getStylesheets().add("/view/application.css");
			primaryStage.setTitle("Truth Table Generator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
