package com.KoreaIT.example.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		
		List<Article> articles = new ArrayList<>();
		int lastId = 0;
		while (true) {
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.equals("article write")) {
				int id = lastId+1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				lastId++;
				articles.add(article);
				System.out.printf("%d번 게시물이 생성 되었습니다.\n", id);
				
				//System.out.println(article);
			} else if (cmd.equals("article write")) {

			}
		}

		System.out.println("==프로그램 종료==");
		sc.close();
	}
}
