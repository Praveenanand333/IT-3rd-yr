import java.util.Scanner;
public class Bmi {
    public static void main(String[] args){
    Scanner inp=new Scanner(System.in);
    double w,h,bmi;
    System.out.println("Enter your weight:");
    w=inp.nextDouble();
     System.out.println("Enter your height:");
     h=inp.nextDouble();
     bmi=w/(h*h);
     System.out.println("Your bmi is:"+bmi);
     if(bmi>30){
        System.out.println("obese");
       
     }
    else if(bmi>=25){
        System.out.println("overweight");
     
    }
    else if(bmi>=18.5){
    System.out.println("Normal");

}
else{
    System.out.println("under-weight");
  
}


}
}