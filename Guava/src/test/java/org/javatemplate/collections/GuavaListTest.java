package org.javatemplate.collections;

import org.javatemplate.chap2.GuavaSplit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class GuavaListTest {

    private GuavaList guavalist = null;
    private List<String> cityList;

    @BeforeMethod
    public void beforeTest() {
        cityList = new ArrayList<>();
        guavalist = new GuavaList();
        cityList.add("Washington");
        cityList.add("New York City");
        cityList.add("Philadelphia");
        cityList.add("Dallas");
        cityList.add("California");
    }

    @Test
    public void test_string_splitter(){
        String expected = "[[Washington, New York City], [Philadelphia, Dallas], [California]]";
        int number_of_split = 2;
        List<List<String>> actual  = guavalist.partitionList(cityList,number_of_split);
        assertEquals(actual.toString(), expected);
    }

}