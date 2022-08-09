package com.KoreaIT.example.JAM.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.example.JAM.Article;

public class JDBCSelectTEST {
	public static void main(String[] args) {
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
			for(Article article : articles) {
				System.out.printf("번호 / %d\n", article.id);
				System.out.printf("제목 / %s\n", article.title);
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
	}
}
