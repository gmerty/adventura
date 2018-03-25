package com.github.gmerty.adventura.ui;

import com.github.gmerty.adventura.logika.Hra;
import com.github.gmerty.adventura.logika.IHra;
import com.github.gmerty.adventura.ui.HomeController;
import com.github.gmerty.adventura.ui.TextoveRozhrani;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
	}
	/**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
		          .getResource("Scene2.fxml"));
		Parent root = loader.load();

		HomeController controller = loader.getController();
		controller.inicializuj(new Hra());
		
        primaryStage.setTitle("title");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
		
	}

}

