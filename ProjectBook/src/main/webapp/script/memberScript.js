

//입력검사 이름,아이디,비번
function checkWrite(){
	var frm =document.writeForm;
	
	if(!frm.name.value){
		alert("이름을 입력해주세요");
		return false;
	}
	else if(!frm.id.value){
		alert("아이디를 입력해주세요");
		return false;
	}
	else if(!frm.pw.value){
		alert("비밀번호를 입력해주세요");
		return false;
	}
	else if(frm.pw.value!=frm.repw.value){
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	if (confirm("입력하신 내용이 맞습니까?")) {
        frm.submit();
    }
}

//로그인화면 입력검사
function checkLogin(){
	
	var frm = document.loginForm;
	
	if(!frm.id.value){
		alert("아이디를 입력하세요");
		return false;
	} else if(!frm.pw.value){
		alert("비밀번호를 입력하세요");
		return false;
	} else{
		frm.submit();
	}
}
//아이디 중복 검사 버튼 클릭
function checkId(){
	//아이디 읽이오기
	var sId = document.writeForm.id.value;
	//입력값이 있는 지 검사
	if(sId==""){
		alert("먼저 아이디를 입력하랑께");
	}else{
		window.open("checkId.jsp?id="+sId,"_blank",
					"width=800 height=500 left=500 top300");
	}
	
	
}





