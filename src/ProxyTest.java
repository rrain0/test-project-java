import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

public class ProxyTest {
    public static void main(String[] args) {
        Class[] classes = {Comparable.class, Callable.class};
        //сможем вызывать методы переданных интерфейсов и методы класса Object
        InvocationHandler handler = new MyProxy(5);
        Object proxy = Proxy.newProxyInstance(null, classes, handler);

        System.out.println(proxy.toString());
        System.out.println();
        System.out.println(((Comparable)proxy).compareTo(3));
    }

    static class MyProxy implements InvocationHandler{
        Object target;

        public MyProxy(Object target) { this.target = target; }

        //при вызове любого метода выполняется метод invoke
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(args);//код выполняется перед вызовом метода
            return method.invoke(target, args);//вызов метода
        }
    }

}
