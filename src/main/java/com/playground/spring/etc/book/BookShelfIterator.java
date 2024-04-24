package com.playground.spring.etc.book;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BookShelfIterator implements Iterator<Book> {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Book next() {
        if (index >= bookShelf.getLength()) {
            throw new NoSuchElementException();
        }
        return bookShelf.getBook(index++);
    }

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(3);
        bookShelf.addBook(new Book("Book1"));
        bookShelf.addBook(new Book("Book2"));
        bookShelf.addBook(new Book("Book3"));

        Iterator<Book> iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }

        for (Book book : bookShelf) {
            System.out.println(book.getTitle());
        }
    }
}
