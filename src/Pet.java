import java.util.Timer;
import java.util.TimerTask;

public abstract class Pet{
    public String name;
    public int health;
    public int hungry;
    public int love;
    public boolean isIll;
    public String strain;
    private Timer timer = new Timer();
    public Pet(){
        this.health=100;
        this.love=60;
        this.hungry=100;
        this.name="无名氏";
    }
    public void reduceHunger(){
        this.hungry--;
    }

    public void fallIll(){
        isIll=true;
        System.out.println();
        System.out.println("Your pet is ill,take it to the hospital");
        System.out.println();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reduceHealth();
            }
        },0L,1000L*5L);
    }

    public void showStatus() {
        System.out.println(Util.line);
        System.out.println("name:"+this.name);
        System.out.println("hunger:"+this.hungry);
        System.out.println("love:"+this.love);
        System.out.println("health:"+this.health);
        if(this instanceof Cat || this instanceof Dog) {
            System.out.println("strain:" + this.strain);
        }
        System.out.println(Util.line);
    }

    public void showExistStrains(){
    }

    public boolean setStrain(int choice){
        return false;
    }

    public void reduceHealth() {
        this.health--;
    }

    public void reduceLove() {
        this.love--;
    }
    public abstract int setFood(int choice,Master master);
    public abstract void showExistFood();
}

