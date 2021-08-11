import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class ByteArrayInputStreamTest {
    public static void main(String[] args) {
        String ss = "3\n01\n02\n03";
        ByteArrayInputStream bais = new ByteArrayInputStream(ss.getBytes());

        BufferedReader br  = new BufferedReader(new InputStreamReader(bais));
        try {

            int count = parseInt(br.readLine());
            String s = "";

            for (int i = 0; i < count; i++) {
                s+=br.readLine();
            }

            System.out.println("-----------\n"+s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
