package com.jmather.rorc.raleighproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sendgrid.*;

import java.net.URL;

/**
 * Created by Josh on 4/19/2015.
 */
public class Email
{
    public void sendEmail()
    {

        SendGrid sendgrid = new SendGrid("mycommuter", "Goatfucker1");
        SendGrid.Email email = new SendGrid.Email();
        email.addTo("mycommuterhack@gmail.com");
        email.setFrom("nicholas.largen@gmail.com");
        email.setSubject("Mileage reimbursement");
        email.setText("My first email with SendGrid Java!");

        try {
            SendGrid.Response response = sendgrid.send(email);
            System.out.println(response.getMessage());
        }
        catch (SendGridException e) {
            System.err.println(e);
        }
    }
}
