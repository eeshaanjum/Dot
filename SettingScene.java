/*
 * @Eesha Anjum
 * the scene below asks the user for thier name and color of choice and then puts those values in a xml file for game app to know 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingScene extends Application {
	 public void start(Stage primaryStage) {
		 Label titleLabel = new Label("Settings for dots game"); // title
	        titleLabel.setFont(new Font("Arial", 30));
	        
	        GridPane loginGrid = new GridPane(); // grid pane for input fields and button
	        loginGrid.setVgap(8); // vertical spacing
	        loginGrid.setHgap(8); // horizontal spacing
	        loginGrid.setAlignment(Pos.CENTER);
	        
	        Label playernameLabel = new Label("Player's name:"); // player's name label
	        TextField playernameInput = new TextField(); //the box for player to write thier name 
	        Label colorLabel = new Label("Color of choice:"); // player's color of choice 
	        PasswordField colorInput = new PasswordField();  // the box to write the color
	        
	        loginGrid.add(playernameLabel, 0, 0);
	        loginGrid.add(playernameInput, 1, 0);
	        loginGrid.add(colorLabel, 0, 1);
	        loginGrid.add(colorInput, 1, 1);
	        
	        Button loginBtn = new Button("Login");
	        loginGrid.add(loginBtn, 1, 2);
	        loginBtn.setOnAction(e -> {
				try {
					writeOptions(playernameInput.getText(), colorInput.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}); // puts the data into the xml file for game gui to know
	       
	        
	       
	     //   loginGrid.add(settingbtn, 1, 3);
	      //  settingbtn.setOnAction(e -> primaryStage.setScene(scene1));
	        
	        
	      
	        VBox layout = new VBox(10.0); // main layout
	        layout.setAlignment(Pos.CENTER);
	        layout.getChildren().addAll(titleLabel, loginGrid); // add title and loginGrid to main layout
	       Scene scene = new Scene(layout, 300, 250);
	       primaryStage.setScene(scene);
	        primaryStage.show();
		 
	 }
	 /**
	  * this method writes the xml file
	  * @param player (players name)
	  * @param color  (player's color)
	  * @throws FileNotFoundException
	  */
	 public synchronized void writeOptions(String player, String color) throws FileNotFoundException {
		 PrintWriter fileWriter = new PrintWriter(new File("Options.xml"));
		 String option = "<player>" + player + "</player>" + "<color>" + color + "</color>";
		 fileWriter.print(option);
		 fileWriter.flush();
		 
	 }
	 public static void main(String[] args) {
	        
	    }
}
