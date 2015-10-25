import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justp_000 on 10/24/2015.
 */
public class People {

    public Thread thread;

    String phoneNum;
    int time;
    String questions;

    public People(String phoneNum, int time, String questions){
        this.phoneNum = phoneNum;
        this.time = time;
        this.questions = questions;

        thread = new Thread(() -> {
            while(true){
            try {
                Thread.sleep((long) time*60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final TwilioRestClient client = new TwilioRestClient("AC24dcb8ff451fefca20dcf24db7169cc6", "1232ab47c4eb8674dffe239456c4c582");

            final Account mainAccount = client.getAccount();

            final SmsFactory messageFactory = mainAccount.getSmsFactory();
            final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
            messageParams.add(new BasicNameValuePair("To", phoneNum)); // Replace with a valid phone number
            messageParams.add(new BasicNameValuePair("From", "(747) 477-8979")); // Replace with a valid phone number in your account
            messageParams.add(new BasicNameValuePair("Body", questions));
            try {
                messageFactory.create(messageParams);
            } catch (TwilioRestException e) {
                e.printStackTrace();
            }}
        });
        thread.start();
    }
}
