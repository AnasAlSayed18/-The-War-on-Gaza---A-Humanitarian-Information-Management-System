//1221020 Anas Al Sayed
package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
            // Create instances of your different windows
			MemoryTest memoryTest=new MemoryTest();
			AddMartyr addMartyr = new AddMartyr();

            // Set up the main layout using BorderPane
			BorderPane root=new BorderPane();
			VBox vbox=new VBox();
			
            // Create buttons for different actions
			Button bt1=new Button("Create Martyr List Window");
			Button bt2=new Button("Memory Test Window");
            
			// Add buttons to the vertical box
			vbox.getChildren().addAll(bt1,bt2);
          
			// Align the box to the center of the scene and set spacing between the buttons
			vbox.setAlignment(Pos.CENTER);
			vbox.setSpacing(10);
			
            // Set the vertical box in the center of the BorderPane
			root.setCenter(vbox);

            // Define actions for button clicks
			bt1.setOnAction(e ->{
				addMartyr.start(primaryStage);
			});
			
			bt2.setOnAction(e ->{
				memoryTest.start(primaryStage);
			});

            
			// Create the main scene
			Scene scene=new Scene(root,500,300);
          
			// Set the title and scene for the primary stage
			primaryStage.setTitle("The War on Gaza");
			primaryStage.setScene(scene);
           
			// Show the primary stage
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
