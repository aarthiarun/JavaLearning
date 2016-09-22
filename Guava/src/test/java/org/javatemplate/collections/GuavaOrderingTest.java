package org.javatemplate.collections;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.javatemplate.common.model.City;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class GuavaOrderingTest {
  GuavaOrdering guavaOrdering;
    City.Builder cityBuilder;
    @BeforeMethod
    public void beforeTest() {
        guavaOrdering = new GuavaOrdering();
        cityBuilder = new City.Builder();
    }

    @Test
    public void testSecondarySort(){
        City city1 = cityBuilder.population(100000).averageRainfall(55.0).build();
        City city2 = cityBuilder.population(100000).averageRainfall(45.0).build();
        City city3 = cityBuilder.population(100000).averageRainfall(33.8).build();
        List<City> cities = Lists.newArrayList(city1,city2,city3);
        guavaOrdering.sort(cities);
        assertEquals(cities.get(0),city3);
    }

}