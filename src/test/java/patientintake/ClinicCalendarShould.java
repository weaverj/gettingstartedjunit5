package patientintake;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClinicCalendarShould {

  private ClinicCalendar calendar;

  @BeforeEach
  void setUp() {
    calendar = new ClinicCalendar(LocalDate.of(2021, 7, 12));
  }

  @Test
  void allowEntryOfAnAppointment() {
    calendar.addAppointment("Timothy", "Stone", "Avery", "07/12/2021 07:00 am");
    List<PatientAppointment> appointments = calendar.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());
    PatientAppointment appointment = appointments.get(0);

    assertAll(
        () -> assertEquals("Timothy", appointment.getPatientFirstName()),
        () -> assertEquals("Stone", appointment.getPatientLastName()),
        () -> assertEquals(Doctor.avery, appointment.getDoctor()),
        () -> assertEquals("7/12/2021 07:00 AM",
            appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
    );
  }

  @Test
  void returnTrueForHasAppointmentsIfThereAreAppointments() {
    calendar.addAppointment("Timothy", "Stone", "Avery", "07/12/2021 07:00 am");
    assertTrue(calendar.hasAppointment(LocalDate.of(2021, 7, 12)));
  }

  @Test
  void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
    assertFalse(calendar.hasAppointment(LocalDate.of(2021, 7, 12)));

  }

  @Test
  void returnCurrentDayAppointments() {
    calendar.addAppointment("David", "Stone", "Avery", "07/12/2021 04:00 pm");
    calendar.addAppointment("Dawn", "Stone", "Avery", "07/12/2021 06:00 pm");
    calendar.addAppointment("Timothy", "Stone", "Murphy", "07/13/2021 07:00 am");
    assertEquals(2, calendar.getTodayAppointments().size());
  }
}
