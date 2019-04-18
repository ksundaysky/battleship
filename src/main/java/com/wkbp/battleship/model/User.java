package com.wkbp.battleship.model;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 */
@Entity
@Table(name = "User_Table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nick")
    private String nick;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;


    public User(Long id, String nick, String email, String password) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
}
