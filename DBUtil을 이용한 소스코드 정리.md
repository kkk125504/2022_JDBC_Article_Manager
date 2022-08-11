DBUtil을 이용한 소스코드 정리

1. article write시 

   SecSql의 객체 .append()를 이용해 쿼리문을 작성하고 

   DBUtil.insert메소드를 통해 DB에 추가

```java
		SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append(" SET regDate = NOW()");
			sql.append(", updateDate = NOW()");
			sql.append(", title = ?", title);
			sql.append(", `body` = ?", body);		
			
			DBUtil.insert(conn, sql);
```

2.  article modify시 

   write와 마찬가지로 sql객체로 쿼리문을 담고

   DBUtil.update메소드를 통해 수정

```java
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(conn, sql);
```

3. article list시

​	 쿼리문을 담는 방식은 동일하고 

​	 DBUtil.selectRows(conn, sql)메소드는 반환값이 List<Map<String, Object>>이다.

​	 Map 리스트이므로 반복문을 통하여 Map객체를 하나씩 꺼내 articles에 추가하였다.

​	 이때  Article객체 생성시 articleMap(Map형태)를 매개변수로 받을수 있게 생성자도 추가

```java
	List<Article> articles = new ArrayList<>();
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append(" FROM article");
		sql.append(" ORDER BY id DESC");

		List<Map<String, Object>> articlesListMap
		= DBUtil.selectRows(conn, sql);
		
		for(Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
```

​		Article생성자 추가 코드

```java
public Article(Map<String, Object> articleMap) {
		this.id = (int)articleMap.get("id");
		this.title = (String)articleMap.get("title");
		this.body = (String)articleMap.get("body");
	}
```

