package org.javatemplate.collections;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.javatemplate.common.model.City;
import org.javatemplate.common.sort.CityByPopulation;
import org.javatemplate.common.sort.CityByRainfall;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by arunmohzi on 9/21/16.
 */
public class GuavaOrdering {
    // Null First --> Ordering.from(comparator).nullsFirst();

    CityByPopulation cityByPopulation = new CityByPopulation();
    CityByRainfall cityByRainfall = new CityByRainfall();
    public void sort(List<City> cities){
        Ordering<City> secondaryOrdering = Ordering.from(cityByPopulation).compound(cityByRainfall);
        Collections.sort(cities, secondaryOrdering);
    }
}



