import java.math.BigDecimal;

public class Solution {

    String str;

    public class Building {

        public void getStr() {
            System.out.println(Solution.this.str);
            System.out.println(str);
        }

        String str;

        public class Hall {
            private BigDecimal square;

            public Hall(BigDecimal square) {
                this.square = square;
            }
        }


        public class Apartments {

            public void getStr() {
                Building.this.getStr();
                System.out.println(str);
            }

            String str = "apppp";
        }

    }

    public static void main(String[] args) {

    }



    public class Apt3Bedroom extends Building.Apartments{

        String str;

        public void getStr() {
            super.getStr();
            System.out.println(str);
            System.out.println(Solution.this.str);

        }

        public Apt3Bedroom(Building building){
            building.super();
        }
    }

    /*public class BigHall extends Building.Hall{
        public BigHall(Building.Hall hall) {
            ((Building)hall).super(hall.square);
        }

        *//*public BigHall(Building building) {
            building.super(BigDecimal.TEN);
        }*//*
    }*/
}
