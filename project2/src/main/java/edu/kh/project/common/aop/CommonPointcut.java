package edu.kh.project.common.aop;

import org.aspectj.lang.annotation.Pointcut;

// JoinPoint : 공통 코드가 삽입 될 수 있는 후보
// Pointcut : 실제로 코드가 삽입될 부분

// pointcut을 모아둘 클래스임!!
public class CommonPointcut {
	
	//edu.kh.project.board.model.service.BoardServiceImpl.메서드(param)
	//edu.kh.project.board.model.service.CommentServiceImpl.메서드(param,param)
	//edu.kh.project.board.model.service.MemberServiceImpl.메서드()
	//edu.kh.project.board.model.service.MyPageServiceImpl.메서드(param,param,param)
	
	// execution([접근제한자] 반환형 패키지명.클래스명.메서드명([파라미터]))
	// * 	: 모든
	// . 	: 바로 밑에 하위 경로
	// .. 	: (의미1) 모든 후손, (의미2) 0개 이상
	
	//모든 ServiceImpl을 나타내는 포인트컷 경로 써보자!!
	//@Pointcut("execution(* edu.kh.project..*ServiceImpl.메서드명(매개변수))")
	@Pointcut("execution(* edu.kh.project..*ServiceImpl.*(..))")
	public void serviceImplPointcut() {
	}
	

}
