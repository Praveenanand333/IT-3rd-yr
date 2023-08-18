class Phno{
    public static void main(String[] args){
        int min=1;
        int max=7;
        int secondset=(int)(Math.random()*(655-100+1)+100);
        int thirdset=(int)(Math.random()*(999-100+1)+100);
        int n1=(int)(Math.random()*(max-min+1)+min);
        int n2=(int)(Math.random()*(max-min+1)+min);
        int n3=(int)(Math.random()*(max-min+1)+min);
        String ans="";
        ans+=String.valueOf(n1);
        ans+=String.valueOf(n2);
        ans+=String.valueOf(n3);
        ans+='-';
        ans+=String.valueOf(secondset);
        ans+='-';
        ans+=String.valueOf(thirdset);
        System.out.println(ans);


    }
}