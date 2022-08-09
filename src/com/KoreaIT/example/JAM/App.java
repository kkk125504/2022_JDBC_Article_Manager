package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	public void run() {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();

			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("예외 : 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			try {
				conn = DriverManager.getConnection(url, "root", "");
				int actionResult = doAction(conn, sc, cmd);

				if (actionResult == -1) {
					System.out.println("프로그램 종료");
					break;
				}
			} catch (SQLException e) {
				System.out.println("@@@@에러@@@@" + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		sc.close();
	}

	private int doAction(Connection conn, Scanner sc, String cmd) {

		if (cmd.equals("article write")) {
			System.out.println("== 게시물 작성 ==");

			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();

			PreparedStatement pstmt = null;
			
			try {
				String sql = "INSERT INTO article";
				sql += " SET regDate = NOW()";
				sql += ",updateDate = NOW()";
				sql += ",title = '" + title + "'";
				sql += ",`body` = '" + body + "';";

				System.out.println(sql);
				
				pstmt = conn.prepareStatement(sql);				
				pstmt.executeUpdate();
			
			} catch (SQLException e) {
				System.out.println("@@@@에러@@@@: " + e);
			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} else if (cmd.equals("article list")) {
			System.out.println("== 게시물 리스트 ==");

			PreparedStatement pstmt = null;
			ResultSet result = null;
			List<Article> articles = new ArrayList<>();

			try {
				String sql = "SELECT * FROM article";
				sql += " ORDER BY id DESC;";

				System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				result = pstmt.executeQuery();

				while (result.next()) {
					int id = result.getInt("id");
					String regDate = result.getString("regDate");
					String updateDate = result.getString("updateDate");
					String title = result.getString("title");
					String body = result.getString("body");

					Article article = new Article(id, regDate, updateDate, title, body);
					articles.add(article);
				}
			} catch (SQLException e) {
				System.out.println("@@@@에러@@@@ : " + e);
			} finally {
				try {
					if (result != null && !result.isClosed()) {
						result.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (articles.size() == 0) {
				System.out.println("게시물이 없습니다.");
				return 0;
			}
			System.out.println("번호   /   제목");
			for (Article article : articles) {
				System.out.printf("%d   /    %s\n", article.id, article.title);
			}
		} else if (cmd.startsWith("article modify ")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);
			System.out.printf("== %d번 게시물 수정 ==\n", id);
			
			System.out.printf("새 제목 : ");
			String title = sc.nextLine();
			System.out.printf("새 내용 : ");
			String body = sc.nextLine();

			PreparedStatement pstmt = null;
			try {
				String sql = "UPDATE article";
				sql += " SET title = " + "'" + title + "'";
				sql += ", `body` = " + "'" + body + "'";
				sql += " WHERE id = " + id + ";";

				System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("에러: " + e);
			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.	close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.printf("== %d번 게시물 수정 되었습니다. ==\n", id);
		}
		if (cmd.equals("exit")) {
			System.out.println("== 프로그램 종료 ==");
			return -1;
		}
		//위에내용 실행시키고 exit(return -1)가아니면 0으로 다시 while문 반복
		return 0;
	}
}
