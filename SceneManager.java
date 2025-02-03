// Author: Chris Fietkiewicz. For Project #4.
// Description: Manages changes from one scene to another scene.
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Socket connection; // Socket connection to server
	private static Stage stage; // Stage used for all scenes
	private static PrimaryScene primaryScene; // For user login
	private static SettingScene settingsScene; // Menu for administrator accounts
	

	// Constructor
	public SceneManager() {
		settingsScene = new SettingScene();
		primaryScene = new PrimaryScene();
		
	}
	
	// Set socket connection to server
	public static void setSocket(Socket setConnection) {
		connection = setConnection;
	}

	// Get socket connection to server
	public static Socket getSocket() {
		return connection;
	}

	// Set initial stage to be used by all scenes
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
	// Change view to SettingsScene
	public static void setSettingsScene() {
		stage.setScene(settingsScene.getScene());
	}
	
	public static void setPrimaryScene() {
		stage.setScene(primaryScene.getScene());
	}
	
	
}
