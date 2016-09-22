package org.javatemplate.collections;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by arunmohzi on 9/20/16.
 */
public class GuavaList {
    public List<List<String>> partitionList(List<String> userList,int number_of_split){
        //List<Person> personList =Lists.newArrayList();
        return Lists.partition(userList,number_of_split);
    }
}
