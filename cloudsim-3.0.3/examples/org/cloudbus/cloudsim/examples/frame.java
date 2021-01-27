
package org.cloudbus.cloudsim.examples;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class Slice {
    double value;
    Color color;
    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}
class MyComponent extends JComponent {
	//org.cloudbus.cloudsim.examples.settter s=new org.cloudbus.cloudsim.examples.settter();
    static int x=250;
    static int y=50;
    Map<Integer,String> map=new HashMap<>();
    
    Slice[] slices = {
            new Slice(10, Color.blue), new Slice(10, Color.green),
    };

    public MyComponent() {
    	//org.cloudbus.cloudsim.DatacenterBroker br=new org.cloudbus.cloudsim.DatacenterBroker();
        try {
        	//0=1, 1=0, 2=0, 3=0, 4=0, 5=1
        	/*map.put(5,1);
        	map.put(4,0);
        	map.put(3,0);
        	map.put(2,0);
        	map.put(1,0);
        	map.put(0,1);*/
        	settter s=new settter();
        	System.out.print(s.getValue());
        	map.putAll(s.getValue());
       
        }catch(Exception e)
        {
        	System.out.print(e);
        }
    }
    public static int getRandomIntegerBetweenRange(int min, int max){

        int x = (int)(Math.random()*((max-min)+1))+min;

        return x;

    }
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.drawString("VM-0",10,50);
        g.setColor(Color.blue);
        g.drawString("VM-1",410,50);
        drawPie((Graphics2D) g, getBounds(), slices);
        g.setColor(Color.red);
       // int rand= getRandomIntegerBetweenRange(20, 40);
        for(int i=0;i<map.size();i++)
        {
            send(g,map.get(i),i);
        }
       /* g.drawString("abc",x-rand,(y));
        System.out.println("Double between 5.0 and 10.00: RandomDoubleNumber = "+getRandomIntegerBetweenRange(20, 40));
        g.fillArc(x-rand,y,10,10,0,360);*/
    }

    private void send(Graphics g,String i,int key) {
        int rand= getRandomIntegerBetweenRange(130, 260);
        int rand2=getRandomIntegerBetweenRange(150,300);
      // System.out.println("rand="+rand+"rand2="+rand2);
        if(i.equalsIgnoreCase("0")) {
            int sm=getRandomIntegerBetweenRange(50,100);
            String k="";
            k=k+""+key;
            g.drawString(k, x - rand+sm, (y) + (rand2));
             //System.out.println("Double between 5.0 and 10.00: RandomDoubleNumber = "+getRandomIntegerBetweenRange(20, 40));
            g.fillArc(x - rand+sm, (y) + (rand2), 10, 10, 0, 360);
        }
        else
        {
        	String ks="";
            ks=ks+""+key;
            int xx=140;int yy=50;
            int rand3=getRandomIntegerBetweenRange(50,80);
            g.drawString(ks, x+rand-rand3 , (y) + rand2);
            // System.out.println("Double between 5.0 and 10.00: RandomDoubleNumber = "+getRandomIntegerBetweenRange(20, 40));
            g.fillArc(x + rand-rand3, (y) + (rand2), 10, 10, 0, 360);
        }
    }

    void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0.0D;

        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 90.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total)-90;
            int arcAngle = (int) (slices[i].value * 360 / total);
            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }
}

