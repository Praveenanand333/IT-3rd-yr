import java.util.Scanner;
class Volsur{
    public static void main(String[] args){
        double r;
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the radius:");
        r=inp.nextDouble();
        double vol=(4/3)*3.14*Math.pow(r, 3);
        double surf=4*3.14*Math.pow(r,2);
        System.out.println("Volume is:"+vol+" \nSurface area is:"+surf);
        



    }
}