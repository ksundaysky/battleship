package com.wkbp.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BattleshipApplication {

	public static void main(String[] args) {
		String string = null;
		System.out.println(10/0);
		SpringApplication.run(BattleshipApplication.class, args);
	}

}
