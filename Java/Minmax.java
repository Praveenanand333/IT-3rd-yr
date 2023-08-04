import java.util.Scanner;
public class Minmax {
    public static void main(String[] args){
        Scanner inp =new Scanner(System.in);
    
        int i;
        int n1=Integer.MIN_VALUE;
        int n2=Integer.MAX_VALUE;
        int n3;
        for(i=0;i<5;i++){
            n3=inp.nextInt();
            if(n3<n2){
                n2=n3;
            }
            if(n3>n1){
                n1=n3;
            }
        }
        System.out.println("Largest number is:"+n1);
        System.out.println("Smallest number is:"+n2);
    }
}
