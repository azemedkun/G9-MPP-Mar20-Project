package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Stage implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;

	}

	private Text messageBar = new Text();

	public void clear() {
		messageBar.setText("");
	}

	private LoginWindow() {
	}

	public void init() {

		setTitle("Login Window");

		GridPane grid = new GridPane();

		grid.setId("top-container");

		grid.setAlignment(Pos.CENTER);

		grid.setHgap(10);
		grid.setVgap(10);

		grid.setPadding(new Insets(25, 25, 25, 25));

	
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");

		grid.add(pw, 0, 2);

		grid.setGridLinesVisible(false);

		PasswordField pwBox = new PasswordField();

		grid.add(pwBox, 1, 2);

		Button loginBtn = new Button("Log in");

		HBox hbBtn = new HBox(10);

		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

		hbBtn.getChildren().add(loginBtn);

		grid.add(hbBtn, 1, 4);

		HBox messageBox = new HBox(10);

		messageBox.setAlignment(Pos.BOTTOM_RIGHT);
		messageBox.getChildren().add(messageBar);

		grid.add(messageBox, 1, 6);

		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					ControllerInterface c = new SystemController();
					c.login(userTextField.getText().trim(), pwBox.getText().trim());

					messageBar.setFill(Start.Colors.green);
					messageBar.setText("Login successful");
					LoginWindow.INSTANCE.hide();

				} catch (LoginException ex) {

					messageBar.setFill(Start.Colors.red);
					messageBar.setText("Error! " + ex.getMessage());
				}

			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);

		grid.add(hBack, 0, 7);

		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);

	}
}
