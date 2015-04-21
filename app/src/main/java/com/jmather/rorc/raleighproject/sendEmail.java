package com.jmather.rorc.raleighproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;


public class sendEmail extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_email, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendemail(View view) {
        TextView bossemailText = (TextView) findViewById(R.id.bossemail);
        String bossemail = bossemailText.getText().toString();
        TextView senderemailText = (TextView) findViewById(R.id.senderemail);
        String senderemail = senderemailText.getText().toString();
        TextView sendernameText = (TextView) findViewById(R.id.name);
        String sendername = sendernameText.getText().toString();
        double miles = 920.0;
        Email email = new Email();
        //     SendEmailASyncTask task = new SendEmailASyncTask(getApplicationContext());

    }
    /**
     * ASyncTask that composes and sends email
     */
 /*   private class SendEmailASyncTask extends AsyncTask<Void, Void, Void> {

        private Context context;
        private String msgResponse;

        private SendEmailASyncTask(Context context) {
            this.context = context;
        }

        @Override
        /*protected Void doInBackground(Void... params) {
/*
            try {
                SendGrid sendgrid = new SendGrid(SENDGRID_USERNAME, SENDGRID_PASSWORD);

                SendGrid.Email email = new SendGrid.Email();

                // Get values from edit text to compose email
                // TODO: Validate edit texts
                email.addTo(toEditText.getText().toString());
                email.setFrom(fromEditText.getText().toString());
                email.setSubject(subjectEditText.getText().toString());
                email.setText(msgEditText.getText().toString());

                // Attach image
                if (selectedImageURI != null) {
                    email.addAttachment(attachmentName, getContentResolver().openInputStream(selectedImageURI));
                }

                // Send email, execute http request
                SendGrid.Response response = sendgrid.send(email);
                msgResponse = response.getMessage();

                Log.d("SendAppExample", msgResponse);

            } catch (SendGridException e) {
                Log.e("SendAppExample", e.toString());
            } catch (JSONException e) {
                Log.e("SendAppExample", e.toString());
            } catch (IOException e) {
                Log.e("SendAppExample", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(context, msgResponse, Toast.LENGTH_SHORT).show();
        }
    }
}*/
}