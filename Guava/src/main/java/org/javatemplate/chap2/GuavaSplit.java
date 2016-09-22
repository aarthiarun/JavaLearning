package org.javatemplate.chap2;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by arunmohzi on 9/20/16.
 */
public class GuavaSplit {
    public List<String> splitStringOnSeparator(String stringToBeSplitted) {
        Splitter splitter = Splitter.on("|").trimResults();
        return splitter.splitToList(stringToBeSplitted);
    }
}
