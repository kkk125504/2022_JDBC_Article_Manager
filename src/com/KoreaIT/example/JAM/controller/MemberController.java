package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController extends Controller {
	
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doJoin(String cmd) {
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;

		System.out.println("== 회원가입 ==");
		// id 입력
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup) {
				System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}
		// Pw,PwConfirm 입력
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			boolean loginPwCheck = true;

			while (true) {
				System.out.printf("비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine().trim();

				if (loginPwConfirm.length() == 0) {
					System.out.println("비밀번호 확인을 입력해주세요");
					continue;
				}

				if (loginPw.equals(loginPwConfirm) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
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

		System.out.printf("%s 님, 가입 되었습니다.\n", name);

	}

	public void login(String cmd) {
		if(Container.session.loginedMemberId != -1) {
			System.out.println("로그아웃 후 이용 가능합니다.");
			return;
		}
		
		
		String loginId = null;
		String loginPw = null;

		System.out.println("== 로그인 ==");
		// id 입력
		int loginIdTryMaxCount = 3;
		int loginIdTrytryCount = 0;
		while (true) {
			if (loginIdTrytryCount >= loginIdTryMaxCount) {
				System.out.println("비밀번호를 확인하고 다시 시도해주세요.");
				break;
			}
			
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				loginIdTrytryCount++;
				System.out.println("아이디를 입력해주세요");				
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup == false) {
				loginIdTrytryCount++;
				System.out.printf("%s는(은) 존재하지 않는 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		int loginPwTryMaxCount = 3;
		int loginPwTryCount = 0;

		while (true) {
			if (loginPwTryCount >= loginPwTryMaxCount) {
				System.out.println("비밀번호를 확인하고 다시 시도해주세요.");
				break;
			}

			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				loginPwTryCount++;
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				loginPwTryCount++;
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			Container.session.loginedMember = member;
			Container.session.loginedMemberId = member.id;
			
			System.out.printf("%s님 환영합니다.\n", member.name);
			break;
		}

	}

	public void showProfile(String cmd) {
		if(Container.session.loginedMemberId==-1) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}else {	 
			System.out.printf("번호  :  %d\n", Container.session.loginedMember.id);
			System.out.printf("아이디  :  %s\n", Container.session.loginedMember.loginId);
			System.out.printf("이름  :  %s\n", Container.session.loginedMember.name);
		}
					
	}

}