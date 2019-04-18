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



}
