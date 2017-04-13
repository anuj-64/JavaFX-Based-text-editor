
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	Stage primarystage;
	BorderPane root;
	@Override
	public  void start(Stage primaryStage) throws IOException {
		primarystage=primaryStage;
		basicload();
		Controller.addview();
	}
	public void basicload() throws IOException{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("BasicLayout.fxml"));
		root=(BorderPane)loader.load();
		Scene scene=new Scene(root);
		primarystage.setScene(scene);
		primarystage.show();
		Controller.set(primarystage, root);

		/*
		 * Mouse Listener for full Screen on Double Click
		 */
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent evt) {
				//System.out.println("click");
				if(evt.getClickCount() == 2){
					if(primarystage.isFullScreen())
						primarystage.setFullScreen(false);
					else
						primarystage.setFullScreen(true);
				}
			}
		});
	}

	public static void main(String[] args) {

		launch(args);
	}
}
