package com.revature.polymorphism;

public class Playground {

    public static void main(String[] args) {


        Parent p = new Parent();
        p.jobtitle = "Trainer";
        p.work();

        //not available method to parent
        //p.play();

        Child c = new Child();

        c.favoritegames = "Minecraft";
        c.jobtitle = "Student";
        c.work();
        c.play();

        //will have all of the states(fields) and behvaiors(methods) available in the Parent Class.
        //will have the implementations provided by the Child class;
        Parent pc = new Child();
        pc.work();
        pc.jobtitle = "something";

        //no play() since only fields and methods of Parent class available
        //pc.play();







    }

}
