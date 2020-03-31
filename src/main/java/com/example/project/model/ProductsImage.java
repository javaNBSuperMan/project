package com.example.project.model;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("productsImage")
public class ProductsImage {

    private String id;
    private String productsId;
    private java.sql.Timestamp createDate;
    private String createAuthor;
    private Integer imgNum;
    private String imgName;
    private String imgSize;
    private String imgSrc;

    public ProductsImage(String id, String productsId, Timestamp createDate, String createAuthor, Integer imgNum, String imgName, String imgSize, String imgSrc) {
        this.id = id;
        this.productsId = productsId;
        this.createDate = createDate;
        this.createAuthor = createAuthor;
        this.imgNum = imgNum;
        this.imgName = imgName;
        this.imgSize = imgSize;
        this.imgSrc = imgSrc;
    }

    public ProductsImage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }


    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }


    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }


    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreateAuthor() {
        return createAuthor;
    }

    public void setCreateAuthor(String createAuthor) {
        this.createAuthor = createAuthor;
    }
}
