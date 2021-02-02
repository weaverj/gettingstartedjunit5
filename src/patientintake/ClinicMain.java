package patientintake;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

   private static ClinicCalendar calendar;

   public static void main(String[] args) throws Throwable {
      calendar = new ClinicCalendar(LocalDate.now());
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to the Patient Intake Computer System!\n\n");
      String lastOption = "";
      while (!lastOption.equalsIgnoreCase("x")) {
         lastOption = displayMenu(scanner);
      }
      System.out.println("\nExiting System...\n");
   }

   private static String displayMenu(Scanner scanner) throws Throwable {
      System.out.println("Please select an option:");
      System.out.println("1. Enter a Patient Appointment");
      System.out.println("2. View All Appointments");
      System.out.println("3. View Today's Appointments");
      System.out.println("4. Enter Patient Height Weight");
      System.out.println("X.  Exit System.");
      System.out.print("Option: ");
      String option = scanner.next();
      switch (option) {
         case "1": performPatientEntry(scanner);
                   return option;
         case "2": performAllAppointments();
                   return option;
         case "3": performTodayAppointments();
                   return option;
         case "4": performHeightWeight(scanner);
            return option;
         default: System.out.println("Invalid option, please re-enter.");
                  return option;
      }
   }

   private static void performPatientEntry(Scanner scanner) {
      scanner.nextLine();
      System.out.println("\n\nPlease Enter Appointment Info:");
      System.out.print("  Patient Last Name: ");
      String lastName = scanner.nextLine();
      System.out.print("  Patient First Name: ");
      String firstName = scanner.nextLine();
      System.out.print("  Appointment Date (M/d/yyyy h:m a): ");
      String when = scanner.nextLine();
      System.out.print("  Doctor Last Name: ");
      String doc = scanner.nextLine();
      try {
         calendar.addAppointment(firstName, lastName, doc, when);
      } catch (Throwable t) {
         System.out.println("Error!  " + t.getMessage());
         return;
      }
      System.out.println("Patient entered successfully.\n\n");
   }

   private static void performAllAppointments() throws Throwable {
      System.out.println("\n\nAll Appointments in System:");
      listAppointments(calendar.getAppointments());
      System.out.println("\nPress any key to continue...");
      System.in.read();
      System.out.println("\n\n");
   }

   private static void performTodayAppointments() throws Throwable {
      System.out.println("\n\nAppointments for Today:");
      listAppointments(calendar.getTodayAppointments());
      System.out.println("\nPress any key to continue...");
      System.in.read();
      System.out.println("\n\n");
   }

   private static void listAppointments(List<PatientAppointment> appointments) {
      for (PatientAppointment appointment : appointments) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
         String apptTime = formatter.format(appointment.getAppointmentDateTime());
         System.out.println(String.format("%s:  %s, %s\t\tDoctor: %s", apptTime, appointment.getPatientLastName(),
            appointment.getPatientFirstName(), appointment.getDoctor().getName()));
      }
   }

   private static void performHeightWeight(Scanner scanner) {
      scanner.nextLine();
      System.out.println("\n\nEnter patient height and weight for today's appointment:");
      System.out.print("  Patient Last Name: ");
      String lastName = scanner.nextLine();
      System.out.print("  Patient First Name: ");
      String firstName = scanner.nextLine();
      PatientAppointment appt = findPatientAppointment(lastName, firstName).orElse(null);
      if (appt != null) {
         System.out.print("  Height in Inches: ");
         Integer inches = scanner.nextInt();
         System.out.print("  Weight in Pounds: ");
         Integer pounds = scanner.nextInt();
         Double bmi = (double) (pounds * 703) / (inches * inches);
         double roundedToTwoPlaces = new BigDecimal(bmi)
                 .setScale(2, RoundingMode.HALF_UP).doubleValue();
         appt.setBmi(roundedToTwoPlaces);
         System.out.println("Set patient BMI to " + roundedToTwoPlaces + "\n\n");
      }
      else {
         System.out.println("Patient not found.\n\n");
      }
   }

   private static Optional<PatientAppointment> findPatientAppointment(String lastName, String firstName) {
      return calendar.getTodayAppointments().stream()
         .filter(p -> (p.getPatientLastName().equalsIgnoreCase(lastName) && p.getPatientFirstName().equalsIgnoreCase(firstName)))
         .findFirst();
   }

}
