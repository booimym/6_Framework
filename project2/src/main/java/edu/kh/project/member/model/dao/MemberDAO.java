package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.vo.Member;

@Repository // 퍼시스턴스(persistence) 레이어, 영속성을 가지는 속성(파일, DB)를 가진 클래스 + bean 등록!
public class MemberDAO {

	
		
		@Autowired
		private SqlSessionTemplate sqlSession;
		
		public Member login(String memberEmail) {
			// sqlSession.selectOne("매펴이름.태그id" , SQL 작성 시 필요한 값);
			return sqlSession.selectOne("memberMapper.login",memberEmail);
			
		}

		/** 회원가입 dao
		 * @param inputMember
		 * @return result
		 */
		public int signUp(Member inputMember) {
			
//			return sqlSession.insert("memberMapper.signUp", inputMember);
			return 0;
		}
		
		
		
		
	}


