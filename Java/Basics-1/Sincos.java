public class Sincos {
    public static void main(String[] args){
        int a=(int)((Math.random()*100)%20);
        int b=(int)((Math.random()*100)%20);
        int c=a+b;
        System.out.println("Sin:"+Math.sin(c));
        System.out.println("Cos:"+Math.cos(c));

    }
}
