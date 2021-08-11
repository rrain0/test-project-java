public class Test002 {
    public static void main(String[] args) {

        Solution s1 = new Solution();                           s1.str = "s1.obj";
        Solution s2 = new Solution();                           s2.str = "s2.obj";

        Solution.Building b1 = s1.new Building();               b1.str = "b1.obj";
        Solution.Building b2 = s2.new Building();               b2.str = "b2.obj";



        Solution.Apt3Bedroom apt1 = s1.new Apt3Bedroom(b2);     apt1.str = "apt1.obj";

        System.out.println(apt1.str);
        apt1.getStr();
    }
}
