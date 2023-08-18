class Para{
    public static void main(String[] args){
        int max=30;
        int min=10;
        int a=(int)(Math.random()*(max-min+1)+min); 
        int b = (int)(Math.random()*(max-min+1)+min); 
        int h=(int)(Math.random()*(max-min+1)+min); 
        int area=b*h;
        int per=2*(a+b);
        System.out.println("Area:"+area+"\nPerimeter:"+per);

    }
}
 