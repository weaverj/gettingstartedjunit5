package patientintake.notifier;

import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

   private ClinicCalendar calendar;

   public UpcomingAppointmentNotifier(ClinicCalendar calendar) {
      this.calendar = calendar;
   }

   public void run() {
      calendar.getTomorrowAppointments().forEach(appt -> {
         SmtpMessageSender notifier = new SmtpMessageSender();
         String email = appt.getPatientLastName().toLowerCase() + appt.getPatientFirstName().toLowerCase() + "@mail.com";
         System.out.println("Sending with body: " + buildMessageBody(appt));
         notifier.sendNotification("Appointment Reminder", buildMessageBody(appt), email);
      });
   }

   private String buildMessageBody(PatientAppointment appt) {
      return "You have an appointment tomorrow at "
         + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
         + " with Dr. "
         + appt.getDoctor().getName() + ".";
   }

}
