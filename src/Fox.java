import java.util.Scanner;

public class Fox extends Pet{
    String[] food =  new String[]{"normal fox food","berry"};
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
                    recover=setNormalFoxFood(master);
                    flag = true;
                    break;
                case 2:
                    recover=setBerry(master);
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

    private int setBerry(Master master) {
        if(master.salary>=10) {
            master.salary -= 10;
            return 10;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }

    private int setNormalFoxFood(Master master) {
        if(master.salary>=5) {
            master.salary -= 5;
            return 5;
        }else{
            System.out.println("can't afford it!");
            return 0;
        }
    }
}
