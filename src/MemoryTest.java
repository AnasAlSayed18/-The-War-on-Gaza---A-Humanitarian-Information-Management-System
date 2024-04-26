import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MemoryTest extends Application{
	public void start(Stage primaryStage) {
		try {
			 BorderPane root;
			 ArrayList<Martyr> nameList = new ArrayList<Martyr>();
			 Label[] nameLabels = new  Label[4];
			 TextField  first = new TextField();
			 TextField  second = new TextField();
			 Button submit = new Button();
			 Button clear = new Button();
			 Label response= new Label();
			
			 
			 root = new BorderPane();

			
			
			
			FileInputStream  inFile = new FileInputStream(new File("MartyrsList.dat"));
		    
			
			nameList.add(new Martyr("martyrName","dateOfMartyrdom"));
			
			
			Scene scene = new Scene(root,600,370);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 public boolean inList(String word ) {
		  return true;
	 }
}
