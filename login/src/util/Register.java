package util;

import java.io.IOException;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register {

	public Register() {};

	public void regStart() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/RegisterView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Register");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private TextField TextFusername;
	@FXML
	private TextField TextFpassword;
	@FXML
	private TextField TextFemail;
	@FXML
	private Button ButtonOk;
	
	public void ButtonOkAction() {
		
		ClassUser user = new ClassUser();
		
		user.setUsername(TextFusername.getText());
		user.setPassword(TextFpassword.getText());
		user.setEmail(TextFemail.getText());
		
		
		user.insertUser(user);
	}
	}
	
	






