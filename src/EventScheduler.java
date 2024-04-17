import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EventScheduler {
    private MinHeap<Event> heap;

    public EventScheduler() {
        heap = new MinHeap<Event>();
    }

    private Date stringToDate(String date) {
        String[] dateArr = date.split("-");
        Date dateObject = new Date(Integer.parseInt("01"+dateArr[2]), Integer.parseInt(dateArr[0])-1, Integer.parseInt(dateArr[1]));
        return dateObject;
    }

    public void addEvent(String name, String date, String location) {
        heap.add(stringToDate(date), new Event(name, location));
    }

    public Event removeNextEvent() {
        System.out.println("The Event: " + ((Event)heap.peek().getData()).getName() + " scheduled for: " + heap.peekKey().toString() + " at: " + ((Event)heap.peek().getData()).getLocation() + "has been removed from the Schedule.");
        return (Event)heap.removeMin().getData();
    }

    public void dispEvents() {
        MinHeap tempHeap = new MinHeap(heap.returnHeap());
        int i = 1;
        while (!tempHeap.isEmpty()) {
            Event ele = (Event)tempHeap.removeMin().getData();
            System.out.println(i + ". " + ele.getName());
            i++;
        }
    }

    public boolean checkConflict() {
        MinHeap tempHeap = new MinHeap(heap.returnHeap());
        boolean res = false;
        ArrayList<HeapNode> arr = new ArrayList<HeapNode>();
        while (!tempHeap.isEmpty()) {
            HeapNode ele = tempHeap.removeMin();
            for (HeapNode other : arr) {
                if (other.getKey().equals(ele.getKey())) {
                    res = true;
                    System.out.println("These two events conlict on date " + ele.getKey().toString() + ":");
                    System.out.println("    Event 1: " + ((Event)ele.getData()).getName());
                    System.out.println("    Event 2: " + ((Event)other.getData()).getName());
                }
            }
            arr.add(ele);
        }

        return res;
    }

    public boolean updateDate(String newDate, int ind) {
        HeapNode[] tempArr = heap.returnHeap();
        if (ind >= tempArr.length) {
            return false;
        }
        tempArr[ind] = new HeapNode<>(stringToDate(newDate), tempArr[ind].getData());
        heap.updateArray(tempArr);
        return true;
    }

    public boolean updateName(String newName, int ind) {
        HeapNode[] tempArr = heap.returnHeap();
        if (ind >= tempArr.length) {
            return false;
        }
        tempArr[ind] = new HeapNode<>(tempArr[ind].getKey(), new Event(newName, ((Event)tempArr[ind].getData()).getLocation()));
        heap.updateArray(tempArr);
        return true;
    }

    public boolean updateLoc(String newLoc, int ind) {
        HeapNode[] tempArr = heap.returnHeap();
        if (ind >= tempArr.length) {
            return false;
        }
        tempArr[ind] = new HeapNode<>(tempArr[ind].getKey(), new Event(((Event)tempArr[ind].getData()).getName(), newLoc));
        heap.updateArray(tempArr);
        return true;
    }




    public static void main(String[]args) {
        EventScheduler es = new EventScheduler();
        es.addEvent("BRUIN DAY", "4-13-24", "UCLA");
        es.addEvent("Sunday Scrimmage", "4-14-24", "Mark Daily");
        es.addEvent("School", "4-15-24", "Woodbridge High School");
        es.addEvent("Graduation", "6-6-24", "Irvine HS Stadium");
        es.addEvent("Tennis Match", "4-17-24", "Heritage Park");
        es.addEvent("Fine Arts Day", "4-17-24", "Woodbridge");
        Scanner sc = new Scanner(System.in);
        String inp;
        do {
            System.out.print("Choose what to do(1. display, 2. add, 3. edit, 4. exit): ");
            inp = sc.nextLine();
            if (inp.equals("1")) {
                es.dispEvents();
            } else if (inp.equals("2")) {
                System.out.print("Enter the name of the event: ");
                String name = sc.nextLine();
                System.out.print("Enter the location of the event: ");
                String loc = sc.nextLine();
                System.out.print("Enter the date of the event(MM-DD-YY): ");
                String date = sc.nextLine();
                es.addEvent(name, date, loc);
                System.out.println("Event: " + name + "added...");
            } else if (inp.equals("3")) {
                System.out.print("Enter the index of the element: ");
                int index = Integer.parseInt(sc.nextLine());
                System.out.print("Enter what you want to edit(1. Name, 2. Location, 3. Date): ");
                String opt = sc.nextLine();
                System.out.print("What do you want to change it to: ");
                String newString = sc.nextLine();
                if (opt.equals("1")) {
                    es.updateName(newString, index);
                    System.out.println("Updated name.");
                } else if (opt.equals("2")) {
                    es.updateLoc(newString, index);
                    System.out.println("Updated location.");
                } else if (opt.equals("3")) {
                    es.updateDate(newString, index);
                    System.out.println("Updated date.");
                } else {
                    System.out.println("Invalid...");
                }
            } else {
                System.out.println("Please enter a valid option...");
            }
        } while(!inp.equals("4"));
    }
}
