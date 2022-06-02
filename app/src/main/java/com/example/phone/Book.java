package com.example.phone;

public class Book {
    private String isim;
    private String soyad;
    private String numara;

    public Book() {
    }

    public Book(String isim, String soyad, String numara) {
        this.isim = isim;
        this.soyad = soyad;
        this.numara = numara;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
