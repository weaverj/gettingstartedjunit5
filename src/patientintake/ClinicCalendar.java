package patientintake;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClinicCalendar {

   private List<PatientAppointment> appointments;

   public ClinicCalendar() {
      this.appointments = new ArrayList<>();
   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime) {
//      if (!Arrays.asList(Doctor.ncontains(doctorKey)) {
//         throw new RuntimeException("Unrecognized identifier for doctor: [" + doctorKey + "]");
//      }
      Doctor doc = Doctor.valueOf(doctorKey);
      LocalDateTime localDateTime;
      try {
         localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("M/d/yyyy h:m a"));
      } catch (Throwable t) {
         throw new RuntimeException("Unable to create date time from: [" + dateTime + "], please enter with format [M/d/yyyy h:m a");
      }
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime, doc);
      appointments.add(appointment);
   }

}
