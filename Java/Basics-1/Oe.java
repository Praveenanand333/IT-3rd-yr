import java.util.Scanner;
public class Oe {
    public static void main(String[] args){
        int n1;
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter the number:");
        n1=inp.nextInt();
        if(n1%2==0){
            System.out.println("Number is even");
        }
        else{
            System.out.println("Number is odd");
        }

    }
}
