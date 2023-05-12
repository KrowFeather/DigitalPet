import java.util.Scanner;

public class Dog extends Pet{
    String[] strains = new String[]{"Pekingese","Beagle","Bulldog","Doberman"};
    String[] food =  new String[]{"normal dog food","bone","meat"};
    @Override
    public void showExistStrains(){
        System.out.println("Existing strains:");
        for(int i=0;i<strains.length;i++){
            System.out.println(i+1+". "+strains[i]);
        }
    }
    @Override
    public boolean setStrain(int choice) {
        if(choice-1<strains.length){
            this.strain=strains[choice-1];
            return true;
        } else{
            return false;
        }
    }
    @Override
    public void showExistFood() {
        for(int i=0;i<food.length;i++){
            System.out.println(i+1+". "+food[i]);
        }
    }
    @Override
    public int setFood(int choice,Master master) {
        boolean flag=false;
        Scanner sc = new Scanner(System.in);
        int recover=0;
        do {
            switch (choice) {
                case 1:
                    recover=setNormalDogFood(master);
                    flag = true;
                    break;
                case 2:
                    recover=setBone(master);
                    flag = true;
                    break;
                case 3:
                    recover=setMeat(master);
                    flag = true;
                    break;
                default:
                    System.out.println("invalid choice");
                    choice=sc.nextInt();
                    break;
            }
        }while(!flag);
        return recover;
    }

    private int setMeat(Master master) {
        if(master.salary>=50) {
            master.salary -= 50;
            return 40;
        }else {
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setBone(Master master) {
        if(master.salary>=25) {
            master.salary -= 25;
            return 20;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setNormalDogFood(Master master) {
        if(master.salary>=10) {
            master.salary -= 10;
            return 10;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }
}
