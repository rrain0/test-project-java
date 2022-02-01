public class RSymbolTest {

    /*
        символ \r - возврат каретки - возвращает курсор в начало текущей линии (но не на предыдущую линию)
     */

    public static void main(String[] args) throws InterruptedException {
        test2();
    }


    private static void loading() throws InterruptedException {
        while(true){
            System.out.print("\r|");
            Thread.sleep(200);
            System.out.print("\r/");
            Thread.sleep(200);
            System.out.print("\r-");
            Thread.sleep(200);
            System.out.print("\r\\");
            Thread.sleep(200);
        }
    }


    private static void test2() throws InterruptedException {
        System.out.print("skladjfkj");
        Thread.sleep(1000);
        System.out.print("\r"); // никак не отображается, пока не начнёшь печатать
        Thread.sleep(1000);
        System.out.print("aaaa");
        System.out.print("\n");
        System.out.print("bbbb");
        System.out.print("\r");
        System.out.print("\r");
        System.out.print("\r"); // на предыдущую линию не попасть
        System.out.print("cccc");
    }


}
