
public class Test001 {
    public static void main(String[] args) {
        Comparator<Integer> compInt= new Comparator<>(3, 7);

        Comparator<Double> compDouble= new Comparator<>(3d, 7d);

        System.out.println(compInt.compare());
        System.out.println(compDouble.compare());
    }

    static class Comparator<T>{
        T a;
        T b;

        public Comparator(T a, T b){
            this.a = a;
            this.b = b;
        }

        public int compare(){
            if (a instanceof Integer) return compare((int)a, (int)b);
            if (a instanceof Double) return compare((double)a, (double)b);
            return 0;
        }

        private static int compare(double a, double b){
            return Double.compare(a, b);
        }

        private static int compare(int a, int b){
            return Integer.compare(a, b);
        }
    }
}
