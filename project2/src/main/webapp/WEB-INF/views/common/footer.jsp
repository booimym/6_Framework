<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

 <footer>
        <p>
            Copyright &copy; KH Information Educational Institute A-Class
        </p>
        <article>
            <a href="#">프로젝트 소개</a>
            <span> | </span>
            <a href="#">이용약관</a>
            <span> | </span>
            <a href="#">개인정보처리방침</a>
            <span> | </span>
            <a href="#">고객센터</a>
        </article>
    </footer>

    <%-- session scope에 message 속성이 존재하는 경우
        alert창을 이용해서 내용을 출력 --%>

    <%-- 메세지가 비어 있는 경우 = 로그인 성공한 경우임. --%>
    <c:if test = "${!empty sessionScope.message}"> <%--메시지가 비어있지 않다면,--%>
        <script>
            alert("${sessionScope.message}");
        </script>

        <%-- message 1회 출력 후 session scope에서 삭제하기!!(삭제 안하면, 한번 로그인 실패하면 계속 로그인 실패한 게 됨....남아있으니까) --%>
        <c:remove var = "message" scope = "session" />
    </c:if>

    <%-- footer에다가 이걸 집어넣는 이유! --%>
    <%-- 언제든 떠야 되니까 header or footer임. 근데 header에 넣으면 읽다가 멈춤(header부터 읽기 때문...) --%>