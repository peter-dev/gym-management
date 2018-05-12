import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

// based on the example
// https://stackoverflow.com/questions/17862717/sorting-a-hashmaps-values-with-sorted-set
// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

public class DateComparator implements Comparator<String> {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");

  @Override
  public int compare(String o1, String o2) {

    LocalDate o1Date = null;
    LocalDate o2Date = null;

    try {
      o1Date = LocalDate.parse(o1, formatter);
      o2Date = LocalDate.parse(o2, formatter);

    } catch (Exception e) {
      System.out.println("Error while parsing date: " + e);
    }

    return o1Date.compareTo(o2Date);
  }
}
