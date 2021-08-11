import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSockets {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(55500);
            while (true){
                new SocketThread(ss.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SocketThread extends Thread {
        Socket socket;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        BufferedReader br;
        BufferedWriter bw;

        public SocketThread(Socket socket) {
            try {
                this.socket = socket;
                bis = new BufferedInputStream(socket.getInputStream());
                bos = new BufferedOutputStream(socket.getOutputStream());
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (Exception e1){}
                finally {
                    System.out.println("Ошибка открытия сокета");
                }
            }
            System.out.println("Соединение установлено");
        }

        @Override
        public void run() {

        }
    }
}
