
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 문자열 관련 메서드를 제공하는 JSTL (EL 형식)  --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">
    <link rel="stylesheet" href="/resources/css/myPage-style.css">

    <!--  fontawesome 사이트 아이콘 이용   -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>

</head>
<body>
    <!-- <main> -->
        <%-- header.jsp.include --%>
        <jsp:include page = "/WEB-INF/views/common/header.jsp" />

        <section class = "myPage-content">
            <%-- 사이드메뉴 jsp 따로 빼고 주소 첨부함....(여러번 쓸 거니까...) --%>
            <jsp:include page = "/WEB-INF/views/member/sideMenu.jsp" />
            

            <section class = "myPage-main">

                <h1 class = "myPage-title">내 정보</h1>
                <span class = "myPage-subject">원하는 회원 정보를 수정할 수 있습니다.</span>
                
                <%-- 절대경로 : /member/myPage/info --%>
                <%-- 상대경로 : info
                                현재 페이지를 기준으로 적는다.............................. ㅎ ?????? --%>

                <form action = "/member/myPage/info" method = "POST" name = "myPage-frm">

                    <div class = "myPage-row">
                        <label >닉네임</label>
                        <input type="text" name = "memberNickname" value = "${loginMember.memberNickname}"
                        maxlength="11">

                    </div>

                    <div class = "myPage-row">
                        <label >전화번호</label>
                        <input type="text" name = "memberTel" value = "${loginMember.memberTel}"
                        maxlength="11">

                    </div>

                    <div class = "myPage-row info-title">
                        <span>주소</span>
                    </div>
                    <%-- 04540,,남대문로 120,,2층 --%>
                    <%-- split(문자열,구분자) :  문자열을 구분자로 쪼개서 배열로 반환하는 친구...--%>
                    <%-- 배열 이름 : addr임... --%>
                    <c:set var = "addr" value = "${fn:split(loginMember.memberAddress,',,')}"/>

                    <div class = "myPage-row info-address">
                        <input type="text" name = "memberAddress" value ="${addr[0]}" placeholder="우편번호">
                        <button type = "button">검색</button>
                    </div>

                    <div class = "myPage-row info-address">
                        <input type="text" name = "memberAddress" value ="${addr[1]}" placeholder="도로명/지번주소">
                       
                    </div>

                    <div class = "myPage-row info-address">
                        <input type="text" name = "memberAddress" value ="2층" placeholder="상세주소">
                       
                    </div>

                    <button class = "myPage-submit">수정하기</button>

                </form>

            </section>

        </section>
    </main>
    <%-- footer.jsp.include --%>
    <jsp:include page = "/WEB-INF/views/common/footer.jsp" />
    <script src="/resources/js/member/myPage.js"></script>
    <!-- 다음 주소 api 추가 -->
</body>
</html>
