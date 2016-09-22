package org.javatemplate.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class GuavaMapTest {

    List<GuavaMap.Book> bookList;
    Map<String,GuavaMap.Book> expectedMap;
    GuavaMap guavaMap;

    @BeforeMethod
    public void beforeTest() {
        bookList = new ArrayList<>();
        expectedMap = new HashMap<>();
        guavaMap = new GuavaMap();
        for (int i = 0; i <= 3; i++){
            GuavaMap.Book book = guavaMap.new Book(i, "name"+i , "isbn-"+i);
            expectedMap.put("isbn-" + i, book);
            bookList.add(book);
        }
    }
    @Test
    public void test_create_isbn_to_book_map() throws Exception {
        Map<String,GuavaMap.Book> actualMap = guavaMap.create_isbn_to_book_map(bookList);
        assertEquals(actualMap,expectedMap);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void test_on_list_duplicate_values() throws Exception {
        //java.lang.IllegalArgumentException: Multiple entries with same key:
        GuavaMap.Book book = guavaMap.new Book(0, "name"+0 , "isbn-"+0);
        bookList.add(book);
        Map<String,GuavaMap.Book> actualMap = guavaMap.create_isbn_to_book_map(bookList);
        assertEquals(actualMap,expectedMap);
    }

    @Test
    public void test_arraylistmultimap(){
        ArrayListMultimap<String,String> multiMap = ArrayListMultimap.create();
        multiMap.put("Bar","1");
        multiMap.put("Bar","2");
        multiMap.put("Bar","3");
        multiMap.put("Bar","3");
        multiMap.put("Bar","3");
        List<String> expected = Lists.newArrayList("1", "2", "3", "3", "3");
        assertEquals(multiMap.get("Bar"),expected);
       // assertEquals(multiMap.size(),1); // this will fail, because multiMap size is depends on all the values
    }

    @Test
    public void test_hashmultimap(){
        HashMultimap<String,String> multiMap = HashMultimap.create();
        multiMap.put("Bar","1");
        multiMap.put("Bar","1");
        multiMap.put("Bar","2");
        multiMap.put("Bar","2");
        multiMap.put("Bar","3");
        multiMap.put("Bar","3");
        multiMap.put("Bar","3");
        List<String> expected = Lists.newArrayList("1", "2", "3");
        assertEquals(multiMap.get("Bar"),expected);
        assertEquals(multiMap.size(),3); // this will fail, because multiMap size is depends on all the values
    }
}