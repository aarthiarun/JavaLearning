package org.javatemplate.common.sort;

import com.google.common.primitives.Doubles;
import org.javatemplate.common.model.City;

import java.util.Comparator;

/**
 * Created by arunmohzi on 9/21/16.
 */
public class CityByRainfall implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return Doubles.compare(city1.getAverageRainfall(), city2.getAverageRainfall());
    }
}