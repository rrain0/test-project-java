import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test006 {
    public static void main(String[] args) throws Exception{
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6666666);

        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String fileName = br.readLine();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(list);
            br.close();
            oos.close();
        }
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String fileName = br.readLine();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));

            Test test = new Test();

            oos.writeObject(test);
            br.close();
            oos.close();
        }


    }

    static class Test implements Externalizable {

        int i = 5;
        String s = "sdfsadkjfnasjkdn";

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(i);
            out.writeObject(s);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            i = in.readInt();
            s = (String)in.readObject();
        }
    }
}
