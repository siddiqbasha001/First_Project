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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class email implements Initializable {

	
	@FXML
	private Button btn_logout;
	
	@FXML
    private Label lbl_welcome;



	@Override
	public void initialize(URL location, ResourceBundle resource) {
		
		btn_logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "login.fxml", "Email Management System - LogIn", null);
			
			}
			
		});
	}
	
	public void setUserInfomation(String username) {
		
		lbl_welcome.setText("Welcome " + username + "!");
		
		
	}

	
	
}

