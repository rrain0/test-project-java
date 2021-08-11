import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class RadixConversionAndRoundTest {

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(2.1234500000);
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        bd = bd.round(mc);
        System.out.println(bd);

        BigDecimal bd1 = new BigDecimal(2.1234500000);
        bd1 = bd1.setScale(10, RoundingMode.HALF_UP);
        System.out.println(bd1);


    }

    public static double roundUp(double number, int digits) throws Exception{
        if (Double.isNaN(number) || number == Double.POSITIVE_INFINITY || number == Double.NEGATIVE_INFINITY)
            return number;
        return new BigDecimal(number).setScale(digits, RoundingMode.HALF_UP).doubleValue();
    }
    public static String roundUp(String number, int digits) throws Exception{
        if (number.equals("NaN") || number.equals("Infinity") || number.equals("-Infinity"))
            return number;
        return new BigDecimal(number).setScale(digits, RoundingMode.HALF_UP).toString();
    }




    public static String DecToOther(String number, int targetRadix)throws Exception{ //отрефакторено
        if (targetRadix==10) return number;
        switch (number) {
            case "NaN":                     return "NaN";
            case "Infinity": case "∞":      return "∞";
            case "-Infinity": case "-∞":    return "-∞";
        }

        String number1 = "";
        String number2 = "";

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i)=='.') {
                number1 = number.substring(0, i);
                number2 = "0" + number.substring(i,number.length());
                break;
            }
            if (i == number.length()-1) number1 = number;
        }

        BigInteger[] arr = {
                new BigInteger("10"),
                new BigInteger("11"),
                new BigInteger("12"),
                new BigInteger("13"),
                new BigInteger("14"),
                new BigInteger("15"),
                new BigInteger(Integer.toString(targetRadix))
        };

        BigInteger n1 = new BigInteger(number1);
        String part1 = "";
        while(-1 != n1.compareTo(arr[6])){
            BigInteger p = n1.mod(arr[6]);
            n1 = n1.divide(arr[6]);
            if      (p.equals(arr[0])) part1 = "A" + part1;
            else if (p.equals(arr[1])) part1 = "B" + part1;
            else if (p.equals(arr[2])) part1 = "C" + part1;
            else if (p.equals(arr[3])) part1 = "D" + part1;
            else if (p.equals(arr[4])) part1 = "E" + part1;
            else if (p.equals(arr[5])) part1 = "F" + part1;
            else part1 = String.valueOf(p) + part1;
        }
        if      (n1.equals(arr[0])) part1 = "A" + part1;
        else if (n1.equals(arr[1])) part1 = "B" + part1;
        else if (n1.equals(arr[2])) part1 = "C" + part1;
        else if (n1.equals(arr[3])) part1 = "D" + part1;
        else if (n1.equals(arr[4])) part1 = "E" + part1;
        else if (n1.equals(arr[5])) part1 = "F" + part1;
        else part1 = String.valueOf(n1) + part1;



        if (number2.isEmpty()) return part1;
        else {
            BigDecimal n2 = new BigDecimal(number2);
            String part2 = "";

            for (int i = 0; i < 15; i++) {
                if (0 == n2.compareTo(BigDecimal.ZERO)) break;
                n2 = n2.multiply(new BigDecimal(targetRadix));
                BigInteger p = n2.toBigInteger();
                n2 = n2.remainder(BigDecimal.ONE);

                if (p.equals(arr[0])) part2 += "A";
                else if (p.equals(arr[1])) part2 += "B";
                else if (p.equals(arr[2])) part2 += "C";
                else if (p.equals(arr[3])) part2 += "D";
                else if (p.equals(arr[4])) part2 += "E";
                else if (p.equals(arr[5])) part2 += "F";
                else part2 += String.valueOf(p);
            }

            return part1 + "." + part2;
        }

    }


    static Object[] toDec(String expr, int start, int radix) throws Exception{
        Object[] numberArray = new Object[2];

        if (expr.length()-start>0) if (expr.substring(start, start+1).equals("∞")) {numberArray[0]="Infinity"; numberArray[1]=1; return numberArray;}
        if (expr.length()-start>1) if (expr.substring(start, start+2).equals("-∞")) {numberArray[0]="-Infinity"; numberArray[1]=2; return numberArray;}
        if (expr.length()-start>7) if (expr.substring(start, start+8).equals("Infinity")) {numberArray[0]="Infinity"; numberArray[1]=8; return numberArray;}
        if (expr.length()-start>8) if (expr.substring(start, start+9).equals("-Infinity")) {numberArray[0]="Infinity"; numberArray[1]=9; return numberArray;}
        if (expr.length()-start>2) if (expr.substring(start, start+3).equals("NaN")) {numberArray[0]="NaN"; numberArray[1]=3; return numberArray;}

        ArrayList<String> number = new ArrayList<>();
        for (int i = start; i < expr.length(); i++) {
            switch (expr.charAt(i)){
                case'0': if (radix>0) number.add("0"); else throw new Exception(); continue;
                case'.':case',': number.add("."); continue;
                case'1': if (radix>1) number.add("1"); else throw new Exception(); continue;
                case'2': if (radix>2) number.add("2"); else throw new Exception(); continue;
                case'3': if (radix>3) number.add("3"); else throw new Exception(); continue;
                case'4': if (radix>4) number.add("4"); else throw new Exception(); continue;
                case'5': if (radix>5) number.add("5"); else throw new Exception(); continue;
                case'6': if (radix>6) number.add("6"); else throw new Exception(); continue;
                case'7': if (radix>7) number.add("7"); else throw new Exception(); continue;
                case'8': if (radix>8) number.add("8"); else throw new Exception(); continue;
                case'9': if (radix>9) number.add("9"); else throw new Exception(); continue;
                case'A': if (radix>10) number.add("10"); else throw new Exception(); continue;
                case'B': if (radix>11) number.add("11"); else throw new Exception(); continue;
                case'C': if (radix>12) number.add("12"); else throw new Exception(); continue;
                case'D': if (radix>13) number.add("13"); else throw new Exception(); continue;
                case'E': if (radix>14) number.add("14");
                else if (radix==10){
                    switch (expr.charAt(i+1)){
                        case'+': number.add("E"); number.add("+"); i++; break;
                        case'-': number.add("E"); number.add("-"); i++; break;
                        default: number.add("E"); break;
                    }
                } else throw new Exception(); continue;
                case'F': if (radix>15) number.add("15"); else throw new Exception(); continue;
            }
            break;
        }
        if (number.size()==0) throw new Exception();

        numberArray[1] = number.size();

        if (radix==10) {
            String n = "";
            for (int i = 0; i < number.size(); i++) n+=number.get(i);
            numberArray[0] = n;
            return numberArray;
        }

        int pointPosition = number.size();
        for (int i = 0; i < number.size(); i++)
            if (number.get(i).equals(".")){pointPosition = i; break;}



        BigDecimal finalNumber = new BigDecimal(0);
        for (int i = 0; i < pointPosition; i++) {
            finalNumber=finalNumber.add((new BigDecimal(number.get(i))).multiply((new BigDecimal(radix)).pow(pointPosition-1-i)));
        }
        int degree = -1;
        for (int i = pointPosition+1; i < number.size(); i++) {
            finalNumber=finalNumber.add((new BigDecimal(number.get(i))).multiply(new BigDecimal(Math.pow(radix, degree))));
            degree--;
        }

        numberArray[0]= finalNumber.stripTrailingZeros().toPlainString();
        return numberArray;
    }


    static String toDecPlainString(String number, int radix) throws Exception{
        switch (number) {
            case "NaN": return number;
            case "Infinity": case "∞": return "∞";
            case "-Infinity": case "-∞": return "-∞";
        }
        if (radix!=10) return (String)toDec(number, 0, radix)[0];

        return new BigDecimal(number).stripTrailingZeros().toPlainString();
    }
}
