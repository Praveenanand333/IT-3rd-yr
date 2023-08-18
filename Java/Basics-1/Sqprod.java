import java.util.Scanner;
class Sqprod{
    public static void main(String[] args){
        Scanner inp=new Scanner(System.in);
        int a,b,c;
        System.out.println("Enter a:");
        a=inp.nextInt();
        System.out.println("Enter b:");
        b=inp.nextInt();
        System.out.println("Enter c:");
        c=inp.nextInt();
        int prod=a*b*c;
        int ans=prod*prod;
        System.out.println(ans);


    }
}