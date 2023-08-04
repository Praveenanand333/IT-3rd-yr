import java.util.Scanner;
public class Digit {
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        int n1=inp.nextInt();
        int d=0;
        while(n1>0){
            n1=n1/10;
            d+=1;
        }
        if(d==5){
            System.out.println("Five-digits");
        }
        else if(d>5){
            System.out.println("Greater than five digits");
        }
        else{
            System.out.println("Less than five digits");
        }
    }
}
