import java.util.concurrent.*;

public class ExecutorFutureCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<Void> future1 = executor.submit(new CallableTest());
            System.out.println(future1.get()); // -> null
            System.out.println();

            Future<String> future2 = executor.submit(task2);
            System.out.println(future2.get());
            System.out.println();

            Future<?> future3 = executor.submit(task3);
            System.out.println(future3.get()); // -> null
            System.out.println();

            executor.execute(task3);
            System.out.println();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            // необходимо вызвать, иначе программа не завершится из-за активных потоков
            // ждёт завершения старых задач, но уже не принимает новые
            executor.shutdown();
        }

    }


    private static class CallableTest implements Callable<Void>{
        @Override
        public Void call() throws Exception {
            System.out.println("I was called: Callable<Void>");
            return null;
        }
    }

    private static Callable<String> task2 = ()->"return from Callable<String> task2";

    private static Runnable task3 = ()->System.out.println("Inside Runnable task3");

}
