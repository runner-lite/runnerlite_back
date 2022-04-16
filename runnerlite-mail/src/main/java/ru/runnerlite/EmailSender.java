package ru.runnerlite;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import ru.runnerlite.model.Letter;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    // путь к файлу конфигурации
    private static final String PROPS_FILE ="runnerlite-mail/src/main/resources/mail.properties";

    private static Logger logger = (Logger) LoggerFactory.getLogger(EmailSender.class);
    private Properties property;
    private EmailAuthenticator emailAuthenticator;
    private Session session;

    public EmailSender() {
        this.property=readPropertyFromConfig();
        this.emailAuthenticator = new EmailAuthenticator(property.getProperty("user"), property.getProperty("password"));
        this.session = Session.getDefaultInstance(property,emailAuthenticator);
    }

    public void sendEmail(Letter letter){
        try{
            logger.info("Try send message to " + letter.getTo());
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(property.getProperty("from")));
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(letter.getTo()));
            mimeMessage.setSubject(letter.getTopic());
            mimeMessage.setText(letter.getMessage());
            Transport.send(mimeMessage);
            logger.info("Message to " + letter.getTo() + " send success");
        }
        catch (MessagingException me){
            logger.error("Message not send Exception:" + me.getMessage());
        }
    }

    //Считываем конфигурации из файла
    private Properties readPropertyFromConfig(){
        Properties property = new Properties();
        try {
            FileInputStream fis ;
            fis = new FileInputStream(PROPS_FILE);
            property.load(fis);
            fis.close();
        } catch (IOException e) {
            logger.error("Properties file not found. Exception : "+ e.getMessage());
        }
        return property;
    }
}
