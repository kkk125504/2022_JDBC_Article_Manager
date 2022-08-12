회원가입 구현(아이디 중복체크 X)

```java
		while (true) {
				System.out.printf("로그인 아이디 : ");
				loginId = sc.nextLine();
			if (loginId.trim().length() == 0) {
				System.out.println("아이디를 입력해주세요.");
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
					System.out.println("패스워드가 일치 하지 않습니다. 
					다시 입력해주세요.");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}
```

- ```if (loginId.trim().length() == 0)``` , 아이디가 공백이면 다시 입력받도록 구현 비밀번호,

  비밀번호 확인도 마찬가지로 공백이면 continue 하여 각각 비밀번호와, 비밀번호 확인만 다시 입력받도록 구현

- 공백만 아니라면 일단 패스워드,패스워드 확인을 받고 ```boolean loginPwCheck``` 의 값이 비밀번호와 비밀번호확인이 일치하지 않을때 ```false```를 대입한다.``` loginPwChec```k가 ```true``` 일때만 while문 종료하게 구현, ```loginPwCheck```가 ```false```이면 다시 비밀번호부터 다시 입력받도록 구현
  		