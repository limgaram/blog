<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="<span><i class='fas fa-sign-in-alt'></i></span> <span>MEMBER LOGIN</span>" />

<%@ include file="../common/head.jspf"%>
<script>
let MemberJoin__submitFormDone = false;
function MemberJoin__submitForm(form){
    if(MemberJoin__submitFormDone){
        return;
    }

    form.loginId.value = form.loginId.value.trim();

    if(form.loginId.value.length == 0){
        alert('로그인아이디(을)를 입력해주세요.');
        form.loginId.focus();

        return;
    }

    form.loginPw.value = form.loginPw.value.trim();

    if(form.loginPw.value.length == 0){
        alert('로그인비밀번호(을)를 입력해주세요.');
        form.loginPw.focus();

        return;
    }

    form.submit();
    MemberJoin__submitFormDone = true;
}
</script>

<div class="section section-article-list">
	<div class="contatiner mx-auto">
        <form method="POST" action="doLogin" onsubmit="MemberJoin__submitForm(this); return false;">
            <input type="hidden" name="redirectUrl" value="/" />
            <div class="form-control">
                <label class="label">
                    로그인아이디
                </label>
                <input class="input input-bordered w-full" type="text" maxlength="30" name="loginId" placeholder="로그인아이디를 입력해주세요." />
            </div>

            <div class="form-control">
                <label class="label">
                    로그인비밀번호
                </label>
                <input class="input input-bordered w-full" type="password" maxlength="30" name="loginPw" placeholder="로그인비밀번호를 입력해주세요." />
            </div>

            <div class="mt-4 btn-wrap gap-1">
                <button type="submit" href="#" class="btn btn-primary btn-sm mb-1">
                    <span><i class="fas fa-sign-in-alt"></i></span>
                    $nbsp;
                    <span>로그인</span>
                </button>

                <a href="#" class="btn btn-sm mb-1">
                    <span><i class="fas fa-home"></i></span>
                    &nbsp;
                    <span>홈</span>
                </a>
            </div>

        </form>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>