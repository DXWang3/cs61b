import java.util.ArrayList;

public abstract class Vehicle {
    int seats;
    int wheels;
    int fuel;
    int mpg;
    int trunkSize;
    ArrayList<Object> trunk;

    public Vehicle(int seats, int wheels, int fuel, int mpg) { 
        this.seats = seats;
        this.wheels = wheels;
        this.fuel = fuel;
        this.mpg = mpg;
        this.trunk = new ArrayList<Object>();
        this.trunkSize = 0;
    }

    public void putInTrunk(Object item) {
        System.out.println("There is no room in the Trunk");
    }

    float range() {
        return fuel * mpg;
    }
}

class Car extends Vehicle {
    public Car(int fuel, int mpg) {
        super(4, 4, fuel, mpg); 
        this.trunkSize = 2;
    }      

    public void putInTrunk(Object item) {
        if (this.trunk.size() < this.trunkSize) {
            trunk.add(item);
        } else {
            super.putInTrunk(item);
        }
    } 
}


class Motorcycle extends Vehicle {
    public Motorcycle(int fuel, int mpg) {
        super(1, 2, fuel, mpg);
    }   
}



/* Fill this class in assuming that the trunkSize of a Truck is 5*/
public class Truck extends Car {
     public Truck() {

     }
}


What will happen after each of these snippets of code are compiled/run?
        //Q1
    Vehicle v1 = new Vehicle(3,4,20,10);
    System.out.println("Range of v1: " + v1.range());

    //Q2
    Vehicle v2 = new Car(20,20);
    System.out.println("Range of v2: " + v2.range());

    //Q3
    Vehicle v3 = new Motorcycle(10,40);
    System.out.println("Range of v3: " + v3.range());
    
    //Q4
    System.out.println("Number of seats of v2 " + v2.seats);
    System.out.println("Number of seats of v3 " + v3.seats);

    //Q5
    System.out.println("Number of wheels of v2" + v2.wheels);
    System.out.println("Number of wheels of v3" + v3.wheels);

    //Q6
    v2.putInTrunk("Backpack");
    v2.putInTrunk("Laptop");
    v2.putInTrunk("Shoes");

    //Q7
    v3.putInTrunk("Backpack");
    v3.putInTrunk("Laptop");
    v3.putInTrunk("Shoes");
