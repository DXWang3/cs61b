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

    public static void main(String[] args) {

        Vehicle v1 = new Vehicle(3,4,20,10);
        System.out.println("Range of v1: " + v1.range());


        Vehicle v2 = new Car(20,20);
        System.out.println(v2.range());

        Vehicle v3 = new Truck();
        

    }
    
}

class Car extends Vehicle {
    public Car(int fuel, int mpg) {
        super(4, 4, fuel, mpg); 
        this.trunkSize = 5;
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


// public class Truck extends Car {
//     public Truck() {

//     }
// }






