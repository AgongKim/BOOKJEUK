<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function checkWrite(){
	var frm = document.writeForm;
	if(!frm.book_name.value){
		alert("그거아세요 제가 18살때..");
		return false;
	}else if(!frm.writer.value){
		alert("아맞다 작가!");
		return false;
	}else if(!frm.publisher.value){
		alert("아맞다 출판사!");
		return false;
	}else if(!frm.grade.value){
		alert("아맞다 평점!");
		return false;
	}else if(!frm.book_summary.value){
		alert("아맞다 요약!");
		return false;
	}else if(!frm.book_link.value){
		alert("아맞다 북링크!");
		return false;
	}else if(!frm.photo.value){
		alert("아맞다 사진!");
		return false;
	}else{
		frm.submit();
	}
}
</script>
</head>
<body>
	<h1>줮같아서 만든 데이터 입력 페이지~~~</h1>
	<form name="writeForm" action="book_insert.do" method="post" enctype="multipart/form-data">
		<h3>책제목</h3>
		<input type="text" name="book_name">
		<h3>작가</h3>
		<input type="text" name="writer">
		<h3>출판사</h3>
		<input type="text" name="publisher">
		<h3>평점 (0.0~5.0)</h3>
		<input type="text" name="grade">
		<h3>책 내용 요약</h3>
		<input type="text" name="book_summary">
		<h3>책 구매 링크</h3>
		<input type="text" name="book_link">
		<h3>책 사진</h3>
		<input type="file" name="photo">		
		<div>
			<input type="checkbox" name="genre_poet" value="1">시/문학	
			<input type="checkbox" name="genre_essay" value="1">에세이	
			<input type="checkbox" name="genre_selfDev" value="1">자기계발	
			<input type="checkbox" name="genre_history" value="1">역사	
			<input type="checkbox" name="genre_science" value="1">과학	
			<input type="checkbox" name="genre_novel" value="1">소설	
			<input type="checkbox" name="genre_comics" value="1">만화	
			<input type="checkbox" name="genre_art" value="1">예술	
		</div>
		<input type="button" value="입력감사" onclick="checkWrite()">
	</form>
</body>
</html>