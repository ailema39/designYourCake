package Util;


import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import domain.Client;


public class SendEmail  extends Thread  {

	public static void sendEmail(Properties propertiesFile, String email, String name, String passwordUser, boolean attach, String pref) {
		
		  String to = email;
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.transport.protocol", "smtps");
		  properties.put("mail.smtp.auth", "true");
		  properties.put("mail.debug", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
	
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));
	
		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
	
		         // Set Subject: header field
		         message.setSubject("Registro en la Página Web de ArmaTuTorta ");
	
		         // Send the actual HTML message, as big as you like
		         String messa = "Hola," + name + "<br><br>" +
		    		" A continuaci&oacute;n encontrar&aacute;s la informaci&oacute;n de tu cuenta: <br><br> " +
		        	" <strong> Nombre de Usuario: </strong>" + email + "<br><br>"+	 
		        	" <strong> Contrase&ntilde;a </strong>" + passwordUser + "<br><br>"+	 
		    		" Atentamente, equipo de apoyo de <a href=\"www.armatutorta.com.ve\"> Arma Tu Torta </a>";
	
		         message.setContent(messa,"text/html" );
		         
		         
		      Transport.send(message);
		 
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	}
		
	
	public static void sendEmailPassword(Properties propertiesFile, String email, String name, String newPassword, boolean attach, String pref) {
		
		  String to = email;
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 	
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.put("mail.transport.protocol", "smtp");
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Recuperación de Contraseña");

		         // Send the actual HTML message, as big as you like
		         String messa = "<div style='color: gray;'>Hola, " + name + ".<br>" +
		        		 "A continuación encontrará la información para acceder a su cuenta: <br><br>"
		        		 + "Su usuario es: " + email + "<br>"+
		        		 "Su nueva contrase&ntilde;a es:" + newPassword +"<br><br>"+
		        		 "Atentamente, equipo de apoyo de Arma Tu Torta.</div>";

		         message.setContent(messa,"text/html" );

		         // Send message
		         Transport.send(message);
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	 }
	
	
	public static void sendContactEmail(Properties propertiesFile, String email, String name, String phone, String msg,  boolean attach, String pref) {
		
		  String to = propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.put("mail.transport.protocol", "smtps");
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Tienes un nuevo comentario o mensaje");

		         // Send the actual HTML message, as big as you like
		         String messa = "<div style='color: #8904B1;'>Hola, has recibido un nuevo comentario a través de " +
		         		"la p&aacute;gina web de Arma Tu Torta.<br><br>" +
		        		 "A continuación encontrarás los datos de la persona que escribi&oacute; y su mensaje: <br><br>"
		        		 + "<strong>Nombre: </strong>" + name + "<br>"
		        		 + "<strong>Email:</strong> " + email + "<br>"
		        		 + "<strong>Tel&efono:</strong> " + phone + "<br>"
		        		 + "<strong>Mensaje:</strong> " + msg + "<br>";
		         message.setContent(messa,"text/html" );

		         // Send message
		         Transport.send(message);
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	 }
	
	
	public static void sendContactEmailCopy(Properties propertiesFile, String email, String name, String phone, String msg,  boolean attach, String pref) {
		
		  String to = email;
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Copia de mensaje envíado a Arma Tu Torta");

		         // Send the actual HTML message, as big as you like
		         String messa = "<div style='color: #8904B1;'>Hola, " + name + ", <br><br>"
		        		 + "A continuación encontrarás una copia del mensaje que nos enviaste " +
		        		 " Arma Tu Torta <br><br>"
		        		 + "<strong> Nombre:</strong>  " + name + "<br>"
		        		 + "<strong> Email:</strong> " + email + "<br>"
		        		 + "<strong> Tel&efono:</strong> " + phone + "<br>"
		        		 + "<strong> Mensaje:</strong> " + msg + "<br><br>" +
        		 		"Gracias por escribirnos, te responderemos en la brevedad posible.<br><br>" +
        		 		"Atentamente, Equipo de Apoyo de Arma Tu Torta.";
		         
		         
		         message.setContent(messa,"text/html" );

		         // Send message
		         Transport.send(message);
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	 }
	
	
	public static void sendEmailOrderCake(Properties propertiesFile, String numPedido, boolean attach, String pref,
			String[] datos, String[] relleno, Client client) {
		
		  String to = propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
	
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));
	
		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
	
		         // Set Subject: header field
		         message.setSubject("Tienes un nuevo pedido de tortas. Orden N°" + numPedido);
	
		         String rell = "";
		         for (int i = 0; i< relleno.length; i++){
		        	 if (i == relleno.length - 1)
		        		 rell += relleno[i] ;
		        	 else
		        		 rell += relleno[i] + "," ;
		         }
		         
		        
		         // Send the actual HTML message, as big as you like
		         String messa = "Has recibido un nuevo pedido. <br><br>" +
		    		"<strong> Datos del pedido.</strong><br><br>" +
		    		" Producto pedido: Torta<br>" +
		    		" Forma: " + datos[0] + "<br>" +
		     		" Tama&ntilde;o: " + datos[1]  + "<br>" +
		     		" Sabor del ponqu&eacute; " + datos[2]  + "<br>" +
		     		" Cantidad de capas: " + datos[3]  + "<br>" +
		     		" Sabores de capas: " + rell + "<br>" +
		     		" Sabores de cubierta: " + datos[4] + ".<br><br>" +
		     		" Precio: Bs." + datos[5] + ".<br><br>" +
		     		" Fecha de Entrega: " + datos[7] + ".<br><br>" +
		     		
		     		" <strong>Datos del comprador:</strong> <br><br> " +
		     		" Nombre: " + client.getFirstName() + " "  + client.getLastName() +  "<br>" +
		     		" Correo Electr&oacute;nico: " + client.getEmail() + "<br>" +
		     		" Tel&eacute;fono: " + client.getPhone() + "<br>";
		         	if (client.getOtherPhone() != null && !client.getOtherPhone().equals(""))
		         		messa += "Otro Tel&eacute;fono: " + client.getOtherPhone() + "<br>";
		         	messa += "Dirección de Envío: " + client.getShippingAddress() + ".<br><br>";
		         	if (attach)
		         		messa += "Adjunto encontrar&aacute;s la imagen que el usuario quiere usar para la cubierta de la torta.";
		         
		         MimeBodyPart mbp1 = new MimeBodyPart();
		         mbp1.setText(messa);
		         mbp1.setContent(messa, "text/html");
		         Multipart mp = new MimeMultipart();
		         mp.addBodyPart(mbp1);

		         if (attach){
		             MimeBodyPart mbp = new MimeBodyPart();
		             FileDataSource fds = new FileDataSource(datos[6]);
		             mbp.setDataHandler(new DataHandler(fds));
		             mbp.setFileName(fds.getName());
		             mp.addBodyPart(mbp);
		         }
		        
		         message.setContent(mp);
		         message.setSentDate(new Date());
		         Transport.send(message);
//		         if (attach){
//			         File file = new File(datos[6]);
//			         if(file.delete()){
//		    			System.out.println(file.getName() + " is deleted!");
//			         }else{
//		    			System.out.println("Delete operation is failed.");
//			         }
//		         }
		 
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	}
		
	
	public static void sendEmailOrderOcEsp(Properties propertiesFile, String numPedido, boolean attach, String pref,
			String[] datos, String productos, Client client) {
		
		  String to = propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
	
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));
	
		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
	
		         // Set Subject: header field
		         message.setSubject("Tienes una nueva solicitud de presupuesto: No." + numPedido);
	
    	         // Send the actual HTML message, as big as you like
		         String messa = "Has recibido una nueva solicitud de presupuesto. <br><br>" +
		    		"<strong> Datos de la solicitud.</strong><br><br>" +
		    		" Productos pedidos: " + productos + "<br>" +
		    		" Ocasi&oacute;n: " + datos[0] + "<br>" +
		     		" N&uacute;mero aproximado de invitados: " + datos[1]  + "<br>" +
		     		" Informaci&oacute;n sobre la idea del usuario: " + datos[2]  + "<br>" +
		     		" Fecha de Entrega: " + datos[3] + ".<br><br>" +
		     		
		     		" <strong>Datos del comprador:</strong> <br><br> " +
		     		" Nombre :" + client.getFirstName() + " " + client.getLastName() +  "<br>" +
		     		" Correo Electr&oacute;nico :" + client.getEmail() + "<br>" +
		     		" Tel&eacute;fono :" + client.getPhone() + ".<br><br>"; 
	               	if (client.getOtherPhone() != null)
	               		messa += "Otro Tel&eacute;fono: " + client.getOtherPhone() + "<br>";
		         	messa += "Dirección de Envío: " + client.getShippingAddress() + ".<br><br>";
		         	if (attach)
		     			messa += "Anexo encontrar&aacute;s la imagen de referencia que mand&oacute; el usuario.";
		         
		         MimeBodyPart mbp1 = new MimeBodyPart();
		         mbp1.setText(messa);
		         mbp1.setContent(messa, "text/html");
		         Multipart mp = new MimeMultipart();
		         mp.addBodyPart(mbp1);

		         if (attach){
		             MimeBodyPart mbp = new MimeBodyPart();
		             FileDataSource fds = new FileDataSource(datos[4]);
		             mbp.setDataHandler(new DataHandler(fds));
		             mbp.setFileName(fds.getName());
		             mp.addBodyPart(mbp);
		         }
		        
		         message.setContent(mp);
		         message.setSentDate(new Date());
		         Transport.send(message);
		        
		         
		         /*if (attach){
			         File file = new File(datos[4]);
			         if(file.delete()){
		    			System.out.println(file.getName() + " is deleted!");
			         }else{
		    			System.out.println("Delete operation is failed.");
			         }
		         }*/
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	}
	
	
	public static void sendEmailOrderDulcesTortas(Properties propertiesFile, String numPedido, boolean attach, String pref,
			String[] datos, String[] productos, Client client) {
		
		  String to = propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String user= propertiesFile.getProperty(pref + "EmailCountFrom");
		  final String password =  propertiesFile.getProperty(pref + "EmailPasswordFrom");
		  final String port = propertiesFile.getProperty(pref + "EmailPort");
			 
		  Properties properties = System.getProperties();
		  properties.setProperty("mail.smtp.host", propertiesFile.getProperty(pref + "EmailServer") );
		  properties.setProperty("mail.smtp.port", port);
		  properties.setProperty("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.auth", "true");
		  
		  Session session = Session.getDefaultInstance(properties,
		   new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			   return new PasswordAuthentication(user,password);
			  }
		  });
		   
		  try{
			  
			  // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
	
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(user));
	
		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
	
		         // Set Subject: header field
		         message.setSubject("Tienes una nueva compra de dulces tortas: No." + numPedido);
	
		         // Send the actual HTML message, as big as you like
		         String messa = "Has recibido una nueva compra de dulces tortas. <br><br>" +
		    		"<strong> Datos de la orden:</strong><br><br>" +
		        	" Productos pedidos: <br>";
		         
		         	for (int j = 0; j < productos.length; j++){
		         		messa += "* " + productos[j] + "<br>";
		         	}
		        	
		         	messa += "<br> Fecha de Entrega: " + datos[0] + ".<br>" +
		         			" Total: Bs." + datos[1] + ".<br><br>" +
		     		" <strong>Datos del comprador:</strong> <br><br> " +
		     		" Nombre :" + client.getFirstName() + " " + client.getLastName() +  "<br>" +
		     		" Correo Electr&oacute;nico: " + client.getEmail() + "<br>" +
		     		" Tel&eacute;fono: " + client.getPhone() + ".<br>"; 
	               	if (client.getOtherPhone() != null && !client.getOtherPhone().equals(""))
	               		messa += "Otro Tel&eacute;fono: " + client.getOtherPhone() + "<br>";
		         	messa += "Dirección de Envío: " + client.getShippingAddress() + ".<br><br>";
		        
		         	MimeBodyPart mbp1 = new MimeBodyPart();
		         	
		         mbp1.setText(messa);
		         mbp1.setContent(messa, "text/html");
		         Multipart mp = new MimeMultipart();
		         mp.addBodyPart(mbp1);

		         message.setContent(mp);
		         message.setSentDate(new Date());
		         Transport.send(message);
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
		   }
	}

}
