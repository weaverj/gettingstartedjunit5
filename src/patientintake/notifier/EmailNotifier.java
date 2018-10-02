package patientintake.notifier;

public interface EmailNotifier {
   void sendNotification(String subject, String body, String address);
}
