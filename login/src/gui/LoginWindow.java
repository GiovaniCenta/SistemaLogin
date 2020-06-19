package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Login;
import util.Register;

public class LoginWindow implements Initializable{
	
	@FXML
	private TextField TextFusername;
	@FXML
	private TextField TextFpassword;
	@FXML
	private Button ButtonRegister;
	@FXML
	private Button ButtonLogin;
	
	
	
	@FXML
	void ButtonRegisterAction() {
		Register reg = new Register();
		reg.regStart();
	}
	
	@FXML
	public void ButtonLoginAction() {
		Login login = new Login(TextFusername,TextFpassword);
		login.checkLogin();
		
	};
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
