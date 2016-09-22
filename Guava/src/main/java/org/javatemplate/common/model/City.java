package org.javatemplate.common.model;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

import java.util.Comparator;

/**
 * Created by arunmohzi on 9/21/16.
 */
public class City {
    private int population;
    private double rainfall;
    private String name;

    public int getPopulation() {
        return population;
    }

    public double getAverageRainfall() {
        return rainfall;
    }

    //builder class
    public static class Builder {
        private int population;
        private double rainfall;
        private String name;

        public Builder population(int population) {
            this.population = population;
            return this;
        }

        public Builder averageRainfall(double rainfall) {
            this.rainfall = rainfall;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public City build(){
            return new City(this);
        }
    }

    private City(Builder cityBuilder){
        this.name = cityBuilder.name;
        this.population = cityBuilder.population;
        this.rainfall = cityBuilder.rainfall;
    }
}
