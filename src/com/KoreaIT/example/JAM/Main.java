package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
	
		while (true) {
			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					String sql = "INSERT INTO article";
					sql += " SET regDate = NOW()";
					sql += ",updateDate = NOW()";
					sql += ",title = '"+ title +"'";
					sql += ",`body` = '"+ body +"';";

					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					int affectedRows = pstmt.executeUpdate();

					System.out.println("affectedRows : " + affectedRows);
					

				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
				} finally {
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}try {
						if (pstmt != null && !pstmt.isClosed()) {
							pstmt.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
							
			
			} else if (cmd.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");				
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				List<Article> articles = new ArrayList<>();
				
				try {					
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

					conn = DriverManager.getConnection(url, "root", "");
					System.out.println("연결 성공!");

					String sql = "SELECT * FROM article";
					sql += " ORDER BY id DESC;";
					
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					result = pstmt.executeQuery();
					
					while(result.next()) {
						int id = result.getInt("id");
						String regDate = result.getString("regDate");
						String updateDate = result.getString("updateDate");
						String title = result.getString("title");
						String body = result.getString("body");
			
						Article article = new Article(id,regDate,updateDate,title,body);
						
						articles.add(article);
						}										
				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
					System.out.println("에러: " + e);
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
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.println("번호   /   제목");
				for (Article article : articles) {
					System.out.printf("%d   /    %s\n", article.id, article.title);
				}
			}
		}
		sc.close();
	}
}
