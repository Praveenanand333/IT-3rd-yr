import java.util.Scanner;
public class Calc{
    public static void main(String[] args){
        Scanner inp =new Scanner(System.in);
        int n1,n2,s,d,p,q;
        System.out.println("Enter a number");
        n1=inp.nextInt();
        System.out.println("Enter a number");
        n2=inp.nextInt();
        s=n1+n2;
        d=n1-n2;
        p=n1*n2;
        q=n1/n2;
        System.out.println("sum-"+s);
        System.out.println("Difference-"+d);
        System.out.println("Product-"+p);
        System.out.println("Quotient-"+q);

    }
}