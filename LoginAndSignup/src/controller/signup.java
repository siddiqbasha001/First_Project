package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signup implements Initializable{
	
	 @FXML
	    private Button btn_login;

	    @FXML
	    private Button btn_signup;

	    @FXML
	    private TextField tf_email;

	    @FXML
	    private TextField tf_firstname;

	    @FXML
	    private TextField tf_lastname;

	    @FXML
	    private TextField tf_password;

	    @FXML
	    private TextField tf_username;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		btn_signup.setOnAction(new EventHandler<ActionEvent>() {
			

			@Override
			public void handle(ActionEvent event) {
				
				if (!tf_firstname.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
					DBUtils.signUpUser(event, tf_firstname.getText(), tf_lastname.getText(), tf_email.getText(), tf_username.getText(), tf_password.getText());
					
				}else {
					System.out.println("Please fill all the information");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill all the information to signup");
					alert.show();
				}
				
			}
		
		});
		
		
		btn_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				DBUtils.changeScene(event, "login.fxml", "Email Management System - LogIn", null);
				
			}
		
		});
	

	}
	
	
	
	
} 
