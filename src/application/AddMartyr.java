//1221020 Anas Al Sayed
package application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddMartyr extends Application {

	// ArrayList to store Martyr objects.
	ArrayList<Martyr> nameList=new ArrayList<>();

	Stage stage;
	// Stages for error messages.
	Stage dateError;
	Stage spaceError;
	Stage nameError;

	MemoryTest m = new MemoryTest();
	public void start(Stage primaryStage)  {
		try {

			// Create FileOutputStream to write data to a file.
			FileOutputStream outFile = new FileOutputStream(new File("MartyrsList.dat"),true);
			DataOutputStream dataOutputStream = new DataOutputStream(outFile);

			// Initialize stage to be a new window.
			stage=new Stage();

			// Initialize stages for error messages.
			dateError =new Stage();
			spaceError =new Stage();
			nameError= new Stage();

			// Create GridPane for the main window.
			GridPane root = new GridPane();

			// Create components for different actions.
			TextField  field = new TextField();
			Button addToFile = new Button("Add To File");
			Label l1= new Label("Add Martyr (Name  date Of Martyrdom)");

			// Add components to the GridPane.
			root.add(l1, 2, 5);
			root.add(field, 3, 5);
			root.add(addToFile, 4, 5);
			root.setHgap(5);
			root.setVgap(5);
			root.setAlignment(Pos.CENTER);


			// Define action for the "Add To File" button.
			addToFile.setOnAction(E->{
				String token =field.getText();
				String [] str = token.split(" ");

				if(str.length==0||str.length==1) {
					// Show space error message.
					spaceError.setScene(spaceError());
					spaceError.show();
				}

				else {

					if(compareTo(str[1])==true) {
						try {
							if(m.readList().size()==0) {
								dataOutputStream.writeUTF(str[0]+" "+str[1]+"\n") ;

							}
							else {
								if(inList( str[0])==false) {
									dataOutputStream.writeUTF(str[0]+" "+str[1]+"\n") ;

								}
								else
								{
									// Show name error message.
									nameError.setScene(nameError());
									nameError.show();
								}}
						}
						catch (IOException ex) {
							// Show date error message.
							dateError.setScene(dateError());
							dateError.show();

						}}
					else {
						// Show date error message.
						dateError.setScene(dateError());
						dateError.show();
					}
				}
			}
					);


			// Create the main scene.
			Scene scene = new Scene(root,500,100);

			// Set the title and scene for the  stage.
			stage.setTitle("Add Martyr To File");
			stage.setScene(scene);

			// Show the stage.
			stage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	// Method to compare date input.
	public boolean compareTo(String o) {
		try {
			Date testDate=new Date();
			long firstLong=testDate.parse(o);
			Date date1 = new Date(firstLong);

			// Create a new Date object for the current date.
			Date date2 = new Date();

			// Compare the two dates and return the result.
			int result = date1.compareTo(date2);
			if (result < 0) {

				return true;

			} else {
				return false;
			}}catch (IllegalArgumentException e) {
				return false;

			}
	}

	// Method to create the date error scene.
	public  Scene dateError() {
		GridPane root = new GridPane();
		Label l1= new Label("Error, please enter a correct date -_-");

		l1.setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 12));
		l1.setStyle("-fx-text-fill: red");


		root.add(new Label(""), 2, 5);
		root.add(l1, 3, 4);
		root.add(new Label(""), 4, 5);
		root.setHgap(5);
		root.setVgap(5);
		root.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(root,400,60);
		dateError.setTitle("Date Error");
		return scene1;
	}

	// Method to create the space error scene.
	public  Scene spaceError() {
		GridPane root = new GridPane();
		Label l1= new Label("Error,Enter names and date in the boxe. Then press Add To File.-_-");

		l1.setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 12));
		l1.setStyle("-fx-text-fill: red");


		root.add(new Label(""), 2, 5);
		root.add(l1, 3, 4);
		root.add(new Label(""), 4, 5);
		root.setHgap(5);
		root.setVgap(5);
		root.setAlignment(Pos.CENTER);


		Scene scene2 = new Scene(root,600,60);
		spaceError.setTitle("Space Error");
		return scene2;
	}

	// Method to create the name error scene.
	public  Scene nameError() {
		GridPane root = new GridPane();
		Label l1= new Label("Error, this Martyr is in the list -_-");

		l1.setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 12));
		l1.setStyle("-fx-text-fill: red");


		root.add(new Label(""), 2, 5);
		root.add(l1, 3, 4);
		root.add(new Label(""), 4, 5);
		root.setHgap(5);
		root.setVgap(5);
		root.setAlignment(Pos.CENTER);


		Scene scene3 = new Scene(root,400,60);
		dateError.setTitle("Date Error");

		return scene3;
	}

	// Method to check if a name is already in the list
	public  boolean inList(String name1) throws IOException{


		for(int i=0;i<m.readList().size();i++) {
			if(m.readList().get(i).getMartyrName().equalsIgnoreCase(name1)) {
				return true;
			}
		}

		return false;
	}

	

	public static void main(String[] args) {
		launch(args);
	}
}

