package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController {
	protected Connection conn;
	protected Scanner sc;

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public void doJoin() {
		System.out.println("== 회원 가입 ==");
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginId.trim().length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) > 0");
			sql.append(" FROM `member`");
			sql.append(" WHERE loginId = ?", loginId);
			boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);

			if (isLoginIdDup) {
				System.out.printf("%s는(은) 이미 사용 중인 아이디가 있습니다.\n", loginId);
				continue;
			}

			break;
		}
		while (true) {
			System.out.printf("로그인 패스워드 : ");
			loginPw = sc.nextLine();

			if (loginPw.trim().length() == 0) {
				System.out.println("패스워드를 입력해주세요.");
				continue;
			}
			boolean loginPwCheck = true;
			while (true) {
				System.out.printf("로그인 패스워드 확인 : ");
				loginPwConfirm = sc.nextLine();

				if (loginPwConfirm.trim().length() == 0) {
					System.out.println("패스워드 확인을 입력해주세요.");
					continue;
				}
				if (loginPw.equals(loginPwConfirm) == false) {
					System.out.println("패스워드가 일치 하지 않습니다. 다시 입력해주세요.");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}
		System.out.printf("이름 : ");
		name = sc.nextLine();

		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);

		int id = DBUtil.insert(conn, sql);

		System.out.printf("%s님 가입 되었습니다. \n", name);

	}

}
