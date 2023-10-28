package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	private static Stage stg;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/usuario.fxml"));
			Scene scene = new Scene(root,400,220);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			Image icono = new Image(Main.class.getResourceAsStream("/img/avion.png"));
			primaryStage.getIcons().add(icono);
			primaryStage.setTitle("AVIONES-LOGIN");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) {
		try {
			Parent padre = FXMLLoader.load(getClass().getResource(fxml));
			if (stg != null) {
				stg.getScene().setRoot(padre);
				stg.setHeight(600);
				stg.setWidth(900);
				stg.setResizable(true);
				stg.setTitle("AVIONES-AEROPUERTOS");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
