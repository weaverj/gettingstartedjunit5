package patientintake.notifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpcomingAppointmentNotifierTest {

   @Test
   void sendNotificationWithCorrectFormat() {
      ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
      calendar.addAppointment("Jim", "Weaver", "avery",
         "08/27/2018 2:00 pm");
      UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar);

      notifier.run();

  }

}