package com.kennen.scanbarcode;

public class Product
{
    private String barCode;
    private String price;

    public Product(String barCode, String price)
    {
        this.barCode = barCode;
        this.price = price;
    }

    public String getBarCode()
    {
        return barCode;
    }

    public String getPrice()
    {
        return price;
    }

    public void setBarCode(String barCode)
    {
        this.barCode = barCode;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
