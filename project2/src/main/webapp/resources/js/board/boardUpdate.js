// 이미지 미리보기


const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");

// 기존에 존재하다가 삭제된 이미지의 순서를 기록!
const deleteSet = new Set(); // 순서x, 

// 미리보기와 관련된 모든 요소의 개수는 5개로 동일함.
// == 인덱스 번호를 통해 하나의 그룹을 지정할 수 있음.

// inputImage[0] preview[0] deleteImage[0]

//for문 돌리면 하나의 그룹 내에서 [i]번째끼리 다같이 한번에 동작할 수 있겠군.....
for(let i = 0 ; i < inputImage.length ; i++){

    // inputImage[i] 요소의 값이 변했을 때
    inputImage[i].addEventListener("change", event => {

        //매개변수 event
        //event.target = 이벤트가 발생한 요소라는 뜻...
        //event.target.files : 선택된 파일의 정보가 배열 형태로 반환
        if(event.target.files[0] != undefined){ //선택된 파일이 있을 경우

            const reader = new FileReader(); //파일을 읽는 객체

            reader.readAsDataURL(event.target.files[0]);
            // 지정된 input type = "file"의 파일을 읽어와서
            // URL형태로 저장(이 URL이 해석되면 이미지 모양이 된당!)

            reader.onload = e => { //파일을 다 읽어온 후
                //e : load된 객체(=읽어온 객체)를 의미한다.
                //e.target == reader
                //e.target.result == 읽어온 파일 URL

                //미리보기가 추가됨 == 파일이 추가되었다
                //==삭제하면 안된다 == deleteSet에서 해당 순번을 제거한다
                deleteSet.delete(i);

                preview[i].setAttribute("src",e.target.result);
            }

        } else { //취소를 누를 경우

            //미리보기 지우기
            preview[i].removeAttribute("src"); //src속성을 제거

        }


    })


    //미리보기 삭제 버튼 클릭 시 동작!
    deleteImage[i].addEventListener("click", () => {

        // 미리보기 이미지가 존재할 때에만 삭제
        if(preview[i].getAttribute("src") != ""){

            //미리보기 삭제
            preview[i].removeAttribute("src");

            //input의 값을 ""으로 만들어서 삭제하겠다.
            inputImage[i].value = "";

            //deleteSet에 삭제된 이미지의 순서(i)를 추가한다.
            deleteSet.add(i);


        }

    })

}

//게시글 수정 유효성 검사
function updateValidate() {
    const boardTitle = document.querySelector("[name = 'boardTitle']");
    const boardContent = document.querySelector("[name='boardContent']");

    if(boardTitle.value.trim().length == 0){
        
        alert("제목을 입력해주세요.");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }

    if(boardContent.value.trim().length == 0){
        
        alert("내용을 입력해주세요.");
        boardContent.value = "";
        boardContent.focus();
        return false;
    }

    // deleteSet의 값을 "0,1,2"의 형태로 변환하여
    // form태그 내부 input태그로 추가하여
    // 같이 제출하게 만들기

    //Array.from(deleteSet) : deleteSet을 배열로 변환하는 거임
    // js배열 특징 : 배열을 HTML요소 또는 console에 출력하면
                    // 요소,요소,요소 형태의 문자열로 변환이 된다.

    document.getElementById("deleteList").value = Array.from(deleteSet);

    return true;
}





























