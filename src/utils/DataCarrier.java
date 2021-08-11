package utils;

public class DataCarrier<V1, V2>{
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
