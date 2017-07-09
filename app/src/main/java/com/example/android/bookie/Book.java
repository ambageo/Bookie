package com.example.android.bookie;

/**
 * Created by georgeampartzidis on 8/7/17.
 */

public class Book {
    private String bTitle;
    private String bAuthor;
    private String bPublisher;
    private String bCategory;

    /**
     *
     * @param title     refers to the book title.
     * @param author    refers to the author of the book.
     * @param publisher refers to the book publisher.
     * @param category  refers to the subject category of the book.
     */
    public Book(String title, String author, String publisher, String category){
        bTitle= title;
        bAuthor= author;
        bPublisher= publisher;
        bCategory= category;
    }

    public String getTitle(){
        return bTitle;
    }

    public String getAuthor(){
        return bAuthor;
    }

    public String getPublisher(){
        return bPublisher;
    }

    public String getCategory(){
        return bCategory;
    }
}
