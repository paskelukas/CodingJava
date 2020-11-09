import java.util.Vector;

public class ManoTankas extends Tankas {
    Vector<Kulka> kulkaVector = new Vector<Kulka>();
    Kulka kulka = null;
    ManoTankas(int x, int y)
    {
        super(x,y);
    }
    public void virsun()
    {
        setY(getY()- getGreitis());
    }
    public  void zemyn()
    {
        setY(getY()+ getGreitis());
    }
    public  void kairen()
    {
        setX(getX()- getGreitis());
    }
    public  void desinen()
    {
        setX(getX()+ getGreitis());
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