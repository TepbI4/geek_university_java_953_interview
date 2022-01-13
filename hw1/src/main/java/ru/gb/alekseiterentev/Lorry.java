package ru.gb.alekseiterentev;

public class Lorry extends LightWeightCar implements Stopable {

    public void stop(){
        System.out.println("Car is stop");
    }
}

