package com.cucot.quizz;

import java.util.Comparator;
import java.util.List;

public class SortingMultipeCriteria {
    public static void main(String[] args) {
        List<Animal> listOfAnimal = List.of(
                new Animal("Spider", 8, 0),
                new Animal("Fly", 6, 2),
                new Animal("Dragonfly", 6, 4),
                new Animal("Worm", 0, 0)
        );
        listOfAnimal.stream().sorted(
//                Comparator.comparing(a -> a.getNumOfLegs())) // type inference works on this
//                Comparator.comparing(a -> a.getNumOfLegs()).thenComparing(a -> a.getNumOfWings())) // but not on this, with comparator chaining, which give compiler error
                // we have to tell the type used in comparator, either way
//                Comparator.comparing((Animal a) -> a.getNumOfLegs()).thenComparing((Animal a) -> a.getNumOfWings()) // this works
//                Comparator.comparing(Animal::getNumOfLegs).thenComparing(Animal::getNumOfWings) // this works since we already specified the type
                Comparator.<Animal, Integer>comparing(a -> a.getNumOfLegs()).thenComparing(a -> a.getNumOfWings()) // this is another workaround
        )
                .forEach(a -> System.out.println(a.getName()));
    }
}

class Animal {
    private String name;
    private int numOfLegs;
    private int numOfWings;

    Animal(String name, int numOfLegs, int numOfWings) {
        this.name = name;
        this.numOfLegs = numOfLegs;
        this.numOfWings = numOfWings;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getNumOfLegs() {
        return numOfLegs;
    }

    public void setNumOfLegs(int numOfLegs) {
        this.numOfLegs = numOfLegs;
    }

    int getNumOfWings() {
        return numOfWings;
    }

    public void setNumOfWings(int numOfWings) {
        this.numOfWings = numOfWings;
    }

    @Override
    public String toString() {
        return name;
    }
}
