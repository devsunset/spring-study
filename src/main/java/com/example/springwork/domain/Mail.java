package com.example.springwork.domain;

public class Mail {
    private String address;
    private String title;
    private String message;

    public Mail(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
