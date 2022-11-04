
let emailCheck = false;


// js 객체를 이용한 유효성 검사 결과 저장 객체
// [참고] js 객체 : {"K":V, "K":V, ....} (Map 형식)


// 변수명.key 또는 변수명["key"]를 이용하면 객체 내부의 속성에 접근할 수 있다.
const checkObj = {

    "memberEmail" :     false,
    "memberPw" :        false,
    "memberPwConfirm" : false,
    "memberNickname" :  false,
    "memberTel" :       false

};

// 회원 가입 양식이 제출되었을 때
document.getElementById("signUp-frm").addEventListener("submit", function(event){

    // // 이메일이 유효한가? 
    // if(!emailCheck){ //유효하지 않은 경우

    //     alert("이메일이 유효하지 않습니다.");
    //     event.preventDefault();

    // }

    // checkObj의 속성 중 하나라도 false가 있다면, 제출 이벤트를 제거할 것임!!!!

    // for in 구문 : 객체의 key값을 순서대로 접근하는 반복문
    // [작성법]
    // for( let 변수명 in 객체명 )
            // == key
    // -> 객체에서 순서대로 key를 하나씩 꺼내서 왼쪽 변수에 저장한다.

    for(let key in checkObj){

        let str;

        // checkObj 속성 하나를 꺼내서 값을 검사했는데 false인 경우
        if( !checkObj[key]){

            switch(key){ // 그 속성이 뭔지 검사해보자!
                
                case "memberEmail" :     str = "이메일이 유효하지 않습니다."; break;
                case "memberPw" :        str = "비밀번호가 유효하지 않습니다."; break;
                case "memberPwConfirm" : str = "비밀번호확인이 유효하지 않습니당"; break;
                case "memberNickname" :  str = "닉네임이 유효하지 않습니다."; break;
                case "memberTel" :       str = "전화번호가 유효하지 않습니다."; break;

            }

            alert(str); //대화상자 출력

            // 유효하지 않은 입력으로 포커스 이동
            document.getElementById(key).focus();
            event.preventDefault(); // 제출 이벤트 제거
            return; // 함수 종료
        }
    }


})



// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail"); // input 태그
const emailMessage = document.getElementById("emailMessage"); // span 태그

memberEmail.addEventListener("input",function(){

    // 문자가 입력되지 않은 경우
    if(memberEmail.value.trim().length == 0){

        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        memberEmail.value = "";


        // confrim, error 클래스 제거 -> 검정 글씨로 만들기
        emailMessage.classList.remove("confirm","error");

        //유효성 검사 확인 객체에 현재 상태를 저장
        checkObj.memberEmail = false;
        //emailCheck = false;

        return;
    }

    // 정규표현식을 이용한 유효성 검사
    const regEx = /^[A-Za-z\d\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if(regEx.test(memberEmail.value)){ //유효한 경우

        emailMessage.innerText = "유효한 이메일 형식입니다.";
        emailMessage.classList.remove("error");
        emailMessage.classList.add("confirm");

        //유효성 검사 확인 객체에 현재 상태를 저장
        checkObj.memberEmail = true;
        //emailCheck = true;

    } else { //유효하지 않은 경우

        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.remove("confirm");
        emailMessage.classList.add("error");

        //유효성 검사 확인 객체에 현재 상태를 저장
        checkObj.memberEmail = false;
        //emailCheck = false;
    }

})

// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");


//input 이벤트 : input 태그에 입력이 되었을 경우(붙여넣기,마우스,키보드 다 인식한다.)
//keyup잘 안쓰고....대신 많이 쓰는듯...
//비밀번호 입력 시
memberPw.addEventListener("input",function(){

    //(1) 비밀번호가 입력되지 않은 경우
    if(memberPw.value.trim().length == 0){
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요";
        memberPw.value = "";
        pwMessage.classList.remove("confirm");
        pwMessage.classList.remove("error");
        //둘다 삭제하면 검정 글씨가 됩니당 ㅋㅋ

        checkObj.memberPw = false;
        return;
    }

    // 비밀번호 정규표현식으로 검사해보자!!
    const regEx = /^[a-z][a-zA-Z\d!@#-_]{5,19}$/;

    if(regEx.test(memberPw.value)){ // 유효한 비밀번호

        checkObj.memberPwConfirm = true;

        //유효한 비밀번호 + 확인은 작성되지 않은 경우
        if(memberPwConfirm.value.trim().length == 0){

            pwMessage.innerText = "유효한 비밀번호 형식입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

        } else {  //유효한 비밀번호 + 확인도 작성된 경우  -> 이때는 이제 비번&비번확인이 같은지를 비교해야지!

            // 비밀번호가 입력될 때
            // 비밀번호 확인에 작성된 값과 일치하는 경우
            if(memberPw.value == memberPwConfirm.value){
                pwMessage.innerText = "비밀번호가 일치합니다.";
                pwMessage.classList.add("confirm");
                pwMessage.classList.remove("error");

                checkObj.memberPwConfirm = true;
            }else{
                pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
                pwMessage.classList.add("error");
                pwMessage.classList.remove("confirm");

                checkObj.memberPwConfirm = false;
            }

        }

    } else { // 유효하지 않음

        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.memberPwConfirm = false;
    }
    
})

// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener("input",function(){

        // 비밀번호가 유효할 경우에만, 
        // 비밀번호 == 확인 같은지를 비교해보겠다!

        if(checkObj.memberPw = true){ //비밀번호가 유효할 경우

                //비밀번호, 비밀번호 확인 같은지 검사
            if(memberPw.value == memberPwConfirm.value){

        
            pwMessage.innerText = "비밀번호가 일치합니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

            checkObj.memberPwConfirm = true;

            } else {
            pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");

            checkObj.memberPwConfirm = false;
            }

        } else { // 비밀번호가 유효하지 않은 경우
            checkObj.memberPwConfirm = false;
        }
    


    
})

// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNickname.addEventListener("input",function(){

    //닉네임에 문자가 입력되지 않은 경우
    if(memberNickname.value.trim().length == 0){

        nickMessage.innerText = "한글, 영어, 숫자로만 2~10글자";
        nickMessage.classList.remove("confirm","error");
        checkObj.memgerNickname = false;
        return;

    }

    //닉네임 정규표현식 검사
    // \w ==[A-Za-z0-9]
    const regEx = /^[가-힣\w]{2,10}$/;

    if(regEx.test(memberNickname.value)){ //유효한 경우

        // ** 닉네임 중복검사 코드를 추가할 예정 **

        nickMessage.innerText = "유효한 닉네임 형식입니다.";
        nickMessage.classList.add("confirm");
        nickMessage.classList.remove("error");
        checkObj.memberNickname = true;



    }else {

        nickMessage.innerText = "유효한 닉네임 형식이 아닙니다.";
        nickMessage.classList.add("error");
        nickMessage.classList.remove("confirm");
        checkObj.memberNickname = false;

    }


})

const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input", function(){

    // (1) 전화번호가 입력되지 않았을 경우
    if(memberTel.value.trim().length == 0){

        telMessage.innerText = "전화번호를 입력해주세요(-제외)";
        telMessage.classList.remove("confirm","error");
        checkObj.memberTel = false;
        
        return;

    }

    // 정규식으로 유효성 검사
    const regEx = /^0(1[01679]|2|[3-6][1-5]|70)[1-9]\d{2,3}\d{4}$/;

    if(regEx.test(memberTel.value)){ // 유효한 경우
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true;
    
    } else {
        telMessage.innerText = " 전화번호 형식이 유효하지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        checkObj.memberTel = false;
    }



})


