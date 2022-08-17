package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.service.MemberService;

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

	public void login() {
		System.out.println("== 로그인 ==");
		
		int loginIdTryCount = 0;
		int loginIdTryMaxCount = 3;
		String loginId = null;
		String loginPw = null;
		while (true) {
			if (loginIdTryCount >= loginIdTryMaxCount) {
				System.out.println("아이디를 확인하고 다시 시도해주세요.");
				return;
			}
			System.out.printf("아이디 입력 :  ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				loginIdTryCount++;
				System.out.println("아이디를 입력해 주세요.");
				continue;
			}
			boolean isLoginDup = memberService.isLoginIdDup(loginId);
			if (isLoginDup == false) {
				loginIdTryCount++;
				System.out.printf("%s는(은) 존재하지 않는 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		Member member = memberService.getMemberByLoginId(loginId);
		
		int loginPwTryCount = 0;
		int loginPwTryMaxCount = 3;

		while (true) {
			if (loginPwTryCount >= loginPwTryMaxCount) {
				System.out.println("비밀번호를 확인하고 다시 시도해주세요.");
				return;
			}
			System.out.printf("비밀번호 입력 :  ");
			loginPw = sc.nextLine();

			if (loginPw.length() == 0) {
				loginPwTryCount++;
				System.out.println("비밀번호를 입력해 주세요.");
				continue;
			}
			if (member.loginPw.equals(loginPw) == false) {
				loginPwTryCount++;
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		System.out.printf("%s님 로그인 성공\n", member.name);
	}

}
