<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<%@ include file="../include/head.jsp" %>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <%@ include file="../include/main_header.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file="../include/left_column.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">OysterKong 의 게시판에 오신것을 환영합니다.</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->


    <!-- Content Header (Page header) -->
	<div class="container-fluid">
	  <div class="row">
	    <div class="col-md-5">
		  <div class="card card-primary card-outline">
		  <div class="card-body box-profile">
		<div class="text-center">
		  <img class="profile-user-img img-fluid img-circle" 
		  src="${path}/dist/img/profile/${login.userImg}"
		  alt="User profile picture">
		</div>

<h3 class="profile-username text-center">${login.userName}</h3>

<div class="text-center">
<a href="#" class="btn btn-primary btn-xs UserImgModify" data-toggle="modal"
data-target="#userPhotoModal"> <i class="fa fa-photo">
프로필사진 수정</i>
</a>
</div>
<ul class="list-group list-group-unbordered mb-5">
<li class="list-group-item"><b>아이디</b> <a
class="float-right">${login.userId}</a></li>
<li class="list-group-item"><b>이메일</b> <a
class="float-right">${login.userEmail}</a></li>
<li class="list-group-item"><b>가입일자</b> <a
class="float-right"> <fmt:formatDate
value="${login.userJoinDate}" pattern="yyyy-MM-dd" />
</a></li>
<li class="list-group-item"><b>최근 로그인 일자</b> <a
class="float-right"> <fmt:formatDate
value="${login.userLoginDate}" pattern="yyyy-MM-dd" />

</a></li>
</ul>
</div>
<div class="card-footer text-center">
<a href="#" class="btn btn-primary btn-xs UserInfoModBtn" data-toggle="modal"
data-target="#userInfoModal"> <i class="fa fa-info-circle">
회원정보 수정</i>
</a> <a href="#" class="btn btn-primary btn-xs UserPwModBtn" data-toggle="modal"
data-target="#userPwModal"> <i
class="fa fa-question-circle"> 비밀번호 수정</i>
</a> <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
data-target="#userWithdrawal"> <i class="fa fa-user-times">
회원 탈퇴</i>
</a>
</div>
</div>
</div>

<div class="col-md-7">
<div class="card">
<div class="nav-tabs-custom">
<div class="card-header p-2">
<ul class="nav nav-pills">
<li class="nav-item">
<a class="nav-link active" href="#myPosts" data-toggle="tab">
<i class="fas fa-pencil-square-o"></i> 나의 게시물
</a>
</li>
<li class="nav-item">
<a class="nav-link" href="#myReplies" data-toggle="tab">
<i class="fas fa-comment-o"></i> 나의 댓글
</a>
</li>										
</ul>
</div>
<div class="card-body">
<div class="tab-content">
<div class="active tab-pane" id="myPosts">
<table id="myPostsTable"
class="table table-bordered table-striped">
<thead>
<tr>
<th style="width: 10%">번호</th>
<th style="width: 70%">제목</th>
<th style="width: 20%">작성일자</th>
</tr>
</thead>
<tbody>
<c:forEach var="articleDto" varStatus="i"
items="${userBoardList}">
<tr>
<td>${i.index + 1}</td>
<td><a
href="${path}/article/paging/search/read?article_no=${articleDto.article_no}">
<c:choose>
<c:when test="${fn:length(articleDto.title) > 30}">
<c:out value="${fn:substring(articleDto.title, 0, 29)}" />....
</c:when>
<c:otherwise>
<c:out value="${articleDto.title}" />
</c:otherwise>
</c:choose>
</a></td>
<td><fmt:formatDate pattern="yyyy-MM-dd"
value="${articleDto.regDate}" /></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<div class="tab-pane" id="myReplies">
<table id="myRepliesTable"
class="table table-bordered table-striped">
<thead>
<tr>
<th style="width: 15px">번호</th>
<th style="width: 150px">게시글 제목</th>
<th style="width: 300px">내용</th>
<th style="width: 150px">작성일자</th>
</tr>
</thead>
<tbody>
<c:forEach var="userReply" varStatus="i"
items="${userReplies}">
<tr>
<td>${i.index + 1}</td>
<td><a
href="${path}/article/paging/search/read?article_no=${userReply.articleDto.article_no}">
<c:choose>
<c:when
test="${fn:length(userReply.articleDto.title) > 10}">
<c:out
value="${fn:substring(userReply.articleDto.title, 0, 9)}" />....
</c:when>
<c:otherwise>
<c:out value="${userReply.articleDto.title}" />
</c:otherwise>
</c:choose>
</a></td>
<td><c:choose>
<c:when test="${fn:length(userReply.reply_text) > 10}">
<c:out
value="${fn:substring(userReply.reply_text, 0, 9)}" />....
</c:when>
<c:otherwise>
<c:out value="${userReply.reply_text}" />
</c:otherwise>
</c:choose></td>
<td><fmt:formatDate pattern="yyyy-MM-dd"
value="${userReply.reg_date}" /></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</div>

</div>
</div>
</div>

</div>
</div>

  <!-- /.content-wrapper -->
</div>
  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <%@ include file="../include/main_footer.jsp" %>
 </div>
<!-- ./wrapper -->
		
		<!-- 회원이미지 수정하기 modal 영역 -->
		<div class="modal fade" id="userPhotoModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form role="form" id="userImageForm" method="post" action="${path}/user/modify/image" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">프로필 이미지 수정</h4>
                        </div>
                        <div class="modal-body">
                            <div class="text-center">
                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                    <div class="fileinput-new thumbnail" style="width: 150px; height: 150px; margin: 0 auto;" onchange="setThumnail(event);">
                                        <img src="${path}/dist/img/profile/${login.userImg}" alt="..." style="width: 100%;">
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 200px;"></div>
                                    <div>
                                    <span class="btn btn-default btn-file">
                                        <span class="fileinput-new">이미지 선택</span>
                                        <span class="fileinput-exists">변경</span>
                                        <input type="file" id="userImgFile" name="userImgFile">
                                        <input type="hidden" name="userId" value="${login.userId}">
                                    </span>
                                        <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">제거</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                            <c:if test="${login.userImg ne '${path}/dist/img/profile/user/default-user.png'}">
                                <button type="button" class="btn btn-primary">프로필 이미지 삭제</button>
                            </c:if>
                            <button type="submit" class="btn btn-primary imgModBtn">프로필 이미지 변경 저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

		<!-- 회원정보 수정하기 modal 영역 -->
        <div class="modal fade" id="userInfoModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form role="form" id="userInfoForm" action="${path}/user/modify/info" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">회원정보 수정</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group has-feedback">
                                <input type="text" id="userId" name="userId" class="form-control" placeholder="아이디" value="${login.userId}" readonly>
                                <span class="glyphicon glyphicon-exclamation-sign form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="text" id="userName" name="userName" class="form-control" placeholder="이름" value="${login.userName}">
                                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                            <input type="email" id="userEmail" name="userEmail" class="form-control" placeholder="이메일" value="${login.userEmail}">
                            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" id="userPw" name="userPw" class="form-control" placeholder="비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary infoModBtn">수정 저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- 비밀번호 수정하기 modal 영역 -->
        <div class="modal fade" id="userPwModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form role="form" id="userPwForm" action="${path}/user/modify/pw" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">비밀번호 수정</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group has-feedback">
                                <input type="hidden" name="userId" class="form-control" value="${login.userId}">
                                <input type="password" name="oldPw" class="form-control" placeholder="현재 비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" name="newPw" class="form-control" placeholder="변경할 비밀번호">
                                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                            </div>
                            <div class="form-group has-feedback">
                                <input type="password" name="newPw2" class="form-control" placeholder="변경할 비밀번호 확인">
                                <span class="glyphicon glyphicon-check form-control-feedback"></span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                            <button type="button" class="btn btn-primary pwModBtn">비밀번호 수정</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- 회원탈퇴 기능 modal 영역 -->
        <div class="modal fade" id="userWithdrawal" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                  <form role="form" id="withdrawalForm" action="${path}/user/withdraw/userInfo" method="post">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        	<h4 class="modal-title">회원 탈퇴</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group has-feedback">
                            <input type="password" name="userPw" class="form-control" placeholder="비밀번호 입력">
                            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-primary withdrawUserInfo">회원 탈퇴</button>
                    </div>
                  </form>
                </div>
            </div>
        </div>
        

<!-- REQUIRED SCRIPTS -->
 <%@ include file="../include/plugin_js.jsp" %>
<script src="${path}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${path}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>

<script>

	function setThumbnail(event) {
	    var reader = new FileReader();
	
	    reader.onload = function(event) {
	      var img = document.createElement("img");
	      img.setAttribute("src", event.target.result);
	      document.querySelector("div.thumbnail").append(img);
	    };
	
	    reader.readAsDataURL(event.target.files[0]);
	  }


    $(document).ready(function () {
    	
        var msg = "${msg}";
        if (msg == "FAILURE") {
            alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요");
        } else if (msg == "FAILURE2") {
       	 	alert("입력하신 새로운 비밀번호끼리 서로 일치하지 않습니다. 비밀번호를 올바르게 입력해주세요.");
        } else if (msg == "SAME") {
        	alert("기존 비밀번호와 바꾸려는 비밀번호가 같습니다. 확인 후 다시 설정해주세요");
        } else if (msg == "FAIL") {
            alert("이미지가 존재하지 않습니다.");
        } else if (msg == "SUCCESS") {
            alert("성공적으로 수정되었습니다.");
        }
        // 회원정보 수정
        $(".infoModBtn").on("click", function () {
            $("#userInfoForm").submit();
        });
        // 회원비밀번호 수정
        $(".pwModBtn").on("click", function () {
            $("#userPwForm").submit();
        });
        // 회원 프로필 이미지 수정
        $(".imgModBtn").on("click", function () {
            var file = $("#file").val();
            if (file == "") {
                alert("파일을 선택해주세요.");
                return;
            }
            $("#userImageForm").submit();
        });
        // 회원 탈퇴
        $(".withdrawUserInfo").on("click", function() {
        	$("#withdrawalForm").submit();
        })
        var param = {
            "language": {
                "lengthMenu": "_MENU_ 개씩 보기",
                "zeroRecords": "내용이 없습니다.",
                "info": "현재 _PAGE_ 페이지 / 전체 _PAGES_ 페이지",
                "infoEmpty": "내용이 없습니다.",
                "infoFiltered": "( _MAX_개의 전체 목록 중에서 검색된 결과)",
                "search": "검색:",
                "paging": {
                    "first": "처음",
                    "last": "마지막",
                    "next": "다음",
                    "previous": "이전"
                }
            }
        };
        $("#myPostsTable").DataTable(param);
        $("#myRepliesTable").DataTable(param);
        $("#myBookmarksTable").DataTable(param);
    });
</script>
</body>
</html>
