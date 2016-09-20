package org.javatemplate.chap2;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arunmohzi on 9/19/16.
 */
public class Joiner {

    public String buildStringWithSkippingNull(List<String> stringList){
        return com.google.common.base.Joiner.on("|").skipNulls().join(stringList);
    }

    public String buildStringWithNullValueReplacement(List<String> stringList){
        return com.google.common.base.Joiner.on("|").useForNull("no value").join(stringList);
    }

}
