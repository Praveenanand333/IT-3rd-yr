import java.util.Scanner;
class Prod{
    public static void main(String[] args){
        double e=2.71828;
        int a;
        Scanner inp=new Scanner(System.in);
        System.out.println("Enter a:");
        a=inp.nextInt();
        double ans=Math.pow(e, a);
        System.out.println(ans);
    }
}