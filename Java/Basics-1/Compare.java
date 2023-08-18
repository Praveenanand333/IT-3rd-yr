import java.util.Scanner;
public class Compare {
    public static void main(String[] args){
        Scanner inp =new Scanner(System.in);
        int n1,n2;
        System.out.println("Enter a number");
        n1=inp.nextInt();
        System.out.println("Enter a number");
        n2=inp.nextInt();
        if(n1==n2){
            System.out.println("Equal");
        }
        else if(n1>n2){
            System.out.println("n1 is greater");
        }
        else{
            System.out.println("n2 is greater");
        }
    }
}
