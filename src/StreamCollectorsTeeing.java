import utils.DataCarrier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCollectorsTeeing {
    public static void main(String[] args) {
        int[] ints = {1 ,6, -7, 0, 3, 4};
        System.out.println(getMinMax(ints));
        collectEventParticipations();
    }

    private static DataCarrier<Integer, Integer> getMinMax(int... ints){
        return IntStream.of(ints).boxed().collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (i1, i2) -> new DataCarrier<>(i1.get(), i2.get())
                )
        );
    }

    public static void collectEventParticipations(){
        EventParticipation result =
                Stream.of(
                        // Guest(String name, boolean participating, Integer participantsNumber)
                        new Guest("Marco", true, 3),
                        new Guest("David", false, 2),
                        new Guest("Roger",true, 6))
                        .collect(Collectors.teeing(
                                // Первый коллектор, мы выбираем только тех, кто подтвердил участие
                                Collectors.filtering(
                                    // выбираем только тех, кто подтвердил участие
                                    Guest::isParticipating,
                                    // мы хотим взять только имя в списке
                                    Collectors.mapping(o -> o.name, Collectors.toList())),
                                // второй коллектор, мы хотим найти общее количество участников
                                Collectors.summingInt(Guest::getParticipantsNumber),
                                // мы объединяем коллекторы в новый объект,
                                // значения передаются неявно
                                EventParticipation::new
                        ));
        System.out.println(result);
    }

    static class Guest {
        private String name;
        private boolean participating;
        private Integer participantsNumber;
        public Guest(String name, boolean participating,
                     Integer participantsNumber) {
            this.name = name;
            this.participating = participating;
            this.participantsNumber = participantsNumber;
        }
        public boolean isParticipating() {
            return participating;
        }
        public Integer getParticipantsNumber() {
            return participantsNumber;
        }
    }

    static class EventParticipation {
        private List<String> guestNameList;
        private Integer totalNumberOfParticipants;
        public EventParticipation(List<String> guestNameList,
                                  Integer totalNumberOfParticipants) {
            this.guestNameList = guestNameList;
            this.totalNumberOfParticipants = totalNumberOfParticipants;
        }
        @Override
        public String toString() {
            return "EventParticipation { " +
                    "guests = " + guestNameList +
                    ", total number of participants = " + totalNumberOfParticipants +
                    " }";
        }}
}
