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

      <form action="${path}/user/register" id=registerForm method="post">
        <div class="input-group mb-3">
          <input type="text" name="userId" id=userId class="form-control" placeholder="아이디" required oninput = "checkId()">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-exclamation"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-1">
          <div class="col-8">
          <div>
			<span class="validId" style="color:green; display:none;">사용 가능한 아이디입니다.</span>
			<span class="invalidId" style="color:red; display:none;">중복된 아이디입니다.</span>
			</div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="text" name="userName" id=userName class="form-control" placeholder="이름">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="email" name="userEmail" id=userEmail class="form-control" placeholder="이메일">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="userPw" id=userPw class="form-control" placeholder="비밀번호" autoComplete="off">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="userPw2" id=userPw2 class="form-control" placeholder="비밀번호 확인" autoComplete="off">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms">
              <label for="agreeTerms">
               이 <a href="#">이용약관</a>에 동의 합니다.
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="button" id="userRegister" class="btn btn-primary btn-block">등록</button>
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
    	} else if (msg == "INVALID") {
            alert("중복된 아이디로는 회원가입이 불가능합니다.");
    	};
    
    	$('#userRegister').on("click", function () {
    	if($('#userId').val() == '') {
    		alert('아이디를 입력해주세요.');
    		$('#userId').focus();
    		return;
    	}
    	if($('#userName').val() == '') {
    		alert('이름을 입력해주세요.');
    		$('#userName').focus();
    		return;
    	}
    	if($('#userEmail').val() == '') {
    		alert('이메일을 입력해주세요.');
    		$('#userEmail').focus();
    		return;
    	} else if (!isEmail($('#userEmail').val())) {
    		alert("올바른 이메일 형식이 아닙니다. 다시 확인 후 입력해주세요.")
    		$('#userEmail').focus();
    		return;
    	}
    	if($('#userPw').val() == '') {
    		alert('비밀번호를 입력해주세요.');
    		$('#userPw').focus();
    		return;
    	}
    	if($('#userPw2').val() == '') {
    		alert('비밀번호 확인이 누락되었습니다. 확인 후 입력해주세요.');
    		$('#userPw2').focus();
    		return;
    	}
    	if($('#userId').val() == $('#userPw').val()) {
    		alert('아이디와 비밀번호는 같게 만드실 수 없습니다.');
    		$('#userPw').focus();
    		return;
    	}
    	agree_TermsCheck();
    	$('#registerForm').submit();
    });
});

	function checkId() {
		var userId = $("#userId").val();
	$.ajax({
		url:'${path}/user/duplicationCheck',
		type:'post',
		data:{userId:userId},
		success:function(result) {
			if(result != 1 && userId.length > 0) {
				$('.validId').css("display", "inline-block");
				$('.invalidId').css("display", "none");    					
			} else if(result == 1 && userId.length > 0) {
				$('.validId').css("display", "none");
				$('.invalidId').css("display", "inline-block");  
			} else {
				$('.validId').css("display", "none");
				$('.invalidId').css("display", "none"); 
			}
		},
		error:function(request, error) {
			alert("아이디 중복 검사가 실패했습니다.");
		}
	});
};

	function isEmail(asValue) {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	 
		return regExp.test(asValue);
	}
	

	
	$('#agreeTerms').change(function() {
		agree_TermsCheck();
	});
	
	
	function agree_TermsCheck() {
		if($('#agreeTerms').prop("checked")) {
			console.log("체크");
			$('#userRegister').prop("disabled", false);
		} else {
			console.log("언 체크");
			$('#userRegister').prop("disabled", true);
		}
	};
    
</script>
</body>
</html>