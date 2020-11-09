import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.Vector;

public class Lentele extends Panel implements KeyListener,Runnable {
    ManoTankas manoTankas = null;
    Vector <PriesininkuTankas> priesininkuTankai = new Vector <PriesininkuTankas> (); // collections
    int tankukiekis = 12; // priesininku

    Lentele() {
        manoTankas = new ManoTankas(120, 500); // sukuriame tanka pagal koordinates
        manoTankas.setGreitis(5); // kokiu greiciu keliaus tankas
       for (int i = 0; i < tankukiekis / 2; i++) {
            PriesininkuTankas priesininkutankas = new PriesininkuTankas(i * (230 + 5), 0); //priesininku tankai sukuriami lenteles virsuje
            priesininkutankas.greitis =2;
            priesininkutankas.setPuse(2);
            Thread thread = new Thread(priesininkutankas);
            thread.start();
            priesininkuTankai.add (priesininkutankas); // pridedam i collections
        }

        for (int i = 6; i < tankukiekis; i++) {
            PriesininkuTankas priesininkutankas = new PriesininkuTankas((12 - i - 1) * (230 + 5), 930); // priesininku tankai sukuriami lenteles apacioje
            priesininkutankas.greitis =2;
            Thread thread = new Thread(priesininkutankas);
            thread.start();
            priesininkuTankai.add(priesininkutankas);
        }
    }
    public void run()
    {
        while (true)
        {
            for(int i = 0; i< priesininkuTankai.size(); i++)
            {
                PriesininkuTankas priesininkutankas = priesininkuTankai.get(i);
                for(int j = 0; j<priesininkutankas.kulkaVector.size(); j++)
                {
                    spresti1(manoTankas,priesininkutankas.kulkaVector.get(j));
                }
            }

            for(int i = 0; i< priesininkuTankai.size(); i++)
            {
                PriesininkuTankas priesininkutankas = priesininkuTankai.get(i);
                for(int j = 0; j< manoTankas.kulkaVector.size(); j++)
                {
                    Kulka kulka = manoTankas.kulkaVector.get(j);
                    spresti(priesininkutankas, kulka);
                }
            }
            try
            {
                Thread.sleep(200);
            }
            catch (Exception e)
            {

            }
            repaint(); // perpaiso lentele pagal naujus duomenis
        }
    }
    public void paint (Graphics g)
    {

        g.setColor (Color.black); // lenteles spalva
        g.fillRect (0, 0, 1200, 1000); // lentele
        if (manoTankas.gyvas) {
            piestiTankus(manoTankas.getX (), manoTankas.getY (), manoTankas.getPuse(), g, 0); // piesia mano tanka
        }
        for (int i = 0; i < manoTankas.kulkaVector.size (); i ++) // piesia kaip keliauja mano kulkos
        {
            Kulka kulka = manoTankas.kulkaVector.get(i);
            if(kulka.gyvas && manoTankas.gyvas)
            {
                g.setColor(Color.white);
                g.fill3DRect(kulka.getX(), kulka.getY(),3,3,false);
            }
            else
            {
                manoTankas.kulkaVector.remove(kulka);
            }
        }

        for (int i = 0; i < priesininkuTankai.size(); i++) {
            PriesininkuTankas priesininkutankas = priesininkuTankai.get(i);
            if(priesininkutankas.gyvas)
                piestiTankus(priesininkutankas.getX (), priesininkutankas.getY (), priesininkutankas.getPuse(), g, 1); // piesia priesininku tankus
        }
        for (int i = 0; i < priesininkuTankai.size (); i ++) // piesia kaip keliauja priesininku kulkos
        {
            PriesininkuTankas priesininkutankas = priesininkuTankai.get(i);
            for(int j = 0; j<priesininkutankas.kulkaVector.size(); j++)
            {
                Kulka kulka = priesininkutankas.kulkaVector.get(j);
                if(kulka.gyvas &&priesininkutankas.gyvas)
                {
                    g.setColor(Color.white);
                    g.fill3DRect(kulka.getX(), kulka.getY(),3,3,false);
                }
                else
                {
                    priesininkutankas.kulkaVector.remove(kulka);
                }
            }
        }
    }
    public void piestiTankus(int x, int y, int dir, Graphics g, int type)
    {
        switch (type) {
            case 0: // musu komandos tankas
                g.setColor(Color.red);
                break;
            case 1: // priesininku komandos tankas
                g.setColor(Color.green);
                break;
        }

        switch (dir) {
            case 0: // virsun
                g.fill3DRect (x, y, 5, 30, false); // koja
                g.fill3DRect (x + 15, y, 5, 30, false); // antra koja
                g.fill3DRect (x + 5, y + 5, 10, 20, false); // vidurys
                g.fillOval (x + 5, y + 10, 10, 10); // apskritimas
                g.drawLine (x + 10, y + 15, x + 10, y - 3); // vamzdis
                break;
            case 1: // kairen
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x - 3, y + 10);
                break;
            case 2: // apacion
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 33);
                break;
            case 3: // desinen
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 33, y + 10);
                break;
        }
    }

    public void keyReleased(KeyEvent e){};
    public void keyTyped(KeyEvent e){};
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_W)
        {
            manoTankas.virsun();
            manoTankas.setPuse(0);
        }
        else if(e.getKeyCode()==KeyEvent.VK_S)
        {
            manoTankas.zemyn();
            manoTankas.setPuse(2);
        }
        else if(e.getKeyCode()==KeyEvent.VK_A)
        {
            manoTankas.kairen();
            manoTankas.setPuse(1);
        }
        else if(e.getKeyCode()==KeyEvent.VK_D)
        {
            manoTankas.desinen();
            manoTankas.setPuse(3);
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            manoTankas.sauti();
        }
        this.repaint();
    }
    public void spresti(PriesininkuTankas priesininkutankas, Kulka kulka) // nuspresti ar musu kulka pasieke priesininko tanko koordinates
    {
        switch (priesininkutankas.getPuse())
        {
            case 0:
            case 2:
                if(kulka.getX()>priesininkutankas.getX()&& kulka.getX()<priesininkutankas.getX()+20&& kulka.getY()>priesininkutankas.getY()&& kulka.getY()<priesininkutankas.getY()+30)
                {
                    kulka.gyvas =false;
                    priesininkutankas.gyvas =false;
                }
                break;
            case 1:
            case 3:
                if(kulka.getX()>priesininkutankas.getX()&& kulka.getX()<priesininkutankas.getX()+30&& kulka.getY()>priesininkutankas.getY()&& kulka.getY()<priesininkutankas.getY()+20)
                {
                    kulka.gyvas =false;
                    priesininkutankas.gyvas =false;
                }
        }
    }

    public void spresti1 (ManoTankas manotankas, Kulka kulka) // nuspresti ar priesininku kulkos pasieke mano tanko koordinates
    {
        switch (manotankas.getPuse())
        {
            case 0:
            case 2:
                if(kulka.getX()>manotankas.getX()&& kulka.getX()<manotankas.getX()+20&& kulka.getY()>manotankas.getY()&& kulka.getY()<manotankas.getY()+30)
                {
                    kulka.gyvas =false;
                    manotankas.gyvas =false;
                    JOptionPane.showMessageDialog (null, "Game over!");
                    System.exit(1);
                }
                break;
            case 1:
            case 3:
                if(kulka.getX()>manotankas.getX()&& kulka.getX()<manotankas.getX()+30&& kulka.getY()>manotankas.getY()&& kulka.getY()<manotankas.getY()+20)
                {
                    kulka.gyvas =false;
                    manotankas.gyvas =false;
                    JOptionPane.showMessageDialog (null, "Game over!");
                    System.exit(1);
                }
        }
    }
}