import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class JavaTime {

    // Instant
    // Local Date/Time/DateTime
    // Offset Date/DateTime - просто оффсет от UTC
    // Zoned DateTime - оффсет от UTC + правила таймзоны (например daylight saving)

    public static void main(String[] args) {

        testZonedDateTime();

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

        // паттерн со временем нельзя применить только к дате - будет эксепшн
        /*{
            //форматирование даты времени
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH); //2020-08-26T06:53:27.609+0000
            String t = zonedDateTime.format(f);
            System.out.println(t);
        }*/

        {
            //форматирование даты времени
            LocalDate localDate = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate();
            DateTimeFormatter f = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSZ", //2020-08-26T06:53:27.609+0000
                    Locale.ENGLISH
            );
            String t = localDate.format(f);
            System.out.println("LocalDate formatted:");
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


    // GET Long TIMESTAMP millis
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


    private static void testZonedDateTime(){
        System.out.println("Test ZonedDateTime:");
        Instant instant = Instant.now();

        Clock clockZoned = Clock.fixed(instant, ZoneId.of("America/New_York")); // you have provided zone with daylight saving rules
        Clock clockUtcOffset = Clock.fixed(instant, ZoneId.of("-04:00")); // you have provided just offset without any zone rules
        // UTC+xx:xx GMT+xx:xx +xx:xx - just offsets, not timezones
        // America/New_York - timezone with daylight saving rules, not just offset

        OffsetDateTime offsetDateTimeFromZoned = OffsetDateTime.now(clockZoned);
        ZonedDateTime zonedDateTimeFromZoned = ZonedDateTime.now(clockZoned);

        OffsetDateTime offsetDateTimeFromUtcOffset = OffsetDateTime.now(clockUtcOffset);
        ZonedDateTime zonedDateTimeFromUtcOffset = ZonedDateTime.now(clockUtcOffset);

        System.out.println(offsetDateTimeFromZoned);        // 2023-06-17T07:21:52.067530900-04:00
        System.out.println(zonedDateTimeFromZoned);         // 2023-06-17T07:21:52.067530900-04:00[America/New_York]
        System.out.println(offsetDateTimeFromUtcOffset);    // 2023-06-17T07:21:52.067530900-04:00
        System.out.println(zonedDateTimeFromUtcOffset);     // 2023-06-17T07:21:52.067530900-04:00
        System.out.println();

        OffsetDateTime offsetZonedPlusSixMonths = offsetDateTimeFromZoned.plusMonths(6);
        ZonedDateTime zonedDateTimeZonedPlusSixMonths = zonedDateTimeFromZoned.plusMonths(6);
        OffsetDateTime offsetUtcOffsetPlusSixMonths = offsetDateTimeFromUtcOffset.plusMonths(6);
        ZonedDateTime zonedDateTimeUtcOffsetPlusSixMonths = zonedDateTimeFromUtcOffset.plusMonths(6);

        System.out.println(offsetZonedPlusSixMonths);               // 2023-12-17T07:21:52.067530900-04:00
        System.out.println(zonedDateTimeZonedPlusSixMonths);        // 2023-12-17T07:21:52.067530900-05:00[America/New_York]
        System.out.println(zonedDateTimeZonedPlusSixMonths.toEpochSecond() - offsetZonedPlusSixMonths.toEpochSecond()); // 3600
        System.out.println(offsetUtcOffsetPlusSixMonths);           // 2023-12-17T07:21:52.067530900-04:00
        System.out.println(zonedDateTimeUtcOffsetPlusSixMonths);    // 2023-12-17T07:21:52.067530900-04:00
        System.out.println(zonedDateTimeUtcOffsetPlusSixMonths.toEpochSecond() - offsetUtcOffsetPlusSixMonths.toEpochSecond()); // 0

        System.out.println();
        System.out.println(zonedDateTimeZonedPlusSixMonths.toLocalDateTime());      // 2023-12-17T07:21:52.067530900
        System.out.println(offsetZonedPlusSixMonths.toLocalDateTime());             // 2023-12-17T07:21:52.067530900
        System.out.println(zonedDateTimeUtcOffsetPlusSixMonths.toLocalDateTime());  // 2023-12-17T07:21:52.067530900
        System.out.println(offsetUtcOffsetPlusSixMonths.toLocalDateTime());         // 2023-12-17T07:21:52.067530900

        System.out.println();
        System.out.println();
    }



}
