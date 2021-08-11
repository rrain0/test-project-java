package calculation2;

public class Elem {
    public int s; //start index inclusive
    public int e; //end index exclusive

    public String name;

    public Elem(){}

    public Elem(int s) {
        this.s = s;
    }

    public Elem(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public Elem(int s, int e, String name) {
        this.s = s;
        this.e = e;
        this.name = name;
    }
}
