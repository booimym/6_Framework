console.log("main.jsp loaded");

//아이디 저장 체크박스가 체크 되었을 때에 대한 동작

const saveId = document.getElementById("saveId");

//radio, checkbox 값이 변할 때 발생하는 이벤트 : change
saveId.addEventListener("change", function(event){
    console.log(event);
    //change는 체크가 되거나, 해제될 때 이벤트 발생
    // -> 체크되었는지 별도의 검사가 필요하다

    //이벤트 핸들러 내부 this : 이벤트가 발생한 요소(아이디 저장 checkbox)
    console.log(this.checked);

    //체크박스.checked : 체크 o == true, 체크 x == false 반환

    //체크박스.checked = true || false
    //  true 대입 : 체크 o
    //  false 대입 : 체크 해제

    if(this.checked) { //체크된 경우

        const str = "개인 정보 보호를 위해 개인 pc에서의사용을 권장하빈다." 
                + "개인 pc가 아닌 경우 취소를 눌러주세요";


    //window.confirm("내용") : 확인 == true , 취소 == false 반환
    if( !confirm(str)){ //취소를 누른 경우

        //체크를 해제시킨다.
        this.checked = false;

    }

    }

   

});

//  로그인 유효성 검사
// (로그인 form 태그 submit 이벤트를 취소하는 방법)
function loginValidate(){
    // Validate : 유효하다

    // document.querySelector("css 선택자")
    // - css 선택자와 일치하는 요소를 얻어옴(선택한다)
    // - 여러 요소가 선택 되면, 첫번째 요소만 얻어옴 (배열로 얻어오는 게 아니라..)
    // 이 특징을 이용한 코드를 써볼거양

    // 이메일 입력 input 요소
    const memberEmail = document.querySelector("[name='memberEmail']");
    const memberPw = document.querySelector("[name='memberPw']");

    //이메일이 입력되지 않는 경우, false를 반환하는 if문을 써보자!
    if( memberEmail.value.trim().length == 0){
        // 이메일 양쪽 공백을 제거한 후 길이가 0인 경우
        // == 이메일 미작성

        alert("이메일을 입력해주셍");

        memberEmail.focus(); //이메일 input 요소에 초점을 맞춤
        memberEmail.value = ""; //이메일 input 요소에 작성된 값을 모두 삭제(띄어쓰기 등 삭제)


        return false;
    }

    //비밀번호이 입력되지 않는 경우, false를 반환하는 if문을 써보자!
    if( memberPw.value.trim().length == 0){
        // 비밀번호 양쪽 공백을 제거한 후 길이가 0인 경우
        // == 비밀번호 미작성

        alert("비밀번호를 입력해주셍");

        memberPw.focus(); //비밀번호 input 요소에 초점을 맞춤
        memberPw.value = ""; //비밀번호 input 요소에 작성된 값을 모두 삭제(띄어쓰기 등 삭제)


        return false;
    }




    return true;
}