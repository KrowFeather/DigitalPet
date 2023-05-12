import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
@SuppressWarnings("Duplicates")
public class GameSystem {
    private final Master master = new Master();
    private Pet pet;
    public void initial(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------电子宠物-----------");
        System.out.println("Choose your pet:");
        System.out.println("1. cat");
        System.out.println("2. dog");
        System.out.println("3. penguin");
        System.out.println("4. fox");
        System.out.println("5. rat");
        System.out.println(Util.line);
        int choice=sc.nextInt();
        checkPetSelection(choice);
        System.out.println(Util.line);
        System.out.println("set your pet's name");
        String name;
        name=sc.next();
        pet.name = name;
        System.out.println(Util.line);
        if(pet instanceof Cat || pet instanceof Dog) {
            System.out.println("set your pet's strain");
            pet.showExistStrains();
            choice=sc.nextInt();
            checkStrainSelection(choice);
        }
        setTimer();
        startGame();
    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(pet.hungry!=0){
                    pet.reduceHunger();
                }else{
                    pet.reduceHealth();
                }
                if(pet.hungry==10){
                    System.out.println();
                    System.out.println("Your pet is hungry! Please feed it!");
                    System.out.println();
                }
                if(pet.health==10){
                    System.out.println();
                    System.out.println("Your pet's health is low! Take it to the hospital!");
                    System.out.println();
                }
                if(pet.health==0){
                    System.out.println();
                    System.out.println("Your pet is dead! Game over!");
                    System.exit(0);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask,0L,1000L*30L);
        TimerTask timerTask1  =new TimerTask() {
            @Override
            public void run() {
                if(pet.love!=0){
                    pet.reduceLove();
                }
                if(pet.love==10){
                    System.out.println();
                    System.out.println("Your pet is unhappy! Play with it!");
                    System.out.println();
                }
            }
        };
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(timerTask1,0L,1000L*45L);
    }

    private void startGame() {
        while(true) {
            System.out.println(Util.line);
            System.out.println("1. touch");
            System.out.println("2. feed");
            System.out.println("3. play");
            System.out.println("4. cure");
            System.out.println("5. work");
            System.out.println("6. relax");
            System.out.println("7. status");
            System.out.println("8. master's status");
            System.out.println("0. exit");
            System.out.println(Util.line);
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            checkMasterAction(choice);
        }
    }

    private void checkMasterAction(int choice) {
        boolean flag= false;
        Scanner sc = new Scanner(System.in);
        do{
            switch (choice){
                case 0:
                    System.exit(0);
                    flag=true;
                    break;
                case 1:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.touch(pet);
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 2:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.feed(pet);
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 3:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.play(pet);
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 4:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.toHospital(pet);
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 5:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.work();
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 6:
                    if(!master.isRelaxing&&!master.isWorking){
                        master.relax();
                    }else{
                        System.out.println("Occupied!");
                    }
                    flag=true;
                    break;
                case 7:
                    pet.showStatus();
                    flag=true;
                    break;
                case 8:
                    master.showStatus();
                    flag=true;
                    break;
                default:
                    System.out.println("invalid choice!");
                    choice=sc.nextInt();
                    break;
            }
        }while(!flag);

        flag=false;
        System.out.println("输入0返回");
        choice=sc.nextInt();
        do{
            if(choice==0){
                flag=true;
            }else{
                System.out.println("invalid choice");
            }
        }while(!flag);
    }

    private void checkStrainSelection(int choice) {
        boolean flag= false;
        Scanner sc = new Scanner(System.in);
        do {
            if (pet.setStrain(choice)) {
                flag=true;
            }else {
                System.out.println("invalid choice");
                choice=sc.nextInt();
            }
        }while(!flag);
    }

    private void checkPetSelection(int choice) {
        boolean flag= false;
        Scanner sc = new Scanner(System.in);
        do{
            switch (choice){
                case 1:
                    pet = new Cat();
                    flag=true;
                    break;
                case 2:
                    pet = new Dog();
                    flag=true;
                    break;
                case 3:
                    pet = new Penguin();
                    flag=true;
                    break;
                case 4:
                    pet = new Fox();
                    flag=true;
                    break;
                case 5:
                    pet = new Rat();
                    flag=true;
                    break;
                default:
                    System.out.println("invalid choice!");
                    choice=sc.nextInt();
                    break;
            }
        }while(!flag);
    }
}
