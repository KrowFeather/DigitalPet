import java.util.Scanner;

public class Cat extends Pet{
    String[] strains = new String[]{"RagDoll","Siamese","Birman","ShortHair"};
    String[] food =  new String[]{"normal cat food","milk","fish"};
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
    public int setFood(int choice,Master master) {
        boolean flag=false;
        Scanner sc = new Scanner(System.in);
        int recover=0;
        do {
            switch (choice) {
                case 1:
                    recover=setNormalCatFood(master);
                    flag = true;
                    break;
                case 2:
                    recover=setMilk(master);
                    flag = true;
                    break;
                case 3:
                    recover=setFish(master);
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

    private int setFish(Master master) {
        if(master.salary>=20) {
            master.salary -= 20;
            return 25;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setMilk(Master master) {
        if(master.salary>=10) {
            master.salary -= 10;
            return 10;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setNormalCatFood(Master master) {
        if(master.salary>=5) {
            master.salary -= 5;
            return 5;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    @Override
    public void showExistFood() {
        for(int i=0;i<food.length;i++){
            System.out.println(i+1+". "+food[i]);
        }
    }
}
