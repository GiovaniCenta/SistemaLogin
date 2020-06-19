package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Objects;

import db.DB;
import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class UserControldb{
	
	
	public void insertUser(ClassUser user) {
		Connection conn = null;
		PreparedStatement st = null;
		Integer flag=0;
		try {
			conn = DB.getConnection();
			System.out.println("hello");
			flag=searchAlready(conn,user);
			
			if (flag==1)
				Alerts.showAlert("ERRO", null, "Usuário já cadastrado", AlertType.ERROR);
			else if (flag==2)
				Alerts.showAlert("ERRO", null, "Email já cadastrado", AlertType.ERROR);
			else if (flag==3)
				Alerts.showAlert("ERRO", null, "Usuario e email já cadastrados", AlertType.ERROR);
			
			else if (flag==0)
			{
			

			// EXAMPLE 1:
			st = conn.prepareStatement(
					"INSERT INTO user "  //insert into nome da tabela
					+ "(username, password, email) " //campos da tabela que eu quero preencher
					+ "VALUES " //valores
					+ "(?, ?, ?)", //aqui é um placeholder aguardando os proximos comandos de inserção
					Statement.RETURN_GENERATED_KEYS); //pra retornar a ID do novo objeto que foi inserido

			
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			int rowsAffected = st.executeUpdate(); //isso aqui é pra saber quantas linhas foram alteradas no banco de dados
			Alerts.showAlert("Success", null, "successfully registered!", AlertType.CONFIRMATION);
			}
			}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);
		}
	}
	
	public Integer searchAlready(Connection conn, ClassUser user) {

		Integer flagU = 0, flagE=0;
		Integer i=0,j=0;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			PreparedStatement st = conn.prepareStatement("select Id from user where username=?");
			st.setString(1, user.getUsername());

			rs = st.executeQuery();
			System.out.println(rs);
			
			if (rs.next()==true)
				i++;
			
			System.out.println("Numero de Registros username via contador: " + i);
			if (i!=0)
				flagU = 1;

			
				PreparedStatement st2 = null;
				try {
					st2 = conn.prepareStatement("SELECT Id from user WHERE email=?");
					st2.setString(1, user.getEmail());

					rs2 = st2.executeQuery();
					
					if (rs2.next()==true)
						j++;
						
					System.out.println("Numero de Registros email via contador: " + j);
					if (j!=0)
						flagE = 1;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		if (flagE==0 && flagU==0)
			return 0;
		else if (flagE==0 && flagU==1)
			return 1;
		else if (flagE==1 && flagU==0)
			return 2;
		else if (flagE==1 && flagU==1)
			return 3;
		return 0;
	}
	
	public Integer LoginAcess(ClassUser user) {
		Connection conn = null;
		Integer flag=0;
		try{
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		st = conn.prepareStatement("SELECT password from user where username=?");
		st.setString(1, user.getUsername());
		ResultSet rs=st.executeQuery();
		
		if (rs.next())
		{	
			Boolean l=Objects.equals(user.getPassword(), rs.getString("password"));
			System.out.println(l);
			if (l) {
				System.out.println(rs.getString("password"));
				flag=1;}
			else
				flag=0;
		}
		else
			flag=-1;
	}
	catch (SQLException e) {
		e.printStackTrace();
	} 
	
	
	return flag;
}
}
		
	

