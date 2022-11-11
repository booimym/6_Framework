package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.vo.Member;

// 서비스 인터페이스 왜 만들었을까용 ㅋ
// 설계, 유지보수성 향상, AOP 때문에
public interface AjaxService {
	
	 /** 이메일 중복 검사 서비스
	 * @param memberEmail
	 * @return
	 */
	int emailDupCheck(String memberEmail); 
	
	
	/** 닉네임 중복 검사 서비스
	 * @param memberNickname
	 * @return
	 */
	int nicknameDupCheck(String memberNickname);


	Member selectEmail(String email);


	List<Member> selectMemberList();

}
