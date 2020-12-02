package sample.components;

import javafx.scene.control.ProgressBar;

public class HiloCorredor extends Thread {

    private ProgressBar pgbCarril;

    public HiloCorredor(ProgressBar pgbCarril) {
        this.pgbCarril = pgbCarril;
    }

    @Override
    public void run() {
        super.run();
        double avance =0;
        while (avance<1) {
            avance += Math.random()/10;
            this.pgbCarril.setProgress(avance);
            try {
                sleep((long)(Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // System.out.println("Vuelta"+i  + "-->" + getName());
        }

    }
}
