public class Kulka implements Runnable {
    private  int greitis =20;
    private  int puse;
    boolean gyvas =true; //ar kulka dar keliauja

    public int getGreitis() {
        return greitis;
    }
    public void setGreitis(int greitis) {
        this.greitis = greitis;
    }
    public int getPuse() {
        return puse;
    }
    public void setPuse(int puse) {
        this.puse = puse;
    }

    public void run ()
    {
        while (true) // Kol kulkos skrenda
        {
            switch (getPuse()) //kulka - puse (pakeisti koordinates)
            {
                case 0:
                    setY(getY()- getGreitis());
                    break;
                case 1:
                    setX(getX()- getGreitis());
                    break;
                case 2:
                    setY(getY()+ getGreitis());
                    break;
                case 3:
                    setX(getX()+ getGreitis());
                    break;
            }
            if(getX()<0||getY()<0||getX()>1200||getY()>1000) // jei uz lenteles rybu
            {
                gyvas =false;
                break;
            }
            try
            {
                Thread.sleep (200); // sustabdyt programa 200 mili sekundziu kad matytusi kaip keliauja kulkos ir juda tankai
            }
            catch (Exception e) {}
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private  int x;
    private  int y;
    Kulka(int x, int y, int puse) // konstruktorius su zinoma saudymo puse
    {
        this.x=x;
        this.y=y;
        this.puse = puse;
    }
}