
import javax.swing.*;

    public class Main extends JFrame {
        Lentele lentele = new Lentele();
        Main()
        {
            this.add(lentele);
            this.addKeyListener(lentele);
            this.setSize(1200,1000);
            this.setLocation(200,10);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Thread thread = new Thread(lentele);
            thread.start();
        }
        public static  void main(String agrs[])
        {
            Main main =new Main();
        }
    }
