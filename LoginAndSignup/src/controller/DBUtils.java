package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.mysql.jdbc.*;
//import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DBUtils {
	
	public static void changeScene(ActionEvent event, String fxmlfile, String title, String username) {
		
		Parent root = null;
		
		if(username!=null ) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlfile));
				root = loader.load();
				email emailinfoload = loader.getController();
				emailinfoload.setUserInfomation(username);	
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				root = FXMLLoader.load(DBUtils.class.getResource(fxmlfile));
			
			}catch(IOException e) {
				e.printStackTrace();
				
			}
		}
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root,600,400));
		stage.show();
	}
	
	public static void signUpUser(ActionEvent event, String firstname, String lastname, String email, String username, String password) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {

			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin?characterEncoding=utf8", "root", "root");
			psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
			psCheckUserExists.setString(1, username);
			resultSet = psCheckUserExists.executeQuery();
			
			if (resultSet.isBeforeFirst()) {
				System.out.print("User already exists");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("You can't use this username");
				alert.show();
			}else {
				psInsert = connection.prepareStatement("INSERT INTO users (firstname, lastname, email, username, password) VALUES (?,?,?,?,?)");
				psInsert.setString(1, firstname);
				psInsert.setString(2, lastname);
				psInsert.setString(3, email);
				psInsert.setString(4, username);
				psInsert.setString(5, password);
				psInsert.executeUpdate();
				
				changeScene(event, "email.fxml", "Email Management System - Email Sending Page", username);
				
			}
		
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			if(psInsert !=null) {
				try {
					psInsert.close();	
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public static void loginUser(ActionEvent event, String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin?characterEncoding=utf8", "root", "root");
			preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {	
				System.out.print("User not found in database");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Are you sure is that correct user credential");
				alert.setContentText("Provided user credential are in correct");
				alert.show();
			}
			else {
				while(resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					
					if(retrievedPassword.equals(password)) {
						changeScene(event, "email.fxml", "Email Management System - Email Sending", username);
					}else {
						System.out.println("Password Not Matched");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Login Error");
						alert.setContentText("Provided user credential are incorrect");				
						alert.show();

					}
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	
	}
	
}
