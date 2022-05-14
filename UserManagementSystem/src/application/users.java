package application;

import java.sql.*;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class users {
	
	//Database Connection String
	Connection con = null;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	
	public void Connect() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dbemail?characterEncoding=utf8";
			String username = "root";
			String password ="root";
			con = DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ObservableList<userModel> getUserList(){
		ObservableList<userModel> userList = FXCollections.observableArrayList();
		String sql = "SELECT ID,REGNO,NAME,ATTENDANCE,EMAIL from users";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			userModel user;
			while (rs.next()) {
				user = new userModel(rs.getInt("ID"), rs.getInt("REGNO"), rs.getString("NAME"), rs.getInt("ATTENDANCE"), rs.getString("EMAIL"));
				userList.add(user);
			}
		}
		catch(Exception e) {
				e.printStackTrace();
		}
		return userList;
		
	}
	
	
	//show user details
	public void loadData() {
		ObservableList<userModel> list = getUserList();
		colID.setCellValueFactory(new PropertyValueFactory<userModel, Integer>("id"));
		colRegNo.setCellValueFactory(new PropertyValueFactory<userModel, Integer>("regno"));
		colName.setCellValueFactory(new PropertyValueFactory<userModel, String>("name"));
		colAttendance.setCellValueFactory(new PropertyValueFactory<userModel, Integer>("attendance"));
		colEmail.setCellValueFactory(new PropertyValueFactory<userModel, String>("email"));
		table.setItems(list);
		
	}

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<userModel, Integer> colAttendance;

    @FXML
    private TableColumn<userModel, String> colEmail;

    @FXML
    private TableColumn<userModel, Integer> colID;

    @FXML
    private TableColumn<userModel, String> colName;

    @FXML
    private TableColumn<userModel, Integer> colRegNo;

    @FXML
    private Label lblAttendance;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRegNo;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<userModel> table;

    @FXML
    private TextField txtAttendance;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRegNo;

    @FXML
    void btnClearClicked(ActionEvent event) {
    	txtID.setText("");
    	txtRegNo.setText("");
    	txtName.setText("");
    	txtAttendance.setText("");
    	txtEmail.setText("");
    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

    	//delete details
    	String id = txtID.getText();
    	
    	int result = JOptionPane.showConfirmDialog(null, "Sure? Are You Want To Delete This?",
    			"Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	
    	if(result==JOptionPane.YES_NO_OPTION) {
    		
    		try {
    			String sql = "DELETE FROM users WHERE ID=?";
    			pst = con.prepareStatement(sql);
    			pst.setString(1, id);
    			pst.executeUpdate();
    			JOptionPane.showMessageDialog(null, "DATA DELETED SUCCESSFULLY");
    			btnClearClicked(event);
    			loadData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}

    	
    }

    @FXML
    void btnSaveClicked(ActionEvent event) {
    	
    	//save details
    	String regno = txtRegNo.getText();
    	String name = txtName.getText();
    	String attendance = txtAttendance.getText();
    	String email = txtEmail.getText();
    	
    	if(regno==null || regno.isEmpty() || regno.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the RegNo");
    		txtRegNo.requestFocus();
    		return;
    	}
    	
    	if(name==null || name.isEmpty() || name.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Name");
    		txtName.requestFocus();
    		return;
    	}
    	
    	if(attendance==null || attendance.isEmpty() || attendance.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Attendance Percentage");
    		txtAttendance.requestFocus();
    		return;
    	}
    	
    	if(email==null || email.isEmpty() || email.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Email ID");
    		txtEmail.requestFocus();
    		return;
    	}
    	
    	if(txtID.getText().isEmpty()) {
    		
    		try {
    			String sql = "INSERT into users(REGNO, NAME, ATTENDANCE, EMAIL) VALUES (?,?,?,?)";
    			pst = con.prepareStatement(sql);
    			pst.setString(1, regno);
    			pst.setString(2, name);
    			pst.setString(3, attendance);
    			pst.setString(4, email);
    			pst.executeUpdate();
    			JOptionPane.showMessageDialog(null, "DATA INSERTED SUCCESSFULLY");
    			btnClearClicked(event);
    			loadData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}

    }

    @FXML
    void btnUpdateClicked(ActionEvent event) {
    	
    	//update details
    	String id = txtID.getText();
    	String regno = txtRegNo.getText();
    	String name = txtName.getText();
    	String attendance = txtAttendance.getText();
    	String email = txtEmail.getText();
    	
    	if(regno==null || regno.isEmpty() || regno.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the RegNo");
    		txtRegNo.requestFocus();
    		return;
    	}
    	
    	if(name==null || name.isEmpty() || name.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Name");
    		txtName.requestFocus();
    		return;
    	}
    	
    	if(attendance==null || attendance.isEmpty() || attendance.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Attendance Percentage");
    		txtAttendance.requestFocus();
    		return;
    	}
    	
    	if(email==null || email.isEmpty() || email.trim().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please Enter the Email ID");
    		txtEmail.requestFocus();
    		return;
    	}
    	
    	if(!txtID.getText().isEmpty()) {
    		
    		try {
    			String sql = "UPDATE users set REGNO=?,NAME=?,ATTENDANCE=?,EMAIL=? where ID=?";
    			pst = con.prepareStatement(sql);
    			pst.setString(1, regno);
    			pst.setString(2, name);
    			pst.setString(3, attendance);
    			pst.setString(4, email);
    			pst.setString(5, id);
    			pst.executeUpdate();
    			JOptionPane.showMessageDialog(null, "DATA UPDATED SUCCESSFULLY");
    			btnClearClicked(event);
    			loadData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}

    }

    @FXML
    void tableClicked(MouseEvent event) {
    	
    	userModel user = table.getSelectionModel().getSelectedItem();
    	txtID.setText(String.valueOf(user.getId()));
    	txtRegNo.setText(String.valueOf(user.getRegno()));
    	txtName.setText(user.getName());
    	txtAttendance.setText(String.valueOf(user.getAttendance()));
    	txtEmail.setText(user.getEmail());
    	

    }

    @FXML
    void initialize() {
    	Connect();
    	loadData();

    }

}
