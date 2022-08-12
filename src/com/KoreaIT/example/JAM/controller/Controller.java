package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

public class Controller {
	protected Connection conn;
	protected Scanner sc;
	Controller(Scanner sc){
		this.sc = sc;
	}
}
