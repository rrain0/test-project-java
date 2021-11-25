import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class JavaTime {

    // Instant
    // Offset Date/DateTime
    // Local Date/Time/DateTime
    // Zoned DateTime

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

        convertToDate();

    }

    private static void convertToDate(){
        ZonedDateTime zonedDateTime;
        LocalDate localDate;
        DateTimeFormatter dateTimeFormatter;
        DateTimeFormatter dateTimeFormatter2;
        OffsetDateTime offsetDateTime;
        Date date;
        Instant instant;

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

        // from instant (long millis) to any
        instant = Instant.ofEpochMilli(1628328759000L); // суббота, 7 августа 2021 г., 17:32:39 GMT+08:00
        dateTimeFormatter2 = DateTimeFormatter.ofPattern("d-M-yyyy");
        //offsetDateTime = instant.atOffset(ZoneOffset.ofHours(9)); // тоже работает
        offsetDateTime = OffsetDateTime.ofInstant(instant, ZoneId.of("GMT+8"));
        zonedDateTime = offsetDateTime.toZonedDateTime();
        localDate = offsetDateTime.toLocalDate();
        System.out.println(zonedDateTime);
        System.out.println(localDate);
        System.out.println(localDate.format(dateTimeFormatter2));
        System.out.println();



        /*dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        String time = zonedDateTime.format(dateTimeFormatter);
        System.out.println(time);

        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        System.out.println(localDateTime);*/
    }


    private static Long convertYyyyMMddHHmmssSSSGmt3ToTimestamp(String dateTimeRaw){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeRaw, formatter);
            ZonedDateTime toZoned = dateTime.atZone(ZoneId.of("GMT+3"));
            Long timestamp = toZoned.toInstant().toEpochMilli();
            return timestamp;
        } catch (Exception e) {
            /*e.printStackTrace();*/
        }
        return null;
    }






}
