


package tn.esprit.Medfly.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class TwilloService {
    private static final String ACCOUNT_SID = "ACcfe7b37fc27fa99c4eb5e0d7e351df2d";
    private static final String AUTH_TOKEN = "ea0d7d241f40649948146c9b213d94eb";
    private static final String FROM_NUMBER = "+12314651476";

    public static void sendSms(String toNumber, String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(FROM_NUMBER),
                        messageText)
                .create();

        System.out.println("Message SID: " + message.getSid());
    }
}