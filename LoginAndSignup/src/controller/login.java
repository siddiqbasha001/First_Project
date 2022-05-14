package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class login implements Initializable{
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
    void initialize() {
       
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		btn_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.loginUser(event, tf_username.getText(), tf_password.getText());
				
			}
		
		});
		
		btn_signup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "signup.fxml", "Email Management System - SignUp", null);
			}
			
		});
		
	}
	
	
}

