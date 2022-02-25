package com.myappinternship.models;

public class SliderItem {

    String strImag;
    int imgData;
    public SliderItem(String strImag, int imgData) {

        this.strImag = strImag;
        this.imgData = imgData;
    }

    public String getStrImag() {
        return strImag;
    }

    public void setStrImag(String strImag) {
        this.strImag = strImag;
    }

    public int getImgData() {
        return imgData;
    }

    public void setImgData(int imgData) {
        this.imgData = imgData;
    }
}
