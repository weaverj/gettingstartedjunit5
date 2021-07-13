package patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClinicCalendarShould {

  @Test
  void allowEntryOfAnAppointment() {
    ClinicCalendar calendar = new ClinicCalendar();
    calendar.addAppointment("Timothy", "Stone", "Avery", "07/12/2021 07:00 am");
    List<PatientAppointment> appointments = calendar.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());
    PatientAppointment appointment = appointments.get(0);
    assertEquals("Timothy", appointment.getPatientFirstName());
    assertEquals("Stone", appointment.getPatientLastName());
    assertEquals(Doctor.avery, appointment.getDoctor());
    assertEquals("7/12/2021 07:00 AM",
        appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
  }
}
