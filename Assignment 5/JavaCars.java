package assignment5;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

public class JavaCars extends Applet implements Runnable{

	Car[] carArray;
	Random random = new Random();
	StopWatch totalTime = new StopWatch();
	StopWatch boostTime = new StopWatch();
	double boostTiming = 0;
	boolean boostFlag = true;
    Thread t;
    boolean flag = true;
	
	public void init(){
		Car firstCar = new Car(10, 125);
		Car secondCar = new Car(10, 225);
		Car thirdCar = new Car(10, 325);
		Car fourthCar = new Car(10, 425);
		Car fifthCar = new Car(10, 525);
		carArray = new Car[]{firstCar, secondCar, thirdCar, fourthCar, fifthCar};
        t = new Thread(this);
        t.start();
        setBackground(Color.gray);
	}
	
	//creates the 5 car objects
	public void start(){

	}
	
	public void run(){
		try{           
            while(flag){
    			for (int j = 0; j < carArray.length; j++){
    				carArray[j].shiftCar(random());
    			}
    			
    			//code that handles the boost feature
    			if(boostFlag){
    				for (int k = 0; k < carArray.length; k++){
    					if (carArray[k].getX()>=925){
    						int car = 0;
    						boostTime.start();
    						try{
    							car = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of a car, 1-5, to boost!"));
    							if (car < 1 || car > 5){
    								JOptionPane.showMessageDialog(null,"You messed up your input so no car will be boosted");
    								car = -1;
    							}
    						}
    						catch (Exception e){
    							JOptionPane.showMessageDialog(null,"You messed up your input so no car will be boosted");
    							car = -1;
    						}
    						if (car != -1){
    							carArray[car-1].shiftCar(75);
    						}
    						boostFlag = false;
    						boostTime.stop();
    						boostTiming = (double)boostTime.getElapsedTime()/1000;
    						break;
    					}
    				}
    			}
                delay();
                repaint();
            }
		}
		catch(Exception e){
			
		}
	}
	
	private void delay(){
	      try {
	    	    Thread.sleep(150);
	    	  }
	      catch(Exception ex) {
	    	  }
	}
	
	public void paint(Graphics g) { 
		Graphics2D g2 = (Graphics2D)g;
		int carNumber = 0;
		double elapsedTime = 0;
		totalTime.start();
		
		//draws start and finish lines
		Rectangle startLine = new Rectangle(80,0,5,650);
		Rectangle endLine = new Rectangle(1265,0,5,650);
		g2.draw(startLine);
		g2.fill(startLine);
		g2.draw(endLine);
		g2.fill(endLine);
		
		Paint burntorange = new Color(191,87, 0);
		Paint black = new Color(0,0, 0);
		
		// draw all of the car parts on the screen
		for (int i = 0; i < carArray.length; i++){
			g2.setPaint(burntorange);
			g2.draw(carArray[i].getBody());
			g2.fill(carArray[i].getBody());
			g2.draw(carArray[i].getRoof());
			g2.fill(carArray[i].getRoof());			
			g2.draw(carArray[i].getFront());
			g2.fill(carArray[i].getFront());
			g2.draw(carArray[i].getBack());
			g2.fill(carArray[i].getBack());			
			g2.setPaint(black);
			g2.draw(carArray[i].getFrontTire()); 
			g2.draw(carArray[i].getRearTire());
			g2.fill(carArray[i].getFrontTire());
			g2.fill(carArray[i].getRearTire());
			g2.drawString((i+1)+"", carArray[i].getX() + 28, carArray[i].getY() + 5);
		}	
		
		//code that figures out when a car has won
		for (int k = 0; k < carArray.length; k++){
			if (carArray[k].getX()> 1205){
				totalTime.stop();
				elapsedTime = (double)totalTime.getElapsedTime()/1000;
				flag = false;
				carNumber = k + 1;
				int leader = carArray[k].getX();
				for (int f = k+1; f < carArray.length; f++){
					if(carArray[f].getX() > leader){
						leader = carArray[f].getX();
						carNumber = f + 1;
					}
				}
				break;
			}
		}
		
		if(!flag){		
			//print winning time
			g2.drawString(("Car " + carNumber + " is the winner with a time of " + myFormat(elapsedTime - boostTiming) + " seconds."), 555, 50);
			//String name = myFormat(elapsedTime - boostTiming);
			boostFlag = true;
			boostTime.reset();
			totalTime.reset();
			t.stop();
		}
	}
	
	//used to create the random number that shifts the cars 
	private int random(){
		int randomNumber = random.nextInt(70 - 30) + 30;
		return randomNumber;
	}
	
	//formats the time to go to 2 decimal places
	public static String myFormat(double number) {
		  DecimalFormat df = new DecimalFormat("0.00");
		  return df.format(number).replaceAll("\\.00$", "");
	}
}
