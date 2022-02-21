package com.myappinternship.models;

public class BookModel {

    String strBook;
    int imgBook;
    public BookModel(String strBook, int imgBook) {

      this.strBook = strBook;
      this.imgBook = imgBook;
    }

    public String getStrBook() {
        return strBook;
    }

    public void setStrBook(String strBook) {
        this.strBook = strBook;
    }

    public int getImgBook() {
        return imgBook;
    }

    public void setImgBook(int imgBook) {
        this.imgBook = imgBook;
    }
}
