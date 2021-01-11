package com.example.phptest;



public class Product {
    public int id;
    public String producttitle;
    public int productid;
    public String productdescription;
    public double productrating;
    public String productcategory;
    public double productprice;
    public String productimage;


    public Product(int productid, String producttitle, String productdescription, double productrating, String productcategory, double productprice, String productimage) {
        this.productid = productid;
        this.producttitle = producttitle;
        this.productdescription = productdescription;
        this.productrating = productrating;
        this.productcategory = productcategory;
        this.productprice = productprice;
        this.productimage = productimage;
    }

    public int getProductid() {
        return productid;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public double getProductrating() {
        return productrating;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public double getProductprice() {
        return productprice;
    }

    public String getProductimage() {
        return productimage;
    }

}
