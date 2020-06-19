package util;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Login {
	
		@FXML
		private TextField TextFusername;
		@FXML
		private TextField TextFpassword;
		
		
		
		public Login(TextField textFusername, TextField textFpassword) {
			super();
			TextFusername = textFusername;
			TextFpassword = textFpassword;
		}



		public void checkLogin()
		{
			ClassUser user = new ClassUser();
			user.setUsername(TextFusername.getText());
			user.setPassword(TextFpassword.getText());
			Integer acess = user.LoginAcess(user);
			if (acess==0)
				Alerts.showAlert("ACESSO NEGADO", null, ":(", Alert.AlertType.ERROR);
			else if (acess==-1)
				Alerts.showAlert("ACESSO NEGADO/USUARIO NAO ENCONTRADO", null, ":(", Alert.AlertType.ERROR);
			else
				Alerts.showAlert("ACESSO CONCEDIDO", null, ":)", Alert.AlertType.CONFIRMATION);
		}
			
			
	

}
