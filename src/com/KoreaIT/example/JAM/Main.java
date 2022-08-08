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
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				int id = lastId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				lastId++;
				articles.add(article);
				System.out.printf("%d번 게시물이 생성 되었습니다.\n", id);

				// System.out.println(article);
			} else if (cmd.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
				if(articles.size()==0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호   /   제목");
				for(Article article : articles) {
					System.out.printf("%d   /    %s\n", article.id, article.title);
				}
			}
		}
		sc.close();
	}
}
