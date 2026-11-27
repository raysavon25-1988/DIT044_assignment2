import javax.swing.*;//is a Java package containing classes for graphical interfaces.
//Allows us to use Swing classes such as Timer without writing their full package names.
import java.awt.event.ActionEvent;
//Let's understand the word event.
//An event means something happened
//ActionEvent is an object representing an action event.
import java.awt.event.ActionListener;
//This imports the ActionListener interface.
//You already know what an interface is.
//An interface describes methods that implementing classes must provide.
//Here, ActionListener requires a method called:
// actionPerformed(ActionEvent e)---line 43
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */
//THIS FILE HANDLES ACTIONS
// CarController receives information about button clicks and coordinates the actions.

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    //new TimerListener()
    //This creates a TimerListener object.
    //Its job is to react when the timer fires.
    //Notice that TimerListener is not a Java class imported at the top.
    //It is a small class defined inside CarController.java, farther down in the file.
    //So overall this means:
    // Create a Timer object that triggers an action about every 50 milliseconds, and give it a listener to handle those events.

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */







    //START TASK 3-4 CHANGE PROPOSAL: Collision Checker -----------------------------------------------------
    // Proposal: We have the class Timelistener and its checker for coillsion method - isOutsidePanel.
    // We need separate and create a separate class that check the boundary.

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            for (Vehicle car : cars) {

                double previousX = car.getX();
                double previousY = car.getY();

                car.move();

                if (isOutsidePanel(car)) {
                    car.stopEngine();

                    car.setPosition(previousX, previousY);

                    car.turnLeft();
                    car.turnLeft();

                    car.startEngine();
                }

                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                frame.drawPanel.moveit(car, x, y);
            }

            frame.drawPanel.repaint();
        }
    }
    //CHANGE: here we are saying that if it collides then stop and then turn left twice and then start

    private boolean isOutsidePanel(Vehicle car) {

        int panelWidth = frame.drawPanel.getWidth();
        int panelHeight = frame.drawPanel.getHeight();

        int carWidth = frame.drawPanel.volvoImage.getWidth();
        int carHeight = frame.drawPanel.volvoImage.getHeight();
        //CHANGE:
        if (car instanceof Saab95) {
            carWidth = frame.drawPanel.saabImage.getWidth();
            carHeight = frame.drawPanel.saabImage.getHeight();
        }
        else {
            carWidth = frame.drawPanel.volvoImage.getWidth();
            carHeight = frame.drawPanel.volvoImage.getHeight();
        }//CHANGE:

        int maxX = panelWidth - carWidth;
        int maxY = panelHeight - carHeight;

        return car.getX() < 0
                || car.getX() > maxX
                || car.getY() < 0
                || car.getY() > maxY;
    }//CHANGE: we are checking if boundaries were in contact with the car object.return true.

//END OF CHANGE------------------------------------------------------------------------------------------




//START TASK 3-4 CHANGE PROPOSAL: CarController - Simulator--------------------------------------------------
    // Proposal: Snce all this methods are related to the movement of the car this part should be stay here.

    void gas(int amount) {

        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas); //CHANGE: WE changed Car into VEHICLE bcz that was the name of our parent class.
        }
    }
    void brake(int amount) {

        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
            //CHANGE: WE ARE CONNECTING BRAKE BUTTON HERE
        }

    }
    void startAllCars() {
        for (Vehicle car : cars) {
            car.startEngine(); //CHANGE: Calling startand stop functions from vehicle when the buttons are clicked
        }}

    void stopAllCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }
    void turnLeft() {
        for (Vehicle car : cars) {
            car.turnLeft();
        }
    }

    void turnRight() {
        for (Vehicle car : cars) {
            car.turnRight(); //change: the same with turnright and turnleft
        }
    }
    void turboOn() {
    for (Vehicle car : cars) {
        if (car instanceof Saab95) {
            Saab95 saab = (Saab95) car;
            saab.setTurboOn();
        }
    }
}

    void turboOff() {
    for (Vehicle car : cars) {
        if (car instanceof Saab95) {
            Saab95 saab = (Saab95) car;
            saab.setTurboOff();
        }
    }
}}//change: connecting saab turbo buttons


