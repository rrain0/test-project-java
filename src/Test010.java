import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class Test010 {
    private static final int n = 134;
    private static final double maxX = 200; // exclusive
    private static final double dY = 30; // exclusive
    private static final String fileName = "ex1data2.txt";
    private static final double degrees = 62;
    private static final int b = 351;


    public static void main(String[] args) throws Exception{
        double[][] arr = new double[n][2];

        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < arr.length; i++) {
            double rx = random.nextDouble(maxX);
            double r = random.nextDouble(dY)-dY/2;
            arr[i][0] = rx;
            arr[i][1] = y(rx)+r;
        }

        try(BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))){
            for (int i = 0; i < arr.length; i++) {
                double x = rd2(arr[i][0]), y = rd2(arr[i][1]);
                System.out.println(x+","+y);
                fw.write(x+","+y);
                fw.newLine();
            }
        }

    }

    private static double y(double x){
        final double tg = Math.tan(degrees/180 * Math.PI);
        return x*tg + b;
    }

    private static double rd2(double d){
        return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
}
