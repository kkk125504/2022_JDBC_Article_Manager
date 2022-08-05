package com.KoreaIT.example.JAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("명령어 ) ");
			String cmd = sc.nextLine().trim();
			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.equals("article write")) {

			} else if (cmd.equals("article write")) {

			}
		}

		System.out.println("==프로그램 종료==");
		sc.close();
	}
}
