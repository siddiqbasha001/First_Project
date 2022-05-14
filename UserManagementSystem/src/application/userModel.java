package application;

public class userModel {
	private Integer id;
	private Integer regno;
	private String name;
	private Integer attendance;
	private String email;
	
	public userModel(Integer id,Integer regno,String name,Integer attendance,String email) {
		this.setId(id);
		this.setRegno(regno);
		this.setName(name);
		this.setAttendance(attendance);
		this.setEmail(email);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegno() {
		return regno;
	}

	public void setRegno(Integer regno) {
		this.regno = regno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAttendance() {
		return attendance;
	}

	public void setAttendance(Integer attendance) {
		this.attendance = attendance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
