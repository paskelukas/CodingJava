import java.util.Vector;

public class PriesininkuTankas extends Tankas implements Runnable {
    PriesininkuTankas(int x, int y)
    {
        super(x,y);
    }
    Vector<Kulka> kulkaVector = new Vector<Kulka>();
    Kulka kulka =null;
    public  void run()
    {
        int flag=1;
        int flag2=1;
        while (true)
        {
            flag++;
            if(flag==15)
            {
                flag=0;
                int puse1=(int)(Math.random()*4);
                setPuse(puse1);
            }
            flag2=(int)(Math.random()*20);
            if(flag2%3==0)
            {
                sauti();
            }
            switch (getPuse())
            {

                case 0: // on
                    if(!(getY()==550&&getX()<=330||getY()==550&&getX()>=430&&getX()<=730||getY()==550&&getX()>=830)&&getY()>=0)
                        setY(getY() - getGreitis());
                    System.out.println(getGreitis());
                    break;
                case 1: // Left
                    if (!(getX()==330&&getY()>=420&&getY()<=550||getX()==730&&getY()>=420&&getY()<=550)&&getX()>=0)
                        setX(getX() - getGreitis());
                    break;

                case 2:
                    if(!(getY()==420&&getX()<=330||getY()==420&&getX()>=430&&getX()<=730||getY()==420&&getX()>=830)&&getY()<=930)
                        setY(getY() + getGreitis());
                    break;
                case 3:
                    if(!(getX()==400&&getY()>=420&&getY()<=550||getX()==800&&getY()>=420&&getY()<=550)&&getX()<=1170)
                        setX(getX() + getGreitis());
                    break;
            }
            try {
                Thread.sleep(200);
            }
            catch (Exception e)
            {
            }
        }
    }
    public void sauti()
    {
        if(kulkaVector.size()<7)
        {
            switch (this.getPuse())
            {
                case 0:
                    kulka = new Kulka(this.getX()+8,this.getY()-7,0);
                    kulkaVector.add(kulka);
                    break;
                case 1:
                    kulka = new Kulka(this.getX()-7,this.getY()+8,1);
                    kulkaVector.add(kulka);
                    break;
                case 2:
                    kulka = new Kulka(this.getX()+8,this.getY()+30,2);
                    kulkaVector.add(kulka);
                    break;
                case 3:
                    kulka = new Kulka(this.getX()+30,this.getY()+8,3);
                    kulkaVector.add(kulka);
                    break;
            }
            Thread thread = new Thread(kulka);
            thread.start();
        }
    }
}