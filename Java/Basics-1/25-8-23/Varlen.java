class Variablelen{
    public  static void func(int ...num){
        int prod=1;
        for(int i:num){
            prod*=i;
        }
        System.out.println(prod);
    }
    public static void main(String[] agrs){
        func(1,2,3);
    }
}