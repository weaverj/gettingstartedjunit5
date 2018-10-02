package patientintake.notifier;

import java.util.ArrayList;

public class EmailNotifierTestDouble implements EmailNotifier {

   ArrayList<Message> receivedMessages = new ArrayList<>();

   @Override
   public void sendNotification(String subject, String body, String address) {
      receivedMessages.add(new Message(subject, body, address));
   }

   class Message {
      public String toAddress;
      public String subject;
      public String body;

      public Message (String subject, String body, String address) {
         this.subject = subject;
         this.body = body;
         this.toAddress = address;
      }
   }

}
