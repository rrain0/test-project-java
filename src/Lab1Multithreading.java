
import java.util.concurrent.ThreadLocalRandom;

public class Lab1Multithreading {

    //вариант 3

    final private BuffArr buff = new BuffArr();

    //код второго потока
    final private Runnable secondThread = () -> {
        try {
            while (true){
                Thread.sleep(5000);
                int val = 0;
                try {
                    val = buff.getMinAndClear();
                } catch (BuffArr.EmptyBuffException e) {
                    e.printStackTrace();
                    continue;
                }
                System.out.println("SECOND thread: " + val);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };


    public static void main(String[] args) throws Exception {
/*

        Runnable rr = new Runnable() {
            @Override
            public void run() {

            }
        };

        //Class
        System.out.println(Class.forName("java.lang.Runnable"));
        Class cl = Class.forName("java.lang.Runnable");


        Method m = Runnable.class.getMethod("run");
        System.out.println(m);
        //Function f =;

*/





        Lab1Multithreading lab = new Lab1Multithreading();

        //запуск второго потока, который читает из буффера
        new Thread(lab.secondThread).start();

        //основной поток кидает данные в буффер
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (true){
            int n = random.nextInt(1001);
            lab.buff.add(n);
            System.out.println("MAIN thread: " + n);
            Thread.sleep(250);
        }

    }

    private static class BuffArr{
        private final int sz = 10;
        private final int[] buffArr = new int[sz];
        private int len = 0;
        private int start = 0;

        public synchronized void add(int n) throws InterruptedException {
            while (true){
                if (len<sz) {
                    int idx = (start+len)%sz;
                    buffArr[idx] = n;
                    len++;
                    this.notifyAll();
                    break;
                } else {
                    this.wait();
                }
            }
        }



        public synchronized int getMinAndClear() throws InterruptedException, EmptyBuffException {
            if (len>0) {
                int min = buffArr[start];
                for (int i = start, cnt = len; cnt > 0; i=(i+1)%sz, cnt--) {
                    min = Integer.min(min, buffArr[i]);
                }
                start = 0;
                len = 0;
                this.notifyAll();
                return min;
            } else {
                throw new EmptyBuffException();
            }
        }

        public static class EmptyBuffException extends Exception {}

        /*public synchronized int poll() throws InterruptedException {
            while (true){
                if (len>0) {
                    int val = buffArr[start];
                    start = (start+1)%sz;
                    len--;
                    this.notifyAll();
                    return val;
                } else {
                    this.wait();
                }
            }
        }*/
    }
}
