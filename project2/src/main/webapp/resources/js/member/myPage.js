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

//------------------------------------------------------------------------------------------------------
// 프로필 수정

const profileImage =  document.getElementById("profile-image");
const deleteImage =  document.getElementById("delete-image");
const imageInput = document.getElementById("image-input");

// 초기 프로필 이미지 상태를 저장하는 변수
// (true : 업로드된 이미지가 있음, false : 기본 이미지 상태)
let initCheck; 

// 이미지가 업로드 되었거나 삭제되었음을 나타내는 변수
// (-1 : 초기값, 0 :프로필 삭제(X버튼 클릭), 1 : 새 이미지 업로드)
let deleteCheck = -1;



// 프로필 수정 페이지에 처음 들어왔을 때의 이미지 경로
const originalImage = profileImage.getAttribute("src"); 

// 프로필 수정 화면일 경우

if(imageInput != null){

    // 해당 화면 진입 시 프로필 이미지 상태를 저장(initCheck)
    if(profileImage.getAttribute("src") == "/resources/images/user.png"){

        // 기본 이미지인 경우
        initCheck = false;

    } else {
        
        initCheck = true;
    }

    //이미지가 선택되었을 때 미리보기

    // * input type = "file" 요소는 값이 없을 때는 ''(빈칸)이다.
    // * input type = "file" 요소는 이전에 선택한 파일이 있어도 취소하면 다시 ''(빈칸)이 된다.
    // * input type = "file" 요소로 파일을 선택하면 change 이벤트가 발생한다.

    imageInput.addEventListener("change", e => {

        //e.target : 이벤트가 발생한 요소 (==imageInput)
        // *화살표 함수에서는 this를 못쓰기 때문에...(this는 window 객체를 의미하기 때문....)
        //e.target을 쓴다.

        console.log(e.target.files); 
        
        // e.target.files -> 선택된 파일의 목록이 나온다. 
        // 근데 우리는 늘 1개만 선택하잖아. 그니까...목록 중에서 [0]이지...
        console.log(e.target.files[0]); 

        //-----------------------------------------------------------------------------------------
        
        //선택된 파일이 있을 경우(없으면 undefined 나옴...)
        if(e.target.files[0] != undefined){

            const reader = new FileReader(); 
            // 자바스크립트가 제공하는 객체 중에 FileReader라는 객체가 있음.
            // FileReader (file을 읽는 객체)
            // - 웹 애플리케이션이
            // 비동기적으로 데이터를 읽기 위하여
            // 읽을 파일을 가리키는 File 객체이다.
            // 쉽게 말해서 file을 읽는 객체임!
            
            // + 읽어들인 파일을 사용자 컴퓨터에 저장할 수 있다!

            reader.readAsDataURL(e.target.files[0]); //이걸 적는 순간 이제 실제로 파일을 읽어들인다....
            // FileReader.readAsDataURL("파일정보")
            // -> 지정된 파일을 읽기 시작함

            // FileReader.onload : 파일 읽기가 완료되었을 때...라는 고전이벤트 형식이래..그에 대한 응답 지정하는 형식
            reader.onload = event => {

                console.log(event.target);

                //  event.target.result : 읽어진 파일의 결과(실제 이미지 파일)의 경로
                event.target.result

                // img 태그의 src 속성으로 읽은 이미지 파일 경로를 추가
                // == 이미지 미리보기
                profileImage.setAttribute("src", event.target.result);

                deleteCheck = 1;
            } 

        } else { // 취소가 눌러진 경우 , 이전 이미지로 돌아가게 해볼게!

            //1) 처음 이미지 경로를 변수 지정해준다음에....
            //const originalImage = profileImage.getAttribute("src"); 라고 위에 씀.
            profileImage.setAttribute("src", originalImage);

        }
        

    })

    //x버튼이 클릭되었을 경우 -> 기본 이미지(화면 들어올 때의 이미지가 아니고 걍 아예 쌩처음이미지)로 변경하겠다!!!!!
    deleteImage.addEventListener("click", () =>{
        profileImage.setAttribute("src", "/resources/images/user.png");
        imageInput.value = "";
        deleteCheck = 0;
    })

}

function profileValidate(){


    //이미지가 없다가 -> 있음
    if(!initCheck && deleteCheck == 1){
        return true;
    }


    //이미지가 있었다가 -> x를 눌러서 없어짐.
    if(initCheck && deleteCheck == 0){
        return true;
    }
        
    //있음 -> 있음(새로운 이미지 업로드)
    if(initCheck && deleteCheck == 1){
        return true;
    }

    alert("이미지 변경 후 클릭하세요");
    return false;
}
