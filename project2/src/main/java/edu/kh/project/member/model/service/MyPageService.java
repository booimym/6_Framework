package edu.kh.project.member.model.service;

import java.util.Map;

import edu.kh.project.member.model.vo.Member;

//설계 + 유지보수 + AOP
public interface MyPageService {

	 /** 회원 정보 수정 서비스
	 * @param inputMember
	 * @return
	 */
	/* public abstract */ int updateInfo(Member inputMember);

	int changePw(Map<String, Object> paramMap);

	int memberDelete(int memberNo, String memberPw);
	 
}
