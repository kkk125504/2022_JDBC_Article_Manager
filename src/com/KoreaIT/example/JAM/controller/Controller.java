package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
	protected Scanner sc;

	protected Controller(Scanner sc) {
		this.sc = sc;
	}
}
