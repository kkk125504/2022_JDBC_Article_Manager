package com.KoreaIT.example.JAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.ArticleService;

public class ArticleController extends Controller {

	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void doWrite(String cmd) {
		if(Container.session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.println("== 게시물 작성 ==");
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		int memberId = Container.session.loginedMemberId;
		int id = articleService.doWrite(memberId, title, body);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

	}

	public void doDelete(String cmd) {		
		if(Container.session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요.");
			return;
		}	
		
		int id = Integer.parseInt(cmd.split(" ")[2]);

		boolean isArticleExists = articleService.isArticleExists(id);

		if (isArticleExists == false) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		Article article = articleService.getArticleById(id);
		if(article.memberId != Container.session.loginedMemberId) {
			System.out.println("해당 게시물에 대한 권한이 없습니다.");
			return;
		}
		
		System.out.printf("== %d번 게시물 삭제 ==\n", id);

		articleService.doDelete(id);

		System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);

	}

	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.printf("== %d번 게시물 상세보기 ==\n", id);

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);

	}

	public void doModify(String cmd) {
		if(Container.session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("== %d번 게시물 수정 ==\n", id);
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();

		articleService.doUpdate(id, title, body);

		System.out.printf("%d번 게시물이 수정 되었습니다\n", id);
	}

	public void showList(String cmd) {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.getArticles();

		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("번호  /  제목");

		for (Article article : articles) {
			System.out.printf("%d  /  %s\n", article.id, article.title);
		}

	}

}