import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class JavaTime {

    // Zoned/Local Date/Time/DateTime
    // Instant

    public static void main(String[] args) {
        {
            //аналогичные методы
            System.out.println(Instant.now().toEpochMilli());
            System.out.println(System.currentTimeMillis());
        }

        {
            //преобразовать время в миллисекунды
            LocalDateTime time = LocalDateTime.of(2020, 5, 20, 19, 37, 2);
            long timeMills = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        {
            //форматирование даты времени
            ZonedDateTime time = ZonedDateTime.now(ZoneId.systemDefault());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH); //2020-08-26T06:53:27.609+0000
            String t = time.format(f);
            System.out.println(t);
        }

        {
            LocalDate date = LocalDate.of(2018, Month.APRIL, 20);
        }

    }

    private static void convertToDate(){
        ZonedDateTime zonedDateTime;
        DateTimeFormatter dateTimeFormatter;
        Date date;

        zonedDateTime = ZonedDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z['z']'", Locale.ENGLISH);
        //zonedDateTime = ZonedDateTime.parse("2020-01-01T11:21:33.333Z[IRKT]", dateTimeFormatter);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.format(dateTimeFormatter));
        System.out.println(zonedDateTime.toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
        System.out.println();

        date = Date.from(zonedDateTime.toInstant());
        System.out.println(date);
        System.out.println(date.getTime());
        System.out.println();

        zonedDateTime = zonedDateTime.withZoneSameLocal(ZoneId.of("UTC"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.format(dateTimeFormatter));
        System.out.println(zonedDateTime.toInstant().toEpochMilli());
        System.out.println();

        date = Date.from(zonedDateTime.toInstant());
        System.out.println(date);
        System.out.println(date.getTime());
        System.out.println();



        /*dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        String time = zonedDateTime.format(dateTimeFormatter);
        System.out.println(time);

        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        System.out.println(localDateTime);*/
    }
}
