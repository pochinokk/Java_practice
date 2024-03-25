package practice4;

class MyThread extends Thread{
    String name;
    public MyThread(String name){
        this.name = name;
    }

    public void run(){
        for(int i = 0; i < 10; i++)
        {
            System.out.println(name);

        }
    }
}
