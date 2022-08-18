package com.KoreaIT.example.JAM.session;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;

public class Session {
	public int loginedMemberId;
	public Member loginedMember;

	public Session() {
		loginedMemberId = -1;
	}

	public void logout() {
		Container.session.loginedMemberId = -1;
		Container.session.loginedMember = null;			
	}

	public void login(Member member) {
		Container.session.loginedMember = member;
		Container.session.loginedMemberId = member.id;		
	}
	public boolean isLogined() {
		return loginedMemberId != -1;
	}
	
}
