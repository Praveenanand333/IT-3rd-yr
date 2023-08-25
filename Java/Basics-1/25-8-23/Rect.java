class Rectangle{
    public double len=1;
    public double wid=1;
    public void per(){
        double p=2*(len+wid);
        System.out.println(p);
    }
    public void area(){
        double ar=len*wid;
        System.out.println(ar);
    }
    public void setlen(double l){
        if(l<20.0 && l>0)len=l;
        else {
            System.out.println("Enter between 0 and 20");
        }
    }
    public void setwid(double w){
        if(w<20.0 && w>0)wid=w;
        else {
            System.out.println("Enter between 0 and 20");
        }
    }
    public double getlen(){return len;}
    public double getwid(){return wid;}

}
class Rect{
public static void main(String[] args){
    Rectangle r=new Rectangle();
    r.setlen(10);
    r.setwid(8);
    System.out.println("Area is:");
    r.area();
    System.out.println("Perimeter is:");
    r.per();
}
}