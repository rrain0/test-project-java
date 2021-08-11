public class PairwiseGenericSum {
    public static void main(String[] args) {
        System.out.println(pairwiseSum(5, 8, -9, 0));
        System.out.println(pairwiseSum("5", "8", "-9", "0"));
        System.out.println(pairwiseSum(null, null, null, null));
    }

    public static <T> DataCarrier<T, T> pairwiseSum(T v1, T v2, T v3, T v4){
        if (v1 instanceof String && v2 instanceof String && v3 instanceof String && v4 instanceof String)
            return new DataCarrier<>((T)((String)v1+(String)v2), (T)((String)v3+(String)v4));
        else if (v1 instanceof Integer && v2 instanceof Integer && v3 instanceof Integer && v4 instanceof Integer)
            return new DataCarrier<>((T)(Integer)((Integer)v1+(Integer)v2), (T)(Integer)((Integer)v3+(Integer)v4));
        else
            return null;
    }


    public static class DataCarrier<V1, V2>{
        V1 v1;
        V2 v2;

        public DataCarrier(){}

        public DataCarrier(V1 v1, V2 v2){
            this.v1 = v1;
            this.v2 = v2;
        }

        public V1 getV1() { return v1; }
        public void setV1(V1 v1) { this.v1 = v1; }
        public V2 getV2() { return v2; }
        public void setV2(V2 v2) { this.v2 = v2; }

        @Override
        public String toString() {
            return "DataCarrier:\n" +
                    "\t" + v1.getClass().getSimpleName() + ": " + v1 + "\n" +
                    "\t" + v2.getClass().getSimpleName() + ": " + v2 + "\n";
        }
    }
}
