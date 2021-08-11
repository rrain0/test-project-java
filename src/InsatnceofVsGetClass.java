public class InsatnceofVsGetClass {

    static class A{}
    static class B extends A{}

    public static void main(String[] args) {
        A a1 = new A();
        A b1 = new B();

        System.out.println(b1 instanceof A);            //true
        System.out.println(b1 instanceof B);            //true
        System.out.println(b1.getClass()==B.class);     //true
        System.out.println(b1.getClass()==A.class);     //false
    }
}
