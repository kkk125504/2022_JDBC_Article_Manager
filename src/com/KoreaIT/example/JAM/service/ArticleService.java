package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title,body);
	}

	public boolean isArticleExists(int id) {		
		return articleDao.isArticleExists(id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);	
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id,title,body);		
	}

	public Article getArticleById(int id) {
		
		return articleDao.getArticleById(id);
	}

	public List<Article> getArticles() {
	
		return articleDao.getArticles();
	}

}
