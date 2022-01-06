package com.example.shopondooradmin;

import java.io.Serializable;

public class OrderModel implements Serializable {
    String productName;
    String productImage;
    int productPriceint;
    String productPrice;
    String totalQuantity;
    int totalPrice;
    String documentId;
    String docId;
    String currentDate;
    String currentTime;
    double totaldiscountPrice;
    String orderStatus;

    public OrderModel() {
    }

    public OrderModel(String productName, String productImage, int productPriceint, String productPrice, String totalQuantity, int totalPrice, String documentId, String docId, String currentDate, String currentTime, double totaldiscountPrice, String orderStatus) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPriceint = productPriceint;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.documentId = documentId;
        this.docId = docId;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totaldiscountPrice = totaldiscountPrice;
        this.orderStatus = orderStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductPriceint() {
        return productPriceint;
    }

    public void setProductPriceint(int productPriceint) {
        this.productPriceint = productPriceint;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public double getTotaldiscountPrice() {
        return totaldiscountPrice;
    }

    public void setTotaldiscountPrice(double totaldiscountPrice) {
        this.totaldiscountPrice = totaldiscountPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

