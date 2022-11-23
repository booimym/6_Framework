package edu.kh.project.common;

import java.text.SimpleDateFormat;

// 유용한 기능을 모아둔 클래스
public class Util {
	
	   // 파일명 변경 메소드
	   public static String fileRename(String originFileName) {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	      String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

	      int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

	      String str = "_" + String.format("%05d", ranNum);

	      String ext = originFileName.substring(originFileName.lastIndexOf("."));

	      return date + str + ext;
	   }
	   
	// XSS 방지 처리 : HTML에서 해석되는 문자를 단순 글자로 변경시키겠다.
	public static String XSSHandling(String content) {
		
		if(content != null ) {
			content = content.replaceAll("&", "&amp;");
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll("\"", "&quot;");
		}
		
		return content;
	}
	
	// 개행문자 처리 : \r\n, \n, \r, \n\r -> <br>로 변경할 거야. 왜냐면 <br>이 되어야지 줄바뀜으로 변하니까!!!
	public static String newLineHandling(String content) {
		
		return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}
	
	// 개행 문자 처리 해제
	public static String newLineClear(String content) {
		
		return content.replaceAll("<br>","\n");
	}

}
