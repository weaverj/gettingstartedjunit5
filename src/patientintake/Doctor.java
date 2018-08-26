package patientintake;

public enum Doctor {
   avery("Ralph Avery", "1-2345"),
   johnson("Beth Johnson", "1-3456"),
   murphy("Pat Murpy", "1-4567");

   private String name;
   private String pager;

   Doctor(String name, String pager) {
      this.name = name;
      this.pager = pager;
   }

   public String getName() {
      return name;
   }

   public String getPager() {
      return pager;
   }
}
