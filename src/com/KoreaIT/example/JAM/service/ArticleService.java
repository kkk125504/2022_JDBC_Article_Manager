package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	ArticleDao articleDao;

	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title,body);

	}

	public int articleCount(int id) {		
		return articleDao.articleCount(id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);	
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id,title,body);		
	}

	public List<Map<String, Object>> articlesListMap() {
		return articleDao.articlesListMap();
	}

	public Map<String, Object> articleMap(int id) {
		return articleDao.articleMap(id);
	}

}
