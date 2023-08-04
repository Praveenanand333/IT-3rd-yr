import java.util.Scanner;
public class Pnz {
    public static void main(String[] args){
        Scanner inp =new Scanner(System.in);
        int i,n1;
        int p=0,n=0,z=0;
        System.out.println("Enter the 5 numbers");
        for(i=0;i<5;i++){
            n1=inp.nextInt();
            if(n1==0){
                z+=1;
            }
            else if(n1>0){
                p+=1;
            }
            else{
                n+=1;
            }
        }
        System.out.println("Positive numbers:"+p);
        System.out.println("Neagtive numbers:"+n);
        System.out.println("Zeros:"+z);

    }
}
