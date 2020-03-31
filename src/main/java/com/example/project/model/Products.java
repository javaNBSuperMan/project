package com.example.project.model;

import org.apache.ibatis.type.Alias;

@Alias("products")
public class Products {

    private String id;
    private String title;
    private String content;
    private java.sql.Timestamp createDate;
    private String createAuthor;
    private java.sql.Timestamp updateDate;
    private String updateAuthor;
    private Integer readNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public java.sql.Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.sql.Timestamp createDate) {
        this.createDate = createDate;
    }


    public String getCreateAuthor() {
        return createAuthor;
    }

    public void setCreateAuthor(String createAuthor) {
        this.createAuthor = createAuthor;
    }


    public java.sql.Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(java.sql.Timestamp updateDate) {
        this.updateDate = updateDate;
    }


    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }


    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", createAuthor='" + createAuthor + '\'' +
                ", updateDate=" + updateDate +
                ", updateAuthor='" + updateAuthor + '\'' +
                ", readNum=" + readNum +
                '}';
    }
}
