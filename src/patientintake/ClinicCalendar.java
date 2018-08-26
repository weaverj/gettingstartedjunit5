package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClinicCalendar {

   private List<PatientAppointment> appointments;
   private LocalDate today;

   public ClinicCalendar(LocalDate today) {
      this.today = today;
      this.appointments = new ArrayList<>();
   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                              String dateTime) {
      Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
      LocalDateTime localDateTime;
      try {
         System.out.println("Creating for today.");
         if (dateTime.toLowerCase().startsWith("today")) {
            String[] parts = dateTime.split(" ", 2);
            System.out.println("time pattern is : [" + parts[1] + "]");
            LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
            localDateTime = LocalDateTime.of(today, time);
         }
         else {
            localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
               DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
         }
      } catch (Throwable t) {
         throw new RuntimeException("Unable to create date time from: [" +
            dateTime + "], please enter with format [M/d/yyyy h:mm a], " + t.getMessage());
      }
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
         localDateTime, doc);
      appointments.add(appointment);
   }

   public List<PatientAppointment> getAppointments() {
      return this.appointments;
   }

}
