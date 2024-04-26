//1221020 Anas Al Sayed
package application;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MemoryTest extends Application {
	// ArrayList to store Martyr objects.
	private ArrayList<Martyr> nameList=new ArrayList<>();

	// Array of Labels for UI.
	private Label LabelsList[]= new Label[5] ;

	// Array of Labels for displaying Martyr names.
	private Label nameLabels[] ;

	// Array of HBox for arranging UI elements
	private HBox hbox[]=new HBox[3] ;

	// TextFields for user input.
	private TextField first=new TextField();
	private TextField second=new TextField();

	// Buttons for user actions.
	private Button submit=new Button();
	private Button clear=new Button();

	// Label to display responses.
	private Label response=new Label();

	// TilePane for displaying Martyr names.
	TilePane tile;

	// BorderPane for organizing the layout.
	BorderPane root;
	// VBox and GridPane for organizing UI elements vertically.
	VBox vbox;
	GridPane GridPane;
	// Stage for the application.
	Stage stage;
	// Stages for error message.
	Stage fileError;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Initialize stage to be a new window.
			stage = new Stage();
			// Initialize stages for error message.
			fileError =new Stage();

			// Read Martyr data from the file.
			try {
			readList ();
			}catch(java.io.FileNotFoundException ex){
				// Show file error message.
				fileError.setScene(fileError());
				fileError.show();
				}
			

			// Initialize TilePane to show the martyr names .
			tile = new TilePane();

			// Set up the main layout using BorderPane.
			root =new BorderPane();

			// Initialize UI components.
			hbox[0] =new HBox();
			hbox[1] =new HBox();
			hbox[2] =new HBox();
			GridPane = new GridPane();

			// Labels for UI.
			LabelsList[0]=new Label("martyred before:");
			LabelsList[1]=new Label("Test your memory");
			LabelsList[2]=new Label("Hey, my friend! Test your memory to see if you remember who was martyred before.");
			LabelsList[3]=new Label("Pick two Martyr names from the following list, enter them in the boxes in the correct order (date of death), and then press\n the Submit button.");
			Font font=new Font("Verdana",25);
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD, 25));


			

			// Set up VBox for introductory labels.
			vbox = new VBox();
			vbox.setPadding(new Insets(60));
			vbox.getChildren().addAll( LabelsList[1],LabelsList[2],LabelsList[3]);
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
			root.setTop(vbox);

			// ComboBox for selecting background color.
			String []colorList= {"Red","Blue","Green","Yellow","Default"};
			ComboBox c_box =new ComboBox(FXCollections.observableArrayList(colorList));
			c_box.setValue("Color");

			// Define action for c_box.
			c_box.setOnAction(e->{
				ComboBox(c_box);
			});

			// Set up HBox for entering Martyr names and a label.
			hbox[0].getChildren().addAll(first,LabelsList[0],second);
			hbox[0].setAlignment(Pos.CENTER);
			hbox[0].setPadding(new Insets(15));
			hbox[0].setSpacing(15);
			GridPane.add(hbox[0],0,0);

			// Set up HBox for buttons and ComboBox.
			hbox[1].getChildren().addAll(submit=new Button("Submit"),clear=new Button("Clear"),c_box);
			hbox[1].setAlignment(Pos.CENTER);
			hbox[1].setPadding(new Insets(15));
			hbox[1].setSpacing(15);
			GridPane.add(hbox[1],0,1);

			// Set up HBox for response label.
			hbox[2].getChildren().addAll(response=new Label(" "));
			hbox[2].setAlignment(Pos.CENTER);
			hbox[2].setPadding(new Insets(15));
			hbox[2].setSpacing(15);
			GridPane.add(hbox[2],0,2);

			// Configure GridPane layout
			GridPane.setHgap(2);
			GridPane.setVgap(2);
			GridPane.setAlignment(Pos.CENTER);
			GridPane.setPadding(new Insets(10, 10, 10, 10));
			root.setBottom(GridPane);

			// Define action for Clear button.
			clear.setOnAction(e->{
				first.setText("");
				second.setText("");
				response.setText("");
			});

			// Define action for Submit button.
			submit.setOnAction(e->{
				submit( first, second, response, nameList);
			});

			// Add Martyr names to TilePane . 
			nameLabels= new Label[nameList.size()];
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i]=new Label(nameList.get(i).getMartyrName());

				tile.getChildren().add(nameLabels[i]);
			}


			tile.setAlignment(Pos.CENTER);
			tile.setHgap(20);
			tile.setPadding(new Insets(10, 10, 10, 10));
			root.setCenter(tile);

			// Create and show the scene.
			Scene scene=new Scene(root,700,700);
			stage.setScene(scene);
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	// Method to get the index of a Martyr in the list based on the name.
	public static int getMartyrIndex(String name1,ArrayList<Martyr> list){
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getMartyrName().equals(name1)) {
				return i;
			}
		}

		return -1;
	}




	// Method to handle ComboBox events for changing background colors.
	public  void ComboBox(ComboBox c_box){
		// Define style strings for different colors.
		String s1="-fx-text-fill: red";
		String s2="-fx-text-fill: white";
		String s3="-fx-text-fill: orange";
		String s4="-fx-text-fill: NAVAJOWHITE";
		String s5="-fx-text-fill: black";

		// Apply styles based on ComboBox value.
		if(c_box.getValue().equals("Default")) {

			// Default color settings.
			root.setStyle("-fx-background-color:white");
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD, 25));

			// Apply styles to various UI elements.
			LabelsList[0].setStyle(s5);
			LabelsList[1].setStyle(s5);
			LabelsList[2].setStyle(s5);
			LabelsList[3].setStyle(s5);
			response.setStyle(s5);
			submit.setStyle(s5);
			clear.setStyle(s5);
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i].setStyle(s5);
			}

		}else if(c_box.getValue().equals("Red")) {

			// Red color settings
			root.setStyle("-fx-background-color:Red");
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 25));

			// Apply styles to various UI elements.
			LabelsList[0].setStyle(s2);
			LabelsList[1].setStyle(s2);
			LabelsList[2].setStyle(s2);
			LabelsList[3].setStyle(s2);
			response.setStyle(s2);
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i].setStyle(s2);
			}

		}
		else if(c_box.getValue().equals("Green")) {

			// Green color settings.
			root.setStyle("-fx-background-color:Green");
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 25));

			// Apply styles to various UI elements.
			LabelsList[0].setStyle(s4);
			LabelsList[1].setStyle(s4);
			LabelsList[2].setStyle(s4);
			LabelsList[3].setStyle(s4);
			response.setStyle(s4);
			submit.setStyle(s5);
			clear.setStyle(s5);
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i].setStyle(s4);
			}
		}
		else if(c_box.getValue().equals("Blue")) {

			// Blue color settings.
			root.setStyle("-fx-background-color:DARKBLUE");
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 25));

			// Apply styles to various UI elements.
			LabelsList[0].setStyle(s3);
			LabelsList[1].setStyle(s3);
			LabelsList[2].setStyle(s3);
			LabelsList[3].setStyle(s3);
			response.setStyle(s3);
			submit.setStyle(s5);
			clear.setStyle(s5);
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i].setStyle(s3);
			}
		}
		else if(c_box.getValue().equals("Yellow")) {

			// Yellow color settings.
			root.setStyle("-fx-background-color:gold");
			LabelsList[1].setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 25));

			// Apply styles to various UI elements.
			LabelsList[0].setStyle(s1);
			LabelsList[1].setStyle(s1);
			LabelsList[2].setStyle(s1);
			LabelsList[3].setStyle(s1);
			response.setStyle(s1);
			submit.setStyle(s1);
			clear.setStyle(s1);
			for(int i=0;i<nameList.size();i++) {
				nameLabels[i].setStyle(s1);
			}


		}


	}

	// Method to handle Submit button events.
	public  void submit(TextField first,TextField second,Label response,ArrayList<Martyr> nameList){

		// Empty input.
		if(first.getText().equalsIgnoreCase("") && second.getText().equalsIgnoreCase("") ) {

			// Make first and second TextField Empty and change the response message.
			first.setText("");
			second.setText("");
			response.setText("Enter names in both boxes. Then press Submit.");

		}

		// Same names entered.
		else if (first.getText().equalsIgnoreCase(second.getText())) {

			// Make first and second TextField Empty and change the response message and color.
			first.setText("");
			second.setText("");
			response.setText("You entered the same names. Try again.");
			response.setTextFill(Color.RED);

		}

		// Both names not in the list.
		else if (inList(first.getText())== false && inList(second.getText() )== false) {

			// Make first and second TextField Empty and change the response message and color.
			first.setText("");
			second.setText("");
			response.setText("You  entry not in name list – check spelling.");
			response.setTextFill(Color.RED);

		}

		// First name not in the list
		else if (inList(first.getText())== false ) {

			// Make first and second TextField Empty and change the response message and color.
			first.setText("");
			second.setText("");
			response.setText("First entry not in name list – check spelling.");
			response.setTextFill(Color.RED);

		}

		// Second name not in the list
		else if (inList(second.getText() )== false) {

			// Make first and second TextField Empty and change the response message and color.
			first.setText("");
			second.setText("");
			response.setText("Second entry not in name list – check spelling. ");
			response.setTextFill(Color.RED);

		}

		else {

			// Compare dates if both names are in the list.
			int index1 =getMartyrIndex(first.getText(),nameList);
			int index2 =getMartyrIndex(second.getText(),nameList);

			int result = nameList.get(index1).compareTo( nameList.get(index2));

			// Correct order(martyr1 martyred before martyr2)
			if (result < 0) {

				// Make first and second TextField Empty and change the response message and color.
				first.setText("");
				second.setText("");
				response.setText("You are correct!");
				response.setTextFill(Color.GREEN);

				// Incorrect order
			} else if (result > 0) {

				// Make first and second TextField Empty and change the response message and color.
				first.setText("");
				second.setText("");
				response.setText(" Wrong. Try again.");
				response.setTextFill(Color.RED);

			} else {
				System.out.println("Error");
			}


		}
	}



	// Method to check if a name is in the list.
	public  boolean inList(String name1){
		for(int i=0;i<nameList.size();i++) {
			if(nameList.get(i).getMartyrName().equals(name1)) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Martyr> getNameList() {
		return nameList;
	}

	// Method to create the file error scene.
		public  Scene fileError() {
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
			fileError.setTitle("File Error");

			return scene3;
		}

		// Method to read Martyr data from the file.
	public ArrayList<Martyr> readList () throws IOException{
		
		// Create FileInputStream to Read data from file.
		FileInputStream inFile = new FileInputStream(new File("MartyrsList.dat"));

		try (DataInputStream read = new DataInputStream(inFile)) {

			while(read.available()>0) {
				String line = read.readUTF();
				String []token = line.split(" ") ;
				if(nameList.size()==0) {
					nameList.add(new Martyr(token[0],token[1]));
				}
				else {
					if(inList( token[0])==false) {
						nameList.add(new Martyr(token[0],token[1]));

					}
				}
			}
		}
		
		return nameList;
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}