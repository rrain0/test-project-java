package expr;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class testExpr {

    public static void main(String[] args) throws Exception{
        testExpr test = new testExpr();
        test.exprList.add(1d);
        test.exprList.add(Add.getInstance());
        test.exprList.add(Log.getInstance());
        test.exprList.add(2d);
        test.exprList.add(16d);

        test.calculatePart1(0, test.exprList.size()-1);

        System.out.println( (double)test.exprList.get(0));



    }




ArrayList<Object> exprList = new ArrayList<>();


    @FunctionalInterface
    static interface ApplyFunction{
        double apply(double... args) throws Exception;
    }

    static abstract class Func implements ApplyFunction {
        int priority;

        int preArgs;
        int postArgs;

        protected static final int PRE_FUNC_PRIOR = 6;
        protected static final int POST_FUNC_PRIOR = 5;
        protected static final int POW_PRIOR = 4;
        protected static final int DIVIDE_PRIOR = 3;
        protected static final int MULT_PRIOR = 2;
        protected static final int MINUS_PRIOR = 1;
        protected static final int PLUS_PRIOR = 0;

        public static int maxPriority = PRE_FUNC_PRIOR;

        public int argsLength(){return preArgs+postArgs;}

        public Func(int priority, int preArgs, int postArgs) {
            this.priority = priority;
            this.preArgs = preArgs;
            this.postArgs = postArgs;
        }



        /*private static Map<Class<Func>, Func> instances = new HashMap<>();
        public static <T extends Func> Func getInst(Class<T> funcClass){
            try {
                if (instances.get(funcClass)==null) instances.put(funcClass, funcClass.newInstance());
                return instances.get(funcClass);
            } catch (Exception e) {
                return null;
            }
        }*/
    }


    static class Add extends Func {
        private Add() { super(Func.PLUS_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return args[0]+args[1]; }

        private static Add instance;
        public static Add getInstance() {
            if (instance == null) instance = new Add();
            return instance;
        }
    }

    static class Subtract extends Func {
        private Subtract() { super(Func.MINUS_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return args[0]-args[1]; }

        private static Subtract instance;
        public static Subtract getInstance() {
            if (instance == null) instance = new Subtract();
            return instance;
        }
    }

    static class Mult extends Func {
        private Mult() { super(Func.MULT_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return args[0]*args[1]; }

        private static Mult instance;
        public static Mult getInstance() {
            if (instance == null) instance = new Mult();
            return instance;
        }
    }

    static class Div extends Func {
        private Div() { super(Func.MULT_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return args[0]/args[1]; }

        private static Div instance;
        public static Div getInstance() {
            if (instance == null) instance = new Div();
            return instance;
        }
    }

    static class Pow extends Func {
        private Pow() { super(Func.POW_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return Math.pow(args[0], args[1]); }

        private static Pow instance;
        public static Pow getInstance() {
            if (instance == null) instance = new Pow();
            return instance;
        }
    }






    static class PowPow extends Func {
        private PowPow() { super(Func.POST_FUNC_PRIOR, 1, 1); }

        @Override
        public double apply(double... args) { return Math.pow(args[0], args[1]); }

        private static PowPow instance;
        public static PowPow getInstance() {
            if (instance == null) instance = new PowPow();
            return instance;
        }
    }

    static class Factorial extends Func {
        private Factorial() { super(Func.POST_FUNC_PRIOR, 1, 0); }

        @Override
        public double apply(double... args) {
            int b = 0;
            try {
                b = new BigDecimal(args[0]).intValueExact();//если было 2.0, то возвратит 2; но если было 2.1, то будет исключение
                if (b<0) throw new Exception();
            } catch (Exception e) {return Double.NaN;}
            if(b==0) return 1d;
            double factorial = 1d;
            for (int i = 2; i <= b; i++) factorial*=i;
            return factorial;
        }

        private static Factorial instance;
        public static Factorial getInstance() {
            if (instance == null) instance = new Factorial();
            return instance;
        }
    }

    static class DoubleFactorial extends Func {
        private DoubleFactorial() { super(Func.POST_FUNC_PRIOR, 1, 0); }

        @Override
        public double apply(double... args) {
            int b = 0;
            try {
                b = new BigDecimal(args[0]).intValueExact();//если было 2.0, то возвпатит 2; но если было 2.1, то будет исключение
                if (b<0) throw new Exception();
            } catch (Exception e) {return Double.NaN;}
            if(b==0) return 1d;
            double doubleFactorial;
            int i;
            if (b%2==0) {doubleFactorial = 2d; i = 4;} else {doubleFactorial = 1d; i = 3;}
            for (; i <= b; i+=2) doubleFactorial*=i;
            return doubleFactorial;
        }

        private static DoubleFactorial instance;
        public static DoubleFactorial getInstance() {
            if (instance == null) instance = new DoubleFactorial();
            return instance;
        }
    }

    static class IsDegrees extends Func {
        private IsDegrees() { super(Func.POST_FUNC_PRIOR, 1, 0); }

        @Override
        public double apply(double... args) { return args[1]==1 ? args[0] : Math.toRadians(args[0]); }

        private static IsDegrees instance;
        public static IsDegrees getInstance() {
            if (instance == null) instance = new IsDegrees();
            return instance;
        }
    }

    static class Percent extends Func {
        private Percent() { super(Func.POST_FUNC_PRIOR, 1, 0); }

        @Override
        public double apply(double... args) { return args[0]*0.01; }

        private static Percent instance;
        public static Percent getInstance() {
            if (instance == null) instance = new Percent();
            return instance;
        }
    }




    static class Sqrt extends Func {
        private Sqrt() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.sqrt(args[0]); }

        private static Sqrt instance;
        public static Sqrt getInstance() {
            if (instance == null) instance = new Sqrt();
            return instance;
        }
    }
    static class Cbrt extends Func {
        private Cbrt() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.cbrt(args[0]); }

        private static Cbrt instance;
        public static Cbrt getInstance() {
            if (instance == null) instance = new Cbrt();
            return instance;
        }
    }

    static class Sin extends Func {
        private Sin() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            if (args[1]==1) args[0]=Math.toRadians(args[0]);
            return Math.sin(args[0]);
        }

        private static Sin instance;
        public static Sin getInstance() {
            if (instance == null) instance = new Sin();
            return instance;
        }
    }
    static class Cos extends Func {
        private Cos() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            if (args[1]==1) args[0]=Math.toRadians(args[0]);
            return Math.cos(args[0]);
        }

        private static Cos instance;
        public static Cos getInstance() {
            if (instance == null) instance = new Cos();
            return instance;
        }
    }
    static class Tg extends Func {
        private Tg() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            if (args[1]==1) args[0]=Math.toRadians(args[0]);
            return Math.tan(args[0]);
        }

        private static Tg instance;
        public static Tg getInstance() {
            if (instance == null) instance = new Tg();
            return instance;
        }
    }
    static class Ctg extends Func {
        private Ctg() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            if (args[1]==1) args[0]=Math.toRadians(args[0]);
            return 1d/Math.tan(args[0]);
        }

        private static Ctg instance;
        public static Ctg getInstance() {
            if (instance == null) instance = new Ctg();
            return instance;
        }
    }
    static class Arcsin extends Func {
        private Arcsin() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            args[0] = Math.asin(args[0]);
            return args[1]==0 ? args[0] : Math.toDegrees(args[0]);
        }

        private static Arcsin instance;
        public static Arcsin getInstance() {
            if (instance == null) instance = new Arcsin();
            return instance;
        }
    }
    static class Arccos extends Func {
        private Arccos() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            args[0] = Math.acos(args[0]);
            return args[1]==0 ? args[0] : Math.toDegrees(args[0]);
        }

        private static Arccos instance;
        public static Arccos getInstance() {
            if (instance == null) instance = new Arccos();
            return instance;
        }
    }
    static class Arctg extends Func {
        private Arctg() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            args[0] = Math.atan(args[0]);
            return args[1]==0 ? args[0] : Math.toDegrees(args[0]);
        }

        private static Arctg instance;
        public static Arctg getInstance() {
            if (instance == null) instance = new Arctg();
            return instance;
        }
    }
    static class ArcCtg extends Func {
        private ArcCtg() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            args[0] = Math.PI/2d - Math.atan(args[0]);
            return args[1]==0 ? args[0] : Math.toDegrees(args[0]);
        }

        private static ArcCtg instance;
        public static ArcCtg getInstance() {
            if (instance == null) instance = new ArcCtg();
            return instance;
        }
    }

    static class Sh extends Func {
        private Sh() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.sinh(args[0]); }

        private static Sh instance;
        public static Sh getInstance() {
            if (instance == null) instance = new Sh();
            return instance;
        }
    }
    static class Ch extends Func {
        private Ch() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.cosh(args[0]); }

        private static Ch instance;
        public static Ch getInstance() {
            if (instance == null) instance = new Ch();
            return instance;
        }
    }
    static class Th extends Func {
        private Th() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.tanh(args[0]); }

        private static Th instance;
        public static Th getInstance() {
            if (instance == null) instance = new Th();
            return instance;
        }
    }
    static class Cth extends Func {
        private Cth() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return 1d/Math.tanh(args[0]); }

        private static Cth instance;
        public static Cth getInstance() {
            if (instance == null) instance = new Cth();
            return instance;
        }
    }
    static class Arsh extends Func {
        private Arsh() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log(args[0]+Math.sqrt(Math.pow(args[0], 2d)+1d)); }

        private static Arsh instance;
        public static Arsh getInstance() {
            if (instance == null) instance = new Arsh();
            return instance;
        }
    }
    static class Arch extends Func {
        private Arch() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log(args[0]+Math.sqrt(Math.pow(args[0], 2d)-1d)); }

        private static Arch instance;
        public static Arch getInstance() {
            if (instance == null) instance = new Arch();
            return instance;
        }
    }
    static class Arth extends Func {
        private Arth() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log((1d+args[0])/(1d-args[0]))/2d; }

        private static Arth instance;
        public static Arth getInstance() {
            if (instance == null) instance = new Arth();
            return instance;
        }
    }
    static class ArCth extends Func {
        private ArCth() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log((args[0]+1d)/(args[0]-1d))/2d; }

        private static ArCth instance;
        public static ArCth getInstance() {
            if (instance == null) instance = new ArCth();
            return instance;
        }
    }

    static class Ln extends Func {
        private Ln() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log(args[0]); }

        private static Ln instance;
        public static Ln getInstance() {
            if (instance == null) instance = new Ln();
            return instance;
        }
    }
    static class Lg extends Func {
        private Lg() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log10(args[0]); }

        private static Lg instance;
        public static Lg getInstance() {
            if (instance == null) instance = new Lg();
            return instance;
        }
    }
    static class Lb extends Func {
        private Lb() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.log(args[0])/Math.log(2d); }

        private static Lb instance;
        public static Lb getInstance() {
            if (instance == null) instance = new Lb();
            return instance;
        }
    }
    static class Log extends Func {
        private Log() {
            super(Func.PRE_FUNC_PRIOR, 0, 2);
        }

        @Override
        public double apply(double... args) {
            return Math.log(args[1])/Math.log(args[0]);
        }

        private static Log instance;
        public static Log getInstance() {
            if (instance == null) instance = new Log();
            return instance;
        }
    }



    static class Abs extends Func {
        private Abs() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.abs(args[0]); }

        private static Abs instance;
        public static Abs getInstance() {
            if (instance == null) instance = new Abs();
            return instance;
        }
    }
    static class Signum extends Func {
        private Signum() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) { return Math.signum(args[0]); }

        private static Signum instance;
        public static Signum getInstance() {
            if (instance == null) instance = new Signum();
            return instance;
        }
    }
    static class Involute extends Func {
        private Involute() { super(Func.PRE_FUNC_PRIOR, 0, 1); }

        @Override
        public double apply(double... args) {
            return Tg.getInstance().apply(args[0], args[1]) - ((args[1]==1) ? Math.toRadians(args[0]) : args[0]);
        }

        private static Involute instance;
        public static Involute getInstance() {
            if (instance == null) instance = new Involute();
            return instance;
        }
    }




    private void calculatePart1(final int start, int end) throws Exception{
        int u;
        Func func;
        double[] args;

        for (int i = Func.maxPriority; i >=0 ; i--) {
            u = start;
            while(u<=end){

                if (exprList.get(u) instanceof Func && ((Func) exprList.get(u)).priority == i){
                    func = ((Func) exprList.get(u));
                    args = new double[func.argsLength()];

                    exprList.remove(u);
                    u-=func.preArgs;
                    for (int j = 0; j < func.argsLength(); j++) {
                        args[j] = (double)exprList.get(u);
                        exprList.remove(u);
                    }
                    exprList.add(u, func.apply(args));
                    end-=func.argsLength();
                }

                u++;
            }
        }


/*
            exprList.remove(start+1); exprList.remove(start-1); // удаление скобок
            */
    }




    public static String toDec(String number, int radix) throws Exception{
        if (radix==10) return number;
        switch (number) {
            case "NaN":                     return "NaN";
            case "Infinity": case "∞":      return "∞";
            case "-Infinity": case "-∞":    return "-∞";
        }

        String sign = "";
        if (number.startsWith("+")) number = number.substring(1, number.length());
        if (number.startsWith("-")) {number = number.substring(1, number.length()); sign = "-";}


        List<String> part1 = new ArrayList<>();
        List<String> part2 = new ArrayList<>();

        List<String> list = part1;
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)){
                case'.':case',':       list = part2;                               continue;
                case'0': if (radix>0)  list.add("0");  else throw new Exception(); continue;
                case'1': if (radix>1)  list.add("1");  else throw new Exception(); continue;
                case'2': if (radix>2)  list.add("2");  else throw new Exception(); continue;
                case'3': if (radix>3)  list.add("3");  else throw new Exception(); continue;
                case'4': if (radix>4)  list.add("4");  else throw new Exception(); continue;
                case'5': if (radix>5)  list.add("5");  else throw new Exception(); continue;
                case'6': if (radix>6)  list.add("6");  else throw new Exception(); continue;
                case'7': if (radix>7)  list.add("7");  else throw new Exception(); continue;
                case'8': if (radix>8)  list.add("8");  else throw new Exception(); continue;
                case'9': if (radix>9)  list.add("9");  else throw new Exception(); continue;
                case'A': if (radix>10) list.add("10"); else throw new Exception(); continue;
                case'B': if (radix>11) list.add("11"); else throw new Exception(); continue;
                case'C': if (radix>12) list.add("12"); else throw new Exception(); continue;
                case'D': if (radix>13) list.add("13"); else throw new Exception(); continue;
                case'E': if (radix>14) list.add("14"); else throw new Exception(); continue;
                case'F': if (radix>15) list.add("15"); else throw new Exception(); continue;
            }
            throw new Exception();
        }
        if (part1.size()==0) throw new Exception();

        BigDecimal finalNumber = BigDecimal.ZERO;

        for (int i = 0; i < part1.size(); i++)
            finalNumber=finalNumber.add(new BigDecimal(part1.get(i)).multiply(new BigDecimal(radix).pow(part1.size()-1-i)));

        for (int i = 0; i < part2.size(); i++)
            finalNumber=finalNumber.add(new BigDecimal(part2.get(i)).multiply(new BigDecimal(Math.pow(radix, -(i+1)))));

        return sign + stripTrailingZeros(finalNumber.toPlainString());
    }









    public static Object[] toDec(String expr, int start, int radix) throws Exception{
        Object[] numberArray = new Object[2];

        if (expr.length()-start>0) if (expr.substring(start, start+1).equals("∞")) {numberArray[0]="Infinity"; numberArray[1]=1; return numberArray;}
        if (expr.length()-start>1) if (expr.substring(start, start+2).equals("-∞")) {numberArray[0]="-Infinity"; numberArray[1]=2; return numberArray;}
        if (expr.length()-start>7) if (expr.substring(start, start+8).equals("Infinity")) {numberArray[0]="Infinity"; numberArray[1]=8; return numberArray;}
        if (expr.length()-start>8) if (expr.substring(start, start+9).equals("-Infinity")) {numberArray[0]="-Infinity"; numberArray[1]=9; return numberArray;}
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













    public static String roundUp(String number, int newScale) throws Exception{
        if (number.equals("NaN") || number.equals("Infinity") || number.equals("-Infinity"))
            return number;
        return new BigDecimal(number).setScale(newScale, RoundingMode.HALF_UP).toString();
    }

    public static String toPlainString(String number) throws Exception{
        if (number.equals("NaN") || number.equals("Infinity") || number.equals("-Infinity"))
            return number;
        return new BigDecimal(number).toPlainString();
    }

    public static String stripTrailingZeros(String number) throws Exception{
        if (number.equals("NaN") || number.equals("Infinity") || number.equals("-Infinity"))
            return number;

        if (number.contains("."))
            for (int i = number.length()-1; i >=0 ; i--) {
                if (number.charAt(i) != '0'){
                    if (number.charAt(i) != '.') number = number.substring(0, i+1);
                    else number = number.substring(0, i);
                    break;
                }
            }

        return number;
    }

    public static String redesignateInfinities(String number) throws Exception{
        switch (number){
            case "Infinity": return "∞";
            case "-Infinity": return "-∞";
            default: return number;
        }
    }





    public static String decToOther(String number, int targetRadix)throws Exception{
        if (targetRadix==10) return number;
        switch (number) {
            case "NaN":                     return "NaN";
            case "Infinity": case "∞":      return "∞";
            case "-Infinity": case "-∞":    return "-∞";
        }

        String sign = "";
        if (number.startsWith("+")) number = number.substring(1, number.length());
        if (number.startsWith("-")) {number = number.substring(1, number.length()); sign = "-";}

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



        if (number2.isEmpty()) return sign + part1;
        else {
            BigDecimal n2 = new BigDecimal(number2);
            String part2 = "";

            for (int i = 0; i < 15; i++) {
                if (0 == n2.compareTo(BigDecimal.ZERO)) break;
                n2 = n2.multiply(new BigDecimal(targetRadix));
                BigInteger p = n2.toBigInteger();
                n2 = n2.remainder(BigDecimal.ONE);

                if      (p.equals(arr[0])) part2 += "A";
                else if (p.equals(arr[1])) part2 += "B";
                else if (p.equals(arr[2])) part2 += "C";
                else if (p.equals(arr[3])) part2 += "D";
                else if (p.equals(arr[4])) part2 += "E";
                else if (p.equals(arr[5])) part2 += "F";
                else part2 += String.valueOf(p);
            }

            return sign + part1 + "." + part2;
        }

    }





    public static String exponentFormat(String number, int mode) throws Exception{
        if (mode<=0) return number;
        switch (number) {
            case "NaN":                     return "NaN";
            case "Infinity": case "∞":      return "∞";
            case "-Infinity": case "-∞":    return "-∞";
        }

        String sign = "";
        if (number.startsWith("+")) number = number.substring(1, number.length());
        if (number.startsWith("-")) {number = number.substring(1, number.length()); sign = "-";}

        int pointIndex;
        if (!number.contains(".")) number += '.';
        pointIndex = number.indexOf('.');


        int exponent;

        if (pointIndex > 6){

            exponent = pointIndex-1 - (pointIndex-1) % mode;

            number = number.substring(0, pointIndex-exponent) + '.' + number.substring(pointIndex-exponent, pointIndex) +
                    number.substring(pointIndex+1, number.length());
            number = stripTrailingZeros(number);
            number += "E" + exponent;
        } else if(number.startsWith("0.000000")){
            exponent = 0;
            String number1 = number.substring(2, number.length());
            for (int i = 0; i < number1.length(); i++) {
                if (number1.charAt(i) != '0') {
                    exponent = (i+mode)/mode * mode;
                    number1 = number1.substring(i/mode * mode, number1.length());
                    break;
                }
            }

            while (number1.length() < mode) number1+="0";
            if (number1.length()>mode) number1 = number1.substring(0, mode)+"."+number1.substring(mode, number1.length());

            for (int i = 0; i < number1.length(); i++) {
                if (number1.charAt(i)!='0') {number1 = number1.substring(i, number1.length()); break;}
            }

            number = number1 + "E" + (-exponent);
        } else if (pointIndex==number.length()-1) number = number.substring(0, number.length()-1);


        return sign+number;
    }


    public static String bitDivision(String number, String divider, int radix) throws Exception{
        if (divider == null) return number;
        switch (number) {
            case "NaN":                     return "NaN";
            case "Infinity": case "∞":      return "∞";
            case "-Infinity": case "-∞":    return "-∞";
        }

        int sectionLength;
        switch (radix){
            case 2:
            case 8: sectionLength = 4; break;
            case 10:sectionLength = 3; break;
            case 16:sectionLength = 2; break;
            default:return number;
        }

        String sign = "";
        if (number.startsWith("+")) number = number.substring(1, number.length());
        if (number.startsWith("-")) {number = number.substring(1, number.length()); sign = "-";}


        StringBuilder part1 = null;
        StringBuilder part2 = null;
        StringBuilder expPart = null;

        int end;
        if (radix == 10 && number.contains("E")) {
            end = number.indexOf('E');
            expPart = new StringBuilder(number.substring(end, number.length()));
        } else end = number.length();

        if (number.contains(".")) {
            part1 = new StringBuilder(number.substring(0, number.indexOf('.')));
            part2 = new StringBuilder(number.substring(number.indexOf('.')+1, end));
        } else part1 = new StringBuilder(number.substring(0, end));

        part1.reverse();
        for (int i = part1.length()-1; i >= sectionLength; i--) {
            if (i%sectionLength == 0) part1.insert(i, divider);
        }
        part1.reverse();

        if (part2 != null)
        for (int i = part2.length()-1; i >= sectionLength; i--) {
            if (i%sectionLength == 0) part2.insert(i, divider);
        }



        String finalNumber = sign + part1;
        if (part2 != null) {finalNumber+="."+part2;}
        if (expPart != null) {finalNumber+=expPart;}

        return finalNumber;
        /*int expIndex;
        int pointIndex;

        if (!number.contains(".")) pointIndex = number.length();
        else pointIndex = number.indexOf('.');

        if (radix == 10 && number.contains("E")) expIndex = number.indexOf("E");
        else  expIndex = number.length();

        StringBuilder sb = new StringBuilder(number);

        for (int i = expIndex-1; i > pointIndex+sectionLength; i--) {
            if ((i-pointIndex-1)%sectionLength==0) sb.insert(i, divider);
        }

        for (int i = pointIndex-sectionLength; i > pointIndex+sectionLength; i--) {
            if ((i-pointIndex-1)%sectionLength==0) sb.insert(i, divider);
        }*/








        /*int pointIndex;
        if (!number.contains(".")) number += '.';
        pointIndex = number.indexOf('.');


        int exponent;

        if (pointIndex > 6){

            exponent = pointIndex-1 - (pointIndex-1) % mode;

            number = number.substring(0, pointIndex-exponent) + '.' + number.substring(pointIndex-exponent, pointIndex) +
                    number.substring(pointIndex+1, number.length());
            number = stripTrailingZeros(number);
            number += "E" + exponent;
        } else if(number.startsWith("0.") && number.length() - pointIndex - 1 >= 6){

            exponent = (number.length() - pointIndex - 1) - (number.length() - pointIndex - 1) % mode;

            number = number.substring(0, pointIndex) + number.substring(pointIndex+1, pointIndex+exponent+1) + '.' +
                    number.substring(pointIndex+exponent+1, number.length());
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i)!='0') {number = number.substring(i, number.length()); break;}
            }
            number = stripTrailingZeros(number);
            number += "E" + (-exponent);
        } else if (pointIndex==number.length()-1) number = number.substring(0, number.length()-1);*/

        /*sb.insert(0, sign);
        return sb.toString();*/
    }


    public static String removeBitDividers(String number){
        return number.replaceAll("[\n _]", "");
    }
}
