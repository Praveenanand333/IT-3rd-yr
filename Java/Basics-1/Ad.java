import java.util.Scanner;
public class Ad {
    public static void main(String[] args){
        int r;
        double a,d;
      
        Scanner inp=new Scanner(System.in);
        r=inp.nextInt();
        a=3.14*r*r;
        d=2*r;
        System.out.println("Area:"+a);
        System.out.println("Diameter:"+d);


    }
}
