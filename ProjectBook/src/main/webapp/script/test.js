function checkIdClose(aaa){
		//opener 내장 객체 : 현재 브라우저 기준으로 부모 브라우저를 관리하는 객체 
		opener.writeForm.id.value = aaa;
		window.close();
		opener.writeForm.pw.focus();
	}
	function checkId(){
		var frm = document.chkId;
		if(!frm.id.value){
			alert("아이디를 입력하랑께!");
		}
		else{
			frm.submit();
		}
	}