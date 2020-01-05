package com.example.myapplication.datatype;

public class MagicWordWrapper {

    String magicWordKey;
    String magicWordTitle;
    String magicWordDescription;

    public MagicWordWrapper(String magicWordKey, String magicWordTitle, String magicWordDescription){
        this.magicWordKey = magicWordKey;
        this.magicWordTitle = magicWordTitle;
        this.magicWordDescription = magicWordDescription;
    }

    public String getMagicWordTitle() {
        return magicWordTitle;
    }

    public String getMagicWordDescription() {
        return magicWordDescription;
    }

    public String getMagicWordKey() {
        return magicWordKey;
    }
}
