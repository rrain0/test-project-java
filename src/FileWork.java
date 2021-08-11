import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWork {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            readFile("d:\\temp\\1.txt", "d:\\temp\\1out.txt", Integer.parseInt(br.readLine()));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void readFile(String filein, String fileout, int mode){
        if(filein==null || fileout==null) {
            System.out.println("не задано имя файла");
            return;
        }
            System.out.println(filein);
            System.out.println(fileout);

        File fin = new File(filein);
        if(!fin.exists()) {
            System.out.println("исходный файл не найден");
            return;
        }
        File fout = new File(fileout);

        /*if (fout.isFile()) fout.delete();*/

        switch (mode){
            case 1:
                try {
                    try(BufferedReader reader = new BufferedReader(new FileReader(fin));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fout))){
                        char cbuf[] = new char[40];
                        int k;
                        while((k = reader.read(cbuf))>0){
                            System.out.print(cbuf);
                            writer.write(cbuf, 0, k);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("ERROR:"+e);
                }
                break;

            case 2:
                try {
                    try(BufferedReader reader = new BufferedReader(new FileReader(fin));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fout))){
                        String line;
                        while((line=reader.readLine())!=null){
                            System.out.print(line+"\r\n");
                            writer.write(line+"\r\n");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("ERROR:"+e);
                }
                break;

            case 3:
                try {
                    try(InputStreamReader reader = new InputStreamReader(new FileInputStream(fin), "utf8");
                        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fout), "cp1251")){
                        char cbuf[] = new char[40];
                        int k;
                        while((k = reader.read(cbuf))>0){
                            System.out.print(cbuf);
                            writer.write(cbuf, 0, k);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("ERROR:"+e);
                }
                break;

            case 4:
                try {
                    try(FileInputStream fis = new FileInputStream(fin);
                        FileOutputStream fos = new FileOutputStream(fout)){
                        char c;
                        int b;
                        int size = fis.available();
                        for (int i = 0; i < size; i++) {
                            b = fis.read();
                            c = (char) b;
                            System.out.println(c);
                            fos.write(b);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("ERROR:"+e);
                }
                break;
        }

    }


}
