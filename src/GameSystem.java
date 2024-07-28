import java.util.*;

@SuppressWarnings("Duplicates")
public class GameSystem {
    private final Master master = new Master();
    private final Vector<Pet> petDeque = new Vector<>();
    private Pet newPet;
    private Pet pet;
    public void initial(){
        Scanner sc = new Scanner(System.in);
        boolean flag=false;
        String str;
        System.out.println("-----------电子宠物-----------");
        System.out.println("What's your name?");
        master.name=sc.next();
        do {
            System.out.println(Util.line);
            System.out.println("Choose your pet:");
            System.out.println("1. cat");
            System.out.println("2. dog");
            System.out.println("3. penguin");
            System.out.println("4. fox");
            System.out.println("5. rat");
            System.out.println(Util.line);
            int choice = sc.nextInt();
            checkPetSelection(choice);
            System.out.println(Util.line);
            System.out.println("set your pet's name");
            String name;
            name = sc.next();
            newPet.name = name;
            System.out.println(Util.line);
            if (newPet instanceof Cat || newPet instanceof Dog) {
                System.out.println("set your pet's strain");
                newPet.showExistStrains();
                choice = sc.nextInt();
                checkStrainSelection(choice);
            }
            petDeque.add(newPet);
            System.out.println("anymore?(Y/N)");
            str = sc.next();
            boolean flag1=false;
            do {
                if (str.equals("Y")) {

                    flag1=true;
                }else if(str.equals("N")){
                    flag1=true;
                    flag = true;
                }else{
                    System.out.println("invalid choice!");
                    str=sc.next();
                }
            }while (!flag1);
        }while(!flag);

        setTimer();
        chooseCurrentPet();
    }

    private void chooseCurrentPet() {
        System.out.println(Util.line);
        System.out.println("choose your current pet:");
        Scanner sc = new Scanner(System.in);
        boolean flag=false;
        int cnt=1;
        for (var pet:petDeque) {
            System.out.println(cnt+":"+pet.name+" "+pet.getClass());
            cnt++;
        }
        int choice=sc.nextInt();
        do{
            if(choice-1<petDeque.size()){
                pet=petDeque.get(choice-1);
                flag=true;
            }else{
                System.out.println("invalid choice!");
                choice=sc.nextInt();
            }
        }while (!flag);
        startGame();
    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (var pet:petDeque) {
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
            }
        };
        timer.scheduleAtFixedRate(timerTask,0L,1000L*30L);
        TimerTask timerTask1  =new TimerTask() {
            @Override
            public void run() {
                for (var pet:petDeque) {
                    if(pet.love!=0){
                        pet.reduceLove();
                    }
                    if(pet.love==10){
                        System.out.println();
                        System.out.println("Your pet is unhappy! Play with it!");
                        System.out.println();
                    }
                }
            }
        };
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(timerTask1,0L,1000L*45L);

        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random random  = new Random();
                int slicer = random.nextInt(petDeque.size());
                if(!petDeque.get(slicer).isIll) {
                    pet.fallIll();
                }
            }
        },1000L*60L*2L,(new Random().nextInt(10)+1)*60L*1000L);
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
            System.out.println("9. choose pet");
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
                case 9:
                    chooseCurrentPet();
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
            if (newPet.setStrain(choice)) {
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
                    newPet = new Cat();
                    flag=true;
                    break;
                case 2:
                    newPet = new Dog();
                    flag=true;
                    break;
                case 3:
                    newPet = new Penguin();
                    flag=true;
                    break;
                case 4:
                    newPet = new Fox();
                    flag=true;
                    break;
                case 5:
                    newPet = new Rat();
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
