import java.util.Scanner;

public class Rat extends Pet{
    String[] food =  new String[]{"normal rat food","grain","nut"};
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
                    recover=setNormalRatFood(master);
                    flag = true;
                    break;
                case 2:
                    recover=setGrain(master);
                    flag = true;
                    break;
                case 3:
                    recover=setNut(master);
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

    private int setNut(Master master) {
        if(master.salary>=25) {
            master.salary -= 25;
            return 25;
        }else{
            return 0;
        }
    }

    private int setGrain(Master master) {
        if(master.salary>=20) {
            master.salary -= 20;
            return 15;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setNormalRatFood(Master master) {
        if(master.salary>=5) {
            master.salary -= 5;
            return 5;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }
}
