package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.example.JAM.exception.SQLErrorException;

public class Main {
	public static void main(String[] args) {
		try {
			new App().run();
		} catch (SQLErrorException e) {
			System.err.println(e.getMessage());
			e.getOrigin().printStackTrace();
		}
	}
}
