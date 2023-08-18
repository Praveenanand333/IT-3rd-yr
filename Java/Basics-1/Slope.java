import java.util.Scanner;
class Slope{
    public static void main(String[] args){
        double x1,x2,y1,y2;
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter x1:");
        x1=inp.nextDouble();
        System.out.println("Enter y1:");
         y1=inp.nextDouble();
        System.out.println("Enter x2:");
         x2=inp.nextDouble();
        System.out.println("Enter y2:");
         y2=inp.nextDouble();
         double slope=(y2-y1)/(x2-x1);
         System.out.println(slope);



    }
}