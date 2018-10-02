package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ClinicCalendar should")
class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeEach
   void init() {
      calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
   }

   @Test
   @DisplayName("record a new appointment correctly")
   void allowEntryOfAnAppointment() {
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);

      assertAll(
         () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
         () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
         () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
         () -> assertEquals("9/1/2018 02:00 PM",
            enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
      );
   }

   @Nested
   @DisplayName("indicate if there are appointments correctly")
   class HasAppointments {

      @Test
      @DisplayName("when there are appointments")
      void returnTrueForHasAppointmentsIfThereAreAppointments() {
         calendar.addAppointment("Jim", "Weaver", "avery",
            "09/01/2018 2:00 pm");
         assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
      }

      @Test
      @DisplayName("when there are no appointments")
      void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
         assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
      }
   }

   @Nested
   @DisplayName("return appointments correctly")
   class AppointmentsForDay {

      @Test
      @DisplayName("for today")
      void returnCurrentDaysAppointments() {
         calendar.addAppointment("Jim", "Weaver", "avery",
            "08/26/2018 2:00 pm");
         calendar.addAppointment("Jim", "Weaver", "avery",
            "08/26/2018 3:00 pm");
         calendar.addAppointment("Jim", "Weaver", "avery",
            "09/01/2018 2:00 pm");
         assertEquals(2, calendar.getTodayAppointments().size());
      }

      @Test
      @DisplayName("for tomorrow")
      void returnTommorowsAppointments() {
         calendar.addAppointment("Jim", "Weaver", "avery",
            "08/27/2018 2:00 pm");
         calendar.addAppointment("Jim", "Weaver", "avery",
            "08/27/2018 2:00 pm");
         calendar.addAppointment("Jim", "Weaver", "avery",
            "08/26/2018 3:00 pm");
         assertEquals(2, calendar.getTomorrowAppointments().size());
      }
   }

}