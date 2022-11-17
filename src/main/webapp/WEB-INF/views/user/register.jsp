<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="../include/head.jsp" %>
<body class="hold-transition register-page">
<div class="register-box">
  <div class="register-logo">
    <a href="${path}/">
      <b>Kong's</b>MVC-BOARD
    </a>
  </div>

  <div class="card">
    <div class="card-body register-card-body">
      <p class="login-box-msg">회원가입</p>

      <form action="${path}/user/register" method="post">
        <div class="input-group mb-3">
          <input type="text" name="userId" class="form-control" placeholder="아이디">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-exclamation"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="text" name="userName" class="form-control" placeholder="이름">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="email" name="userEmail" class="form-control" placeholder="이메일">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="userPw" class="form-control" placeholder="비밀번호" autoComplete="off">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="userPw2" class="form-control" placeholder="비밀번호 확인" autoComplete="off">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms" value="agree">
              <label for="agreeTerms">
               이 <a href="#">규정</a> 동의 합니다.
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block">등록</button>
          </div>
          <!-- /.col -->
        </div>
      </form>

      <a href="${path}/user/login" class="text-center">이미 계정이 있어요 !</a>
    </div>
    <!-- /.form-box -->
  </div><!-- /.card -->
</div>
<!-- /.register-box -->

<%@ include file="../include/plugin_js.jsp" %>
<script>
	
	$(document).ready(function () {
	
    var msg = "${msg}";
    if (msg == "FAILURE") {
        alert("입력하신 비밀번호가 서로 다릅니다. 확인 후 올바르게 입력해주세요.");
    	};
	});

    
</script>
</body>
</html>