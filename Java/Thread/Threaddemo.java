class Threaddemo {
    public static void main(String args[]){
        Thread obj1 =new A();
        Thread obj2 =new B();
        obj1.start();
        obj2.start();
    }
}
class A extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("hi");
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){

            }
        }
    }
}
class B extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("hello");
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                
            }
        }
    }
}