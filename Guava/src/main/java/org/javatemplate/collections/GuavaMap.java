package org.javatemplate.collections;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by arunmohzi on 9/21/16.
 */
public class GuavaMap {
   public Map<String,Book> create_isbn_to_book_map(List<Book> bookList){
       //uniqueIndex
       Map<String,Book> bookMap = Maps.uniqueIndex(bookList.iterator(), new Function<Book, String>() {
           @Override
           public String apply(Book input) {
               return input.getIsbn();
           }
       });
     return  bookMap;
   }
    class Book {
        private int id;
        private String isbn;
        private String name;

        public Book(int id, String name, String isbn) {
            this.id = id;
            this.name = name;
            this.isbn = isbn;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
