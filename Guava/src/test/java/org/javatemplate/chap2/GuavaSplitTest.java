package org.javatemplate.chap2;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class GuavaSplitTest {


    private GuavaSplit guavasplit = null;
    private List<String> cityList;
    @BeforeMethod
    public void beforeTest() {
        cityList = new ArrayList<>();
        guavasplit = new GuavaSplit();
        cityList.add("Washington");
        cityList.add("New York City");
        cityList.add("Philadelphia");
        cityList.add("Dallas");
    }

    @Test
    public void test_string_splitter(){
        String cities = "Washington|New York City|Philadelphia|Dallas";
        List<String> expected  = guavasplit.splitStringOnSeparator(cities);
        assertEquals(cityList,expected);
    }

    @Test
    public void testSplitter(){
        String keyValueString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> expectedMap = Maps.newLinkedHashMap();
        expectedMap.put("Washington D.C", "Redskins");
        expectedMap.put("New York City", "Giants");
        expectedMap.put("Philadelphia", "Eagles");
        expectedMap.put("Dallas", "Cowboys");
        Map<String,String> splitMap = com.google.common.base.Splitter.on("#").
                withKeyValueSeparator("=").split(keyValueString);
        assertEquals(splitMap ,expectedMap);
    }


}