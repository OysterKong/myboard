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




