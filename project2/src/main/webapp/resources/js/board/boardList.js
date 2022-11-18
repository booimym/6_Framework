
//썸네일 클릭 시 Modal창으로 출력하기


//즉시 실행 함수
(()=>{

    //1. 썸네일 없는 글도 있잖아. 
    //썸네일 띄우기 전에 썸네일이 있는지 여부부터 검사하기(길이로 검사 가능함)
    
    const thumbnailList = document.getElementsByClassName("list-thumbnail");

    if(thumbnailList.length > 0 ) { //썸네일이 존재할 경우

        //2. 있으니까 //(1) Modal 관련 요소 얻어오기
        const modal =  document.querySelector(".modal");
        const modalClose = document.getElementById("modal-close");
        const modalImage = document.getElementById("modal-image");

        //(2) 썸네일 요소에 클릭 이벤트를 추가
        for (let th of thumbnailList){
            th.addEventListener("click", () => {

                //modal 창에 show클래스가 없으면 추가(있으면 삭제)
                modal.classList.toggle("show");


                //클릭한 썸네일의 src 속성값을 얻어와서
                //modal-image의 src 속성으로 세팅한다!

                //th.getAttribute("src");
                modalImage.setAttribute("src",th.getAttribute("src"));
            })
        }
        // x버튼 동작
        modalClose.addEventListener("click",() => {
    
            // hide 클래스를 추가해서 0.5초동안 투명해지는 애니메이션 수행
            modal.classList.toggle("hide");
    
            // 0.5초 후에 show,hide 클래스를 모두 제거
            //setTimeout(함수,지연시간);
            setTimeout(()=>{
                modal.classList.remove("show","hide");
            },500);
        });

    }



})();