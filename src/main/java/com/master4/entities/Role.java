package com.master4.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
//@Getter
//@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class Role {
    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "listRole")
    private List<User> userList;

    @Transient
    private Boolean used;

    public Role(short parseShort) {
        this.id=parseShort;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
