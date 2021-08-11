import java.util.List;

public class Test003 {

    public static void main(String[] args) {
        method1(List.<B>of(new B()));
        method2(List.of(new B()));
    }

    private static void method1(List<? extends A> list){}
    private static void method2(List<A> list){}

    // <? extends Object> => <?>
    private static void method3(List<? extends Object> list){}

    private static class A{}
    private static class B extends A{}
}
