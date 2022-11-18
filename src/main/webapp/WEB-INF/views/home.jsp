<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<%@ include file="include/head.jsp" %>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <%@ include file="include/main_header.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file="include/left_column.jsp" %>

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
              <li class="breadcrumb-item"><a href="${path}/">Home</a></li>
              <li class="breadcrumb-item active">Main Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-6">
            <div class="card card-primary card-outline">
             <div class="card-header">
            	<h5 class="card-title">Basic CRUD Board</h5>
             </div>
              <div class="card-body">
                <p class="card-text">
                  기본적인 CRUD 기능만 구현되어있는 게시판입니다.
                </p>
                <a href="${path}/article/write" class="btn btn-primary">게시글 작성하기</a>
                <a href="${path}/article/list" class="btn btn-primary">게시글 목록으로</a>
              </div>
            </div>

            <div class="card card-primary card-outline">
              <div class="card-header">
                <h5 class="card-title">Paging CRUD Board</h5>
              </div>
              <div class="card-body">
                <p class="card-text">
                  기본적인 CRUD + 페이징 기능만 구현되어있는 게시판입니다.
                </p>
                <a href="${path}/article/paging/write" class="btn btn-primary">게시글 작성하기</a>
                <a href="${path}/article/paging/list" class="btn btn-primary">게시글 목록으로</a>
              </div>
            </div><!-- /.card -->
            
            <div class="card card-primary card-outline">
              <div class="card-header">
                <h5 class="card-title">Paging Search CRUD Board&nbsp;&nbsp;&nbsp;( = = MAIN = = )</h5>
              </div>
              <div class="card-body">
                <p class="card-text">
                  기본적인 CRUD 와 페이징, 검색, 댓글, 파일첨부 기능이 포함된 게시판입니다.<br>
                  1. 회원가입, 회원탈퇴, 아이디중복확인<br>
                  2. 로그인, 로그아웃, 로그인유지(Cookie), 비밀번호 암호화(Bcrypt)<br>
                  3. 회원정보수정, 비밀번호수정, 프로필사진수정(X)<br>
                </p>

                <a href="${path}/article/paging/search/write" class="btn btn-primary">게시글 작성하기</a>
                <a href="${path}/article/paging/search/list" class="btn btn-primary">게시글 목록으로</a>
              </div>
            </div>
          </div>
          <!-- /.col-md-6 -->
          <div class="col-lg-6">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <h5 class="m-0">Kong's github</h5>
              </div>
              <div class="card-body">
                <h3 class="card-title">개발에 관한 기록 또는 협업을 위한 깃허브입니다.</h3>
				<br>
                <p class="card-text">개발공부를 시작하며 배운 것들 또는 만들어본 프로젝트를 비정기적으로 기록하고 있습니다.</p>
                <a href="https://github.com/OysterKong" class="btn btn-primary">깃허브로 이동</a>
              </div>
            </div>

            <div class="card card-primary card-outline">
              <div class="card-header">
                <h5 class="m-0">Kong's blog</h5>
              </div>
              <div class="card-body">
                <h3 class="card-title">개인적인 기록을 위한 블로그입니다.</h3>
				<br>
                <p class="card-text">개발공부를 시작하며 드는 개인적인 생각, 공부한 것들을 비정기적으로 기록하고 있습니다.</p>
                <a href="https://blog.naver.com/whatar" class="btn btn-primary">블로그로 이동</a>
              </div>
            </div>
          </div>
          <!-- /.col-md-6 -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

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
  <%@ include file="include/main_footer.jsp" %>
 </div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
 <%@ include file="include/plugin_js.jsp" %>
 <script>
 
	 $(document).ready(function () {
	 	
	     var msg = "${msg}";
	     if (msg == "WITHDRAW SUCCESS") {
	         alert("회원탈퇴가 성공적으로 처리되었습니다.");
	     };
	 });
 
 </script>

</body>
</html>
