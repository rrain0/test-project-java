import java.util.Arrays;

public class EnumTest {

    public static void main(String[] args) {

        System.out.println(ValuesEnum.valueOf("FIRST_VALUE"));
        System.out.println(ValuesEnum.valueOf(ValuesEnum.class, "FIRST_VALUE")); // то же, что и ValuesEnum.valueOf("FIRST_VALUE")
        System.out.println(Arrays.toString(ValuesEnum.values()));

        System.out.println(ValuesEnum.FIRST_VALUE.ordinal());
        System.out.println(ValuesEnum.FIRST_VALUE.toString());

    }

    
    private enum ValuesEnum {
        // нельзя FIRST_VALUE = <что-то>,
        FIRST_VALUE,
        SECOND_VALUE,
        THIRD_VALUE;
    }

    private enum ValuesEnum2 {
        // тут же можно прокидывать параметры в конструктор
        FIRST_VALUE(56),
        SECOND_VALUE,
        THIRD_VALUE(-8);

        private int value;

        ValuesEnum2() { }
        ValuesEnum2(int value) { this.value = value; }

        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }
}
