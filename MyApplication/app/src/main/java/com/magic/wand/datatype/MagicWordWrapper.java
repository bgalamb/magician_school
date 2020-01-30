package com.magic.wand.datatype;

public class MagicWordWrapper {

    String magicWordKey;
    String magicWordTitle;
    String magicWordDescription;
    String magicPower;

    public MagicWordWrapper(String magicWordKey, String magicWordTitle, String magicWordDescription, String magicPower){
        this.magicWordKey = magicWordKey;
        this.magicWordTitle = magicWordTitle;
        this.magicWordDescription = magicWordDescription;
        this.magicPower = magicPower;
    }

    public String getMagicWordTitle() {
        return magicWordTitle;
    }

    public String getMagicWordPower() {
        return magicPower;
    }

    public String getMagicWordDescription() {
        return magicWordDescription;
    }

    public String getMagicWordKey() {
        return magicWordKey;
    }
}
