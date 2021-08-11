import java.io.*;

public class Serialization {
    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/11.txt"));
            Test00 t = new Test00("t", 6, 5);
            oos.writeObject(t);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/11.txt"));
            Test00 t1 = (Test00)ois.readObject();
            ois.close();

            System.out.println(t1); //Test00{s='t', i=6, d=0.0}
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Test00 implements Serializable {
        String s;
        int i;
        transient double d = 7; //transient означает, что сериализовать не надо

        public Test00(String s, int i) {
            this.s = s;
            this.i = i;
        }

        public Test00(String s, int i, double d) {
            this.s = s;
            this.i = i;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Test00{" +
                    "s='" + s + '\'' +
                    ", i=" + i +
                    ", d=" + d +
                    '}';
        }
    }

}
