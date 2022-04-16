package ru.runnerlite;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


//3g9b6psMQJmR1XqFkYiF -mail

public class EmailAuthenticator extends Authenticator {
    private String login   ;
    private String password;
    public EmailAuthenticator (final String login, final String password)
    {
        this.login    = login;
        this.password = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(login, password);
    }
}
