package patientintake;

public enum Doctor {
   avery("Ralph Avery"),
   johnson("Beth Johnson"),
   murphy("Pat Murphy");

   private String name;

   Doctor(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
