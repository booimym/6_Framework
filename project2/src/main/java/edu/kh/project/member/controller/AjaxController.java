package edu.kh.project.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.kh.project.member.model.service.AjaxService;
import edu.kh.project.member.model.vo.Member;

@Controller // 컨트롤러의 역할 : 요청이 오면 알맞은 서비스로 보내주고 거기서 결과 반환하면 알맞은 view로 응답 제어 + bean 등록하는 역할
public class AjaxController {

	@Autowired
	private AjaxService service;

	// 이메일 중복 검사
	@GetMapping("/emailDupCheck")
	@ResponseBody // 반환된 값을 jsp 경로가 아닌 값 자체로 인식한다.
	public int emailDupCheck(String memberEmail) {
		// data : {"memberEmail" : memberEmail.value}

		// System.out.println(memberEmail);

		// 이메일 중복 검사 서비스 호출
		int result = service.emailDupCheck(memberEmail);

		// /WEB-INF/views/

		// @ResponseBody 어노테이션 덕분에
		// result가 View Resolver로 전달되지 않고
		// 호출했던 ajax 함수로 반환됨
		return result;
	}

	// 닉네임 중복 검사
	@GetMapping("/nicknameDupCheck")
	@ResponseBody // 반환된 값을 jsp 경로가 아닌 값 자체로 인식한다.//우리가 jsp호출하고 이런게 아니고... 단지 값을 반환하는 것 뿐임.... 그니까
					// 값 자체로 인식하게 해줘야 해....
	public int nicknameDupCheck(String memberNickname) {

		int result = service.nicknameDupCheck(memberNickname);

		return result;
	}
	
	  // 이메일로 회원 정보 조회 (JSON과 GSON 활용) //만약에 @responseBody 안 쓰면 경로로 가니까 404 뜸...
	  
	  @PostMapping("/selectEmail")
	  @ResponseBody 
	  public String selectEmail(String email) {
		  Member member = service.selectEmail(email); //System.out.println(member);
		  
		  //String result =
		  //"{\"memberEmail\" : \"user01@kh.or.kr\", \"memberNickname\" : \"유저일\"}"; //
		  //JSON 형식으로 Member 객체 생성 //{"memberEmail" : member.getMemberEmail(),
		 //"memberNickname":member.getMemberNickname()}
		  //{"\memberEmail\" : "user01@kh.or.kr\", "\memberNickname\" : "\유저일\"}
		  //return result;
		 
		  //(GSON라이브러리를 이용해서) GSON객체를 만들어서, Member객체 => JSON변환(String) return new
		 return new Gson().toJson(member);
	  
	  }
	 

//	// 이메일로 회원 정보 조회 (jackson-databind 활용)
//	@PostMapping("/selectEmail")
//	@ResponseBody
//	public Member selectEmail(String email) {
//
//		// jackson이란?
//		// JS 객체 <-> Java 객체 <-> JSON
//		Member member = service.selectEmail(email);
//
//		return member;
//		// java 객체 반환시 jackson 라이브러리가		
//		// js객체로 변환
//
//	}

	  //회원 목록 조회
	  @GetMapping("/selectMemberList")
	  @ResponseBody
	  public String selectMemberList() {
		  
		List<Member> memberList = service.selectMemberList();
		
		// 객체 1개를 표현 == JSON
		// 객체 여러 개가 담긴 배열 == JSONArray
		// "[{}[}{}]"
		
		  
		return new Gson().toJson(memberList);
	  }
}
