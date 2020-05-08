package com.master4.entities;


import com.master4.validators.Password;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


//@NoArgsConstructor
@AllArgsConstructor
//@Setter @Getter
@Entity
@Table(name="users")
//@Password
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    @Size(min = 5, message = "5 caracteres au min")
    private String name;

    @Email(message = "email invalid")
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "6 catacters au min")
    @Column(name="password", nullable = false)
    private String password;

    @Transient
    private String confirmedPassword;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name="modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Article> articles;

    @Size(min=1,message = "selectionner au moins un role")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles", joinColumns={@JoinColumn(referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    private List<Role> listRole;

    public User(long id) {
        this.id=id;
    }
    public   User (String email, String password){
        this.email=email;
        this.password=password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }
}
