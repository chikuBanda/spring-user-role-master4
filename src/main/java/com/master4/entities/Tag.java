package com.master4.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
//@NoArgsConstructor
//@Setter @Getter
@Entity
@Table(name="tags")
public class Tag {

    public Tag() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String  title;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified ;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy="tagList")
    List<Article> ArticleList;


    @Transient
    private Boolean used;

    public Tag(long id) {
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<Article> getArticleList() {
        return ArticleList;
    }

    public void setArticleList(List<Article> articleList) {
        ArticleList = articleList;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
