import java.io.*;
import java.util.ArrayList;

public class ExternalizableTest {

    public static void main(String[] args) throws Exception{
        ArrayList<String[]> list = new ArrayList<>(){{add(new String[]{"3+3","6"});add(new String[]{"4+4","8"});}};

        FileOutputStream fos = new FileOutputStream("D:\\1\\e1.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        SavingHistory sh = new SavingHistory(list);
        sh.writeExternal(oos);
        fos.close();

        FileInputStream fis = new FileInputStream("D:\\1\\e1.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        sh = new SavingHistory();
        sh.readExternal(ois);
        fis.close();
        list=sh.list;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                System.out.println(list.get(i)[j]);
            }
        }

    }



    static class SavingHistory implements Externalizable {
        ArrayList<String[]> list;
        public SavingHistory() {}
        public SavingHistory(ArrayList<String[]> list) {
            this.list = list;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length; j++) {
                    out.writeUTF(list.get(i)[j]);
                }
            }
        /*for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length; j++) {
                    out.write(list.get(i)[j].getBytes());
                    if(j<list.get(i).length-1) out.write('\u001E');
                }
                if(i<list.size()-1) out.write('\u001D');
            }
        */}

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            list=new ArrayList<>();
            while(in.available()>0){
                list.add(new String[]{in.readUTF(), in.readUTF()});
            }
        /*byte[] buffer = new byte[1000];
            StringBuffer s = new StringBuffer();
            list=new ArrayList<>();
            while(in.available()>0){
                int read;
                read = in.read(buffer);
                s.append(new String(buffer,0,read));
            }
            String[] entries = (new String(s)).split("\u001D");
            for (int i = 0; i < entries.length; i++) {
                list.add(entries[i].split("\u001E"));
            }
        */}
    }


}


