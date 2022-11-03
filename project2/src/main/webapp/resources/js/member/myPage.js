//비밀번호 변경 유효성 검사

//비밀번호 변경 form 요소
const changePwForm = document.getElementById("changePwForm");

//null일 때는 에러나니까 그걸 막기 위해서..
if(changePwForm != null){ // changePwForm요소가 존재할 때
    changePwForm.addEventListener("submit", function(event){

        //**이벤트 핸들러 매개변수 event OR e */
        // -> 현재 발생한 이벤트 정보를 가지고 있는 event객체가 전달됨.

        console.log(event);


        //비밀번호 변경에 사용되는 input요소들 얻어오기~
        const currentPw  = document.getElementById("currentPw");
        const newPw  = document.getElementById("newPw");
        const newPwConfirm  = document.getElementById("newPwConfirm");

        //(1) 현재 비밀번호가 작성되지 않았을 때
        if(currentPw.value.trim().length==0){
            //alert("현재 비밀번호를 입력해주세요.");
            
           // currentPw.focus();
            
            //다 지워버리기
           // currentPw.value = "";
            
            alertAndFocus(currentPw,"현재비밀번호를 입력해주세요.");

            //return false;
            //->인라인 이벤트 모델 onsubmit = "return 함수명()"; 에서만 가능하다.

            event.preventDefault();
            // -> 이벤트를 수행하지 못하게 하는 함수
            // -> 기본 이벤트 삭제

            return;
        }
        //(2) 새 비밀번호가 작성되지 않았을 때
        if(newPw.value.trim().length == 0){
            alert("새 비밀번호를 입력해주세요");

            newPw.focus();
            newPw.value = "";


            event.preventDefault(); // form 기본 이벤트 제거
            return;
        }
        //(3) 새 비밀번호 확인이 작성되지 않았을 때
        if(newPwConfirm.value.trim().length == 0){
            alert("새 비밀번호 확인을 입력해주세요");
            newPwConfirm.focus();
            newPwConfirm.value = "";

            event.preventDefault();
            return;
        }

        // 비밀번호 정규식 검사 필요함!!!!!!

        // 새 비밀번호, 새 비밀번호 확인이 같은지 검사
        if(newPw.value != newPwConfirm.value){
            alert("새 비밀번호가 일치하지 않습니다.");
            newPwConfirm.focus();
            event.preventDefault(); //기본 이벤트 제거
            return;//함수 종료
        }

    })
}

// 경고창 출력 + 포커스 이동 + 값 삭제
function alertAndFocus( input , message){

    alert(message);
    input.focus();
    input.value = "";

}

// 회원 탈퇴 유효성 검사하기
// - 인라인 이벤트 모델 또는 표준 이벤트 모델로 작성하기

// 1) 비밀번호 미작성 -> "비밀번호를 입력해주세요" alert 출력 후 
                                                //포커스 이동(내용도 같이 삭제)
// 2) 동의 체크가 되지 않은 경우 -> "탈퇴를 동의하시면 체크를 눌러주세요"
                                                // 포커스를 이동해라
// 3) 1번 2번이 모두 유효할 때
// 정말 탈퇴를 진행할 것인지 확인하는 confirm 출력!
// (확인 클릭 -> 탈퇴 / 취소 -> 탈퇴 취소하기)

const memberDeleteForm = document.getElementById("memberDeleteForm");

if( memberDeleteForm != null){
                                //제출이 되었을 때의 동작
    memberDeleteForm.addEventListener("submit", function(event){

        // 1) 비밀번호 미작성 -> "비밀번호를 입력해주세요" alert 출력 후 
                                            //포커스 이동(내용도 같이 삭제)
        
        const memberPw = document.getElementById("memberPw");

        if(memberPw.value.trim().length == 0){

            alert("비밀번호를 입력해주세요!!!");
            memberPw.focus();
            memberPw.value = "";

            event.preventDefault(); //제출 못하게 하는.... (form 기본 이벤트 제거)
            return;

        }

        const agree = document.getElementById("agree");

        //!agree.checked 도 됨...check되어있으면 true 아니면 false임
        if(agree.checked == false){

            alert( "탈퇴를 동의하시면 체크를 눌러주세요");

            agree.focus();
            event.preventDefault(); //제출 못하게 하는.... (form 기본 이벤트 제거)
            return;
        }

        const str = "정말 탈퇴를 진행할 것임?";

        if( !confirm(str)){ //취소를 누른 경우
            alert("탈퇴취소");
            agree.checked = false;
            memberPw.value = "";
            event.preventDefault(); //제출 못하게 하는.... (form 기본 이벤트 제거)
            return;
            
        }
    
    })

    
}


