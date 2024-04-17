import java.util.Date;

public class Main
{
    public static void main(String[] args) {
        EventScheduler es = new EventScheduler();
        es.addEvent("BRUIN DAY", "4-13-24", "UCLA");
        es.addEvent("Sunday Scrimmage", "4-14-24", "Mark Daily");
        es.addEvent("School", "4-15-24", "Woodbridge High School");
        es.addEvent("Graduation", "6-6-24", "Irvine HS Stadium");
        es.addEvent("Tennis Match", "4-17-24", "Heritage Park");
        es.addEvent("Fine Arts Day", "4-17-24", "Woodbridge");
        es.updateName("FArts Day", 5);
        es.dispEvents();

        System.out.println("\n");

        System.out.println(es.checkConflict());
    }
}
