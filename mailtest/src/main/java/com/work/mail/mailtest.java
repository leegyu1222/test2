package com.work.mail;

import java.util.Properties;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mailtest {
	@Autowired
    public static void send() throws MessagingException{
        String smtp_addr ="127.0.0.1";
        String sender_addr ="webmaster@localhost";
        String sender_nm  = "관리자";
        String receiver = "leegyu007@hanmail.net" ;
        String subject = "승인좀 부탁";
        String content = "승인해주삼 " ;
        try
        {
            // 기본적인 메소드를 선언한다.
            Properties props = new Properties();
            
            props.put("mail.smtp.host",smtp_addr);
            Session session = Session.getDefaultInstance(props,null);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender_addr,sender_nm, "euc-kr"));

            InternetAddress[] tos = InternetAddress.parse(receiver);
            message.setRecipients(Message.RecipientType.TO, tos);
            message.setSubject(subject, "euc-kr");

            Multipart multipart = new MimeMultipart();
            //메일 내용을 Setting한다.
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText(content, "euc-kr");
            //messageBodyPart.setContent(content, "text/html;charset=euc-kr");

            multipart.addBodyPart(messageBodyPart);
            message.setSentDate(new java.util.Date());
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");

            Transport.send(message);
            transport.close();

        }
        catch(Exception e)  {
               e.printStackTrace();
        }


	}

}
