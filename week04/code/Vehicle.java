public abstract class Vehicle {
    int seats;
    int wheels;
    int fuel;
    float mpg;
    int coolFactor;
    
    float range() {
        return fuel * mpg;
    }
    
    
    public Vehicle(int seats, int wheels, int fuel, float mpg, int coolFactor) { 
        this.seats = seats;
        this.wheels = wheels;
        this.fuel = fuel;
        this.mpg = mpg;
        this.coolFactor = coolFactor;
    }
    public void putInTrunk(Object item) {
        System.out.println("There is no room in the Trunk");
    }
}

public abstract class FourWheels extends Vehicle {
    ArrayList<Object> trunk;
    int trunkSize;
    public FourWheels(int seats, int fuel, float mpg, int coolFactor) { 
        super(seats, 4, fuel, mpg, coolFactor, int trunkSize) {
        this.trunkSize = trunkSize;
        trunk = new ArrayList<Object>();
    } 
    public void putInTrunk(Object item) {
        if (trunk.size() < trunkSize) {
        
            trunk.add(item)
        } else {
            super().putInTrunk(item);
        }
    } 
}

public abstract class TwoWheels extends Vehicle {
    public TwoWheels(int seats, int fuel, float mpg, int coolFactor) {
        super(seats, 2, fuel, mpg, coolFactor)
    }  
}

public class Car extends FourWheels {
    public Car(int fuel, float mpg, int coolFactor) {
        super(4, fuel, mpg, coolFactor); 
    }      
}

public class Truck extends TwoWheels {
    public Truck(int fuel, float mpg, int coolFactor) {
        super(2, fuel, mpg, coolFactor);
    }
}

