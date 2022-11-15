// 업로드 JS

// 파일 정보 가공
function getFileInfo(file_name) {

    var fileName;   // 화면에 출력할 파일명
    var imgsrc;     // 썸네일 or 파일아이콘 이미지 파일 요청 URL
    var getLink;    // 원본파일 요청 URL
    var fileLink;   // 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)
    // 이미지 파일일 경우
    if (checkImageType(file_name)) {
        // 썸네일 파일 이미지 URL
        imgsrc = "/myboard/file/display?fileName=" + file_name;
        // UUID_파일명.확장자 (s_ 제외 : 원본이미지)
        fileLink = file_name.substr(14);
        // 원본파일 요청 URL
        var front = file_name.substr(0, 12); // 날짜 경로
        var end = file_name.substr(14);      // 파일명(s_ 제외)
        getLink = "/myboard/file/display?fileName=" + front + end;
    
    // 이미지 파일이 아닐 경우
    } else {
        // 파일 아이콘 이미지 URL
    	imgsrc = "/myboard/resources/upload/files/file-icon.png";
        // UUID_파일명.확장자
        fileLink = file_name.substr(12);
        // 파일 요청 url
        getLink = "/myboard/file/display?fileName=" + file_name;
    }
    // 화면에 출력할 파일명
    fileName = fileLink.substr(fileLink.indexOf("_") + 1);
    return {fileName: fileName, imgsrc: imgsrc, getLink: getLink, file_name: file_name};
}

// 이미지 파일 유무 확인
function checkImageType(fileName) {
    // 정규 표현식을 통해 이미지 파일 유무 확인
    var pattern = /jpg$|gif$|png$|jpge$/i;
    return fileName.match(pattern);
}

// 파일 목록 : 게시글 조회, 수정페이지
function getFiles(article_no) {
    $.getJSON("/myboard/file/list/" + article_no, function (list) {
        if (list.length === 0) {
            $(".uploadedList").html("<span class='noAttach'>첨부파일이 없습니다.</span>");
        }
        $(list).each(function () {
            printFiles(this);
        })
    });
}

// 첨부파일 출력
function printFiles(data) {
    // 파일 정보 처리
    var fileInfo = getFileInfo(data);
    // Handlebars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
    // Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML을 DOM에 주입
    if (fileInfo.file_name.substr(12, 2) == "s_") {
    var html = templatePhotoAttach(fileInfo);
	// 이미지 파일이 아닐 경우
	} else {
	    html = templateFileAttach(fileInfo);
	} 
    $(".uploadedList").append(html);
    // 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
    if (fileInfo.file_name.substr(12, 2) === "s_") {
        // 마지막에 추가된 첨부파일 템플릿 선택자
        var that = $(".uploadedList li").last();
        // lightbox 속성 추가
        that.find(".mailbox-attachment-name").attr("data-lightbox", "uploadImages");
        // 파일 아이콘에서 이미지 아이콘으로 변경
        that.find(".fa-paperclip").attr("class", "fa fa-camera");
    }
}


