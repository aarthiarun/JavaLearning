package org.javatemplate.chap2;

import com.google.common.collect.Maps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class JoinerTest {

    private Joiner joiner = null;
    private List<String> cityList;
    @BeforeMethod
    public void beforeTest() {
        cityList = new ArrayList<>();
        joiner = new Joiner();
        cityList.add("Washington");
        cityList.add("New York City");
        cityList.add("Philadelphia");
        cityList.add("Dallas");
    }

    @Test
    public void test_buildstring_should_skip_null(){
        cityList.add(null);
        String expected = "Washington|New York City|Philadelphia|Dallas";
        String actual  = joiner.buildStringWithSkippingNull(cityList);
        assertEquals(actual,expected);
    }

    @Test
    public void test_buildstring_should_replace_null(){
        cityList.add(null);
        String expected = "Washington|New York City|Philadelphia|Dallas|no value";
        String actual  = joiner.buildStringWithNullValueReplacement(cityList);
        assertEquals(actual,expected);
    }

    @Test
    public void test_buildstring_on_stringbuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        com.google.common.base.Joiner joiner = com.google.common.base.Joiner.on("|").skipNulls();
        joiner.appendTo(stringBuilder,"foo","bar","baz");
        assertEquals(stringBuilder.toString(), "foo|bar|baz");
        //not only string builder, it will work on File writer too
    }

    @Test
    public void testMapJoiner() {
        //Using LinkedHashMap so that the original order is preserved
        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");
        String returnedString = com.google.common.base.Joiner.on("#").
                withKeyValueSeparator("=").join(testMap);
        assertEquals(returnedString ,expectedString);
    }
}