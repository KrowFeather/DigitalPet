import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
@SuppressWarnings("Duplicates")
public class Master {
    public boolean isWorking;
    public boolean isRelaxing;
    public long salary;
    public int sanity;
    private final Timer timer = new Timer();
    public Master(){
        this.salary=100;
        this.sanity=100;
    }
    public void feed(Pet pet){
        System.out.println(Util.line);
        System.out.println("Choose the food:");
        pet.showExistFood();
        Scanner sc = new Scanner(System.in);
        int choice=sc.nextInt();
        int recover=pet.setFood(choice,this);
        if (pet.hungry <= 100-recover) {
            pet.hungry += recover;
        }else if (pet.hungry > 100-recover && pet.hungry < 100) {
            pet.hungry += 100 - pet.hungry;
        }
        System.out.println(pet.name + "的饥饿度为:" + pet.hungry);
        System.out.println(Util.line);
    }

    public void touch(Pet pet) {
        System.out.println(Util.line);
        if(pet.love<=98) {
            pet.love += 2;
        }else if(pet.love == 99){
            pet.love+=100-pet.love;
        }
        System.out.println(pet.name+"的情感值为:"+pet.love);
        System.out.println(Util.line);
    }

    public void play(Pet pet) {
        System.out.println(Util.line);
        if(pet.love<=95) {
            pet.love += 5;
        }else if(pet.love<100){
            pet.love+=100-pet.love;
        }
        System.out.println(pet.name+"的情感值为:"+pet.love);
        System.out.println(Util.line);
    }

    public void toHospital(Pet pet){
        System.out.println(Util.line);
        if(pet.health<100){
            pet.health+=100-pet.health;
            this.salary-=50;
        }
        System.out.println(pet.name+"的生命值为:"+pet.love);
        System.out.println(Util.line);
    }

    public void work(){
        if(!isWorking) {
            System.out.println("WorkList:");
            System.out.println("1. Wash dishes");
            System.out.println("2. DJ");
            System.out.println("3. Code");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            checkWorkChoice(choice);
        }else{
            System.out.println("You are working");
        }
    }

    private void checkWorkChoice(int choice) {
        isWorking=true;
        boolean flag=false;
        do{
            switch (choice) {
                case 1:
                    washDishes();
                    flag=true;
                    break;
                case 2:
                    DJ();
                    flag=true;
                    break;
                case 3:
                    Code();
                    flag=true;
                    break;
                default:
                    System.out.println("invalid choice!");
                    break;
            }
        }while(!flag);
    }

    private void Code() {
        if(this.sanity<40){
            System.out.println("No enough sanity!");
            return;
        }
        System.out.println(Util.line);
        System.out.println("working......");
        System.out.println(Util.line);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Util.line);
                System.out.println("work is done!");
                System.out.println("sanity - 40");
                System.out.println("salary + 50");
                System.out.println(Util.line);
                salary+=50;
                sanity-=40;
                isWorking=false;
            }
        },1000L*180L);
    }

    private void DJ() {
        if(this.sanity<10){
            System.out.println("No enough sanity!");
            return;
        }
        System.out.println(Util.line);
        System.out.println("working......");
        System.out.println(Util.line);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Util.line);
                System.out.println("work is done!");
                System.out.println("sanity - 10");
                System.out.println("salary + 20");
                System.out.println(Util.line);
                salary+=20;
                sanity-=10;
                isWorking=false;
            }
        },1000L*60L);
    }

    private void washDishes() {
        if(this.sanity<5){
            System.out.println("No enough sanity!");
            return;
        }
        System.out.println(Util.line);
        System.out.println("working......");
        System.out.println(Util.line);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Util.line);
                System.out.println("work is done!");
                System.out.println("sanity - 5");
                System.out.println("salary + 10");
                System.out.println(Util.line);
                salary+=10;
                sanity-=5;
                isWorking=false;
            }
        },1000L*30L);
    }

    public void showStatus() {
        System.out.println(Util.line);
        System.out.println("Salary:"+this.salary);
        System.out.println("Sanity:"+this.sanity);
        System.out.println(Util.line);
    }

    public void relax() {
        System.out.println(Util.line);
        System.out.println("relaxing....");
        isRelaxing=true;
        System.out.println(Util.line);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Util.line);
                System.out.println("relaxing is done");
                System.out.println("sanity + 10");
                System.out.println(Util.line);
                sanity+=10;
                isRelaxing=false;
            }
        },1000L*10L);
    }
}
