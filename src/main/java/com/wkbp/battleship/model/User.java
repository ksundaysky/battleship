package com.wkbp.battleship.model;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 */
@Entity
@Table(name = "user_table")
public
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nick")
    private String nick;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;



}
