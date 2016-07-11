package com.gentlemen.easybuy.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ljf-梁燕双栖 on 2016/6/30.
 */
public class Good implements Serializable{

    private int id;
    private int cid;
    private String name;
    private double price;
    private double offset;
    private int storage;
    private String unit;
    private String description;
    private String image;
    private Timestamp time;

    public Good() {
    }

    public Good(int id, int cid, String name, double price, double offset, int storage, String unit, String description, String image, Timestamp time) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.price = price;
        this.offset = offset;
        this.storage = storage;
        this.unit = unit;
        this.description = description;
        this.image = image;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", offset=" + offset +
                ", storage=" + storage +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
