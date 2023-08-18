import java.util.Scanner;
class Country{
    public static void main(String[] args){
       Scanner inp=new Scanner(System.in);
        String  country;
        String city;
        System.out.println("Enter your country:");
        country=inp.nextLine();
        System.out.println("Enter your city:");
        city=inp.nextLine();
        String ans="";
        ans=country.substring(0, 2);
        ans+=',';
        ans+=city;
        ans+=':';
        int r=(int)(Math.random()*100);
        ans+=String.valueOf(r);
        System.out.println(ans);



    }
}