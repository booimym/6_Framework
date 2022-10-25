package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.vo.Member;

//@Service:  // Service 레이어, 비즈니스 로직을 가진 클래스임을 명시 
		 	 // + bean 등록까지 하는 annotation
			//이름 x : bean 등록 시 이름을 클래스명으로 등록(memberServiceImpl)
//@Service("이름") : bean 등록 시 이름을 지정된 이름으로 등록!


@Service
public class MemberServiceImpl implements MemberService{
	
	// MemberDAO bean을 의존성 주입해라 라는.... 뜻이래......
	@Autowired
	MemberDAO dao;

	// spring-security.xml에서 등록한 bean을 여기서 의존성 주입을 한다....!!!!
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//로그인 서비스
	@Override
	public Member login(Member inputMember) {
		
		System.out.println("입력한 비밀번호 : " + inputMember.getMemberPw());
		System.out.println("암호화 비밀번호 : " + bcrypt.encode(inputMember.getMemberPw()));
		
		//bcrypt 이용시 로그인 방법
		// 1. 이메일이 일치하는 회원 정보를 DB에서 조회한다
		// 	  반드시 비밀번호(MEMBER_PW)가 포함되어야 함
		
		Member loginMember = dao.login(inputMember.getMemberEmail());
		// 2. 입력 받은 비밀번호(평문)와
		//	  조회한 암호화된 비밀번호를 비교한다. 어떻게?
		//    --> BCryptPasswordEncoder.matches(평문, 암호문) 를 이용할 것임.
		//    --> 같으면 true, 아니면 false
		
		if(loginMember != null) { //아이디 정상 입력
		
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				// 3-1. 비밀번호가 일치하면 조회된 회원 정보를 반환할 것임...
				//		단, 비밀번호는 제거
				loginMember.setMemberPw(null);
			} else {
				// 3-2.  비밀번호가 일치하지 않으면 null을 반환할거얌
				loginMember = null;
			}
		}
		
		
		// 3-1. 비밀번호가 일치하면 조회된 회원 정보를 반환할 것임...
		//    단, 비밀번호는 제거
		
		// 3-2.  비밀번호가 일치하지 않으면 null을 반환할거얌
		
		
		//DAO 코드
		
		
		return loginMember;
	}

	
	
}
