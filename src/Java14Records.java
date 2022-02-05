
public class Java14Records {
    public static void main(String[] args) {



        Point p1 = new Point(7, 9);
        System.out.println(p1.x);
        System.out.println(p1.x());
        System.out.println(p1.len());

        System.out.println(getNewTriangle());
    }

    private static Triangle getNewTriangle(){ return new Triangle(8,6,-7); }



    public record Triangle(int x, int y, int z){}
    public record SomeRecord<T>(T a){} //Рекорд с дженерик параметром

    // рекорд может быть отдельным классом-файлом
    // рекорд не может наследовать другие классы, но может реализовывать интерфейсы
    // рекорд не может быть абстрактным
    // рекорд может иметь дженерик поля
    // рекорд может иметь статик поля, но объявлять их в коде рекорда
    record Point(int x, int y) {
        // Все переменные автоматически private final
        // Объявлять в коде рекорда другие нестатик переменные - нельзя

        // Канонический конструктор
        public Point(int x, int y) { this.x = x*2; this.y = y+9; }

        // Работало в JAVA 14 - убрали в JAVA 15/16
        // Компактная форма канонического конструктора public Point {...}
        //public Point { this.x = x*2; this.y = y+9; }




        // Автоматически реализован канонический конструктор для всех этих переменных - можно переопределить
        // Другие конструкторы должны обязательно вызывать канонический
        public Point(Point p){this(p.x, p.y);}

        // Так нельзя, нужно обязательно вызывать исходный (канонический) конструктор
        //public Point(Point p){x=p.x; y=p.y;}





        // автоматически реализованы методы toString, equals, hashCode - можно переопределить
        @Override public String toString() { return "Point("+x+", "+y+")"; }

        // можно писать свои методы
        public double len(){ return Math.sqrt(x*x+y*y); }

    }


    public record Point4(double x, double y, double z, double a){
        // Компактный конструктор (новый)
        // Можно использовать либо компактный конструктор, либо канонический (который со всеми переменными рекорда)
        // В нём уже присвоены аргументы переменным рекорда, так что их менять нельзя
        // И делегировать другому конструктору нельзя (через this(...))
        public Point4 {
            // this(x,y,z,a); // НЕЛЬЗЯ
            // this.x = x+4; // НЕЛЬЗЯ
            System.out.println("x: "+x);
        }


    }


}
