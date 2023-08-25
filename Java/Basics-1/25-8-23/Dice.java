class Random{
    public int gen(){
        return (int)(Math.random()*6)+1;
    }
}
class Dice{
    public static void main(String[] args){
        Random r =new Random();
        int poss[]=new int[11];
        for(int i=0;i<36000000;i++){
                int a=r.gen();
                int b=r.gen();
                poss[a+b-2]+=1;
               


        }
        for(int i=0;i<11;i++){
             System.out.println(poss[i]);
             
        }
    }
}