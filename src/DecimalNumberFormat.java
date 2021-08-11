import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DecimalNumberFormat {
    public static void main(String[] args) {
        //DecimalFormat extends NumberFormat
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH); //чтобы были точки вместо запятых для дробной части
        format.setGroupingUsed(false); //отключить разделение разрядов
        format.setMinimumFractionDigits(2); //минимум цифр в дробной части
        format.setMaximumFractionDigits(2); //максимум цифр в дробной части
        format.setRoundingMode(RoundingMode.HALF_UP); //режим округления

        if (format instanceof DecimalFormat f) System.out.println(f.toPattern());
        if (format instanceof DecimalFormat f) System.out.println(new DecimalFormat(f.toPattern()).getRoundingMode()); //строковое представление паттерна не хранит режим округления
        if (format instanceof DecimalFormat f) System.out.println(new DecimalFormat(f.toPattern()).getMaximumFractionDigits()); //строковое представление паттерна хранит количество разрядов в строке

        System.out.println();

        double[] arr = {123.45, 3485689, 123.46, 123.444, 98798.1, 555.556, 555.5555, 555.555,};
        for(double d : arr) System.out.println(d + " -> " + format.format(d));

        System.out.println();

        System.out.println(roundUp(555.5, 0));
        System.out.println(roundUp(555.55, 1)); //косяк
        System.out.println(roundUp(555.555, 2)); //косяк
        System.out.println(roundUp(555.5555, 3));
        System.out.println(roundUp(555.55555, 4));
        System.out.println(roundUp(555.555555, 5));
    }


    private static double roundUp(double d, int scale){
        return new BigDecimal(d).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }



}
