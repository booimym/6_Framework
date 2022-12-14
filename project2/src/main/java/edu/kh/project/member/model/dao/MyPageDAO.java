package edu.kh.project.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.vo.Member;


@Repository //스프링이 bean 등록 + 관리(ioc)
public class MyPageDAO {

	@Autowired //스프링으로부터 bean을 주입받음(di)
	private SqlSessionTemplate sqlSession;

	/** 회원 정보 수정 DAO
	 * @param inputMember
	 * @return
	 */
	public int updateInfo(Member inputMember) {
		
		
		return sqlSession.update("myPageMapper.updateInfo",inputMember);
		
	}

	/** 암호화된 비밀번호 조회 dao
	 * @param memberNo
	 * @return encPw
	 */
	public String selectEncPw(int memberNo) {
		
		
		return sqlSession.selectOne("myPageMapper.selectEncPw", memberNo);
	
	}

	/** 비밀번호 변경하는 dao코드
	 * @param paramMap
	 * @return result
	 */
	public int changePw(Map<String, Object> paramMap) {
		
		
		return sqlSession.update("myPageMapper.changePw", paramMap);
	}

	/**
	 * @param memberNo
	 * @param memberPw
	 * @return
	 */
	public int memberDelete(int memberNo) {
		
		return sqlSession.update("myPageMapper.memberDelete",memberNo);
	}

	/** 프로필 이미지를 수정하겠다!!
	 * @param loginMember
	 * @return
	 */
	public int updateProfile(Member loginMember) {
		
		
		//마이바티스를 이용해서 update를 진행할겨
		return sqlSession.update("myPageMapper.updateProfile", loginMember);
		
	}
}
