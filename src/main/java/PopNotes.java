/**
 * Created by justp_000 on 10/24/2015.
 */

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.verbs.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class PopNotes {
    public static void main(String[] args) {

        ArrayList<People> peoples = new ArrayList<People>();

        TwilioRestClient client = new TwilioRestClient("AC24dcb8ff451fefca20dcf24db7169cc6" , "1232ab47c4eb8674dffe239456c4c582"); // replace this
        Account mainAccount = client.getAccount();

        staticFileLocation("/public"); // Static files

        get("/name", (req, res) -> {
            String name = req.queryParams("foo");
            return "Hello, " + name;
        });

        get("/form", (req, res) -> {
           String delay = req.queryParams("delay");
           int delayInt = Integer.parseInt(delay);

           String phoneNum = req.queryParams("phoneNum");
           String questions = req.queryParams("questions");

           peoples.add(new People(phoneNum, delayInt, questions));

           return "Message Sent :)";
        });

        post("/sms", (req, res) -> {
            TwiMLResponse twiml = new TwiMLResponse();

            twiml.append(new Message("Notes Stopped"));

            return twiml.toXML();
        });
    }
}
