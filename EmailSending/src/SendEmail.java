import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public static void sendMail(String recepient){
		System.out.println("Preparing to send Email");
		
		//store the prperties needed t access smpt server
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");


		//sender user name(email) and password
		String username="your-emai-id";
		String password="your-password";
	

		//create session object and pass the credentials
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});


		//create message object receiving from
		Message message = prepareMessage(session,username,recepient);
		try {
			Transport.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("email sent successfully to " + recepient);

	}


	private static Message prepareMessage(Session session,String username, String recepient){

		Message message = new MimeMessage(session);
		try{
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
			message.setSubject("[--test email --]");
			message.setText("hello,\n [------mail body------]");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return message;

	}

	
}
