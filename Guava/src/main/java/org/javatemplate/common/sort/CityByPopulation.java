package org.javatemplate.common.sort;

import com.google.common.primitives.Ints;
import org.javatemplate.common.model.City;

import java.util.Comparator;

/**
 * Created by arunmohzi on 9/21/16.
 */
public class CityByPopulation implements Comparator<City> {
    @Override
    public int compare(City city1, City city2) {
        return Ints.compare(city1.getPopulation(), city2.getPopulation());
    }
}
