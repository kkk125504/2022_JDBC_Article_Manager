package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.service.MemberService;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController extends Controller {
	MemberService memberService;

	public MemberController(Connection conn, Scanner sc) {
		super(sc);
		memberService = new MemberService(conn);
	}

	public void doJoin() {
		System.out.println("== 회원 가입 ==");
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup) {
				System.out.printf("%s는(은) 이미 사용 중인 아이디가 있습니다.\n", loginId);
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("로그인 패스워드 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("패스워드를 입력해주세요.");
				continue;
			}
			boolean loginPwCheck = true;
			while (true) {
				System.out.printf("로그인 패스워드 확인 : ");
				loginPwConfirm = sc.nextLine().trim();

				if (loginPwConfirm.length() == 0) {
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
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		int id = memberService.doJoin(loginId, loginPw, name);
		System.out.printf("%s님 가입 되었습니다. \n", name);
	}

}
