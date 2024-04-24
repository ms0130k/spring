package com.playground.spring.etc.book;

import java.util.Iterator;

public class BookShelf implements Iterable<Book> {
    private Book[] books;
    private int last;

    public BookShelf(int size) {
        books = new Book[size];
        last = 0;
    }

    public void addBook(Book book) {
        if (last < books.length) {
            books[last++] = book;
        }
    }

    public Book getBook(int index) {
        if (index >= 0 && index < books.length) {
            return books[index];
        }
        return null;
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }

    public int getLength() {
        return last;
    }
}
