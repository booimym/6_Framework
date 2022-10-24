<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

 <header>
            <section>
                <!-- 클릭 시 메인페이지(최상위 주소인 '/')로 이동하는 로고 -->
                <a href = "/">
                                <%-- 절대경로로 변경하자 --%>
                    <img src = "/resources/images/logo.jpg" id ="home-logo">
                </a>
            </section>
            <section>
                <article class = "search-area">

                    <!-- form : 내부 input 태그의 값을 서버 또는 페이지로 전달(제출) -->
                    <form action = "#">
                        <fieldset>
                            <input type = "search" id="query" name="query"
                            placeholder="검색어를 입력해주세요" >
                            <button type ="submit" id="search-btn" class="fa-solid fa-magnifying-glass">
                                <!-- 돋보기는 글자(특수문자)로 취급되기 때문에, font-size 등 다 적용됨(꾸미기 가능!) -->
                            </button>
                        </fieldset>
                    </form>
                </article>
            </section>

            <%-- 헤더 오른쪽 상단 메뉴 --%>
            <div id = "header-top-menu">
                <c:choose>
                    <c:when test ="${empty sessionScope.loginMember}"> <%--로그인x인 경우--%>
                        <a href="/">메인페이지</a>
                        |
                        <a href="/member/login">로그인</a>
                    </c:when>

                    <c:otherwise>  <%--로그인 o인 경우--%>
                        <label for = "header-menu-toggle">
                            ${loginMember.memberNickname}
                            <i class = "fa-solid fa-caret-down"></i>  <%-- fontawesome? --%>
                        </label>

                        <input type = "checkbox" id = "header-menu-toggle">   <%-- 스위치 --%>

                        <div id = "header-menu">
                            <a href="/member/myPage/info">내정보</a>
                            <a href="/member/logout">로그아웃</a>
                        </div>
                    </c:otherwise>
                </c:choose>

               
            </div>
            
        </header>

        <nav>
            <ul>
                <%-- <li><a href = "#">공지사항</a></li>
                <li><a href = "#">자유게시판</a></li>
                <li><a href = "#">질문게시판</a></li>
                <li><a href = "#">FAQ</a></li>
                <li><a href = "#">1:1문의</a></li> --%>

                <c:forEach var = "boardType" items = "${boardTypeMap}">
                
                <%-- 
                    EL을 이용해서 Map 데이터를 다루는 방법
                
                    key ==> ${변수명.key}
                    value ==> ${변수명.value}
                
                --%>

                <li><a href="/board/${boardType.key}/list">${boardType.value}</a></li>
                
                </c:forEach>




            </ul>

        </nav>