<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.comm{witdh:400px;height:100px;border:1px solid #aaa;margin-bottom:5px;}
</style>
<script type="text/javascript">
	function delComm(n){
		let xhr=XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let dat=xhr.responseText;
				let json=JSON.parse(dat);
				if(json.doce=='success'){
					commList();
				}
			}
		};
		xhr.open('post','${pageContext.request.contextPath}/comm/delete?num='+num,true);
		xhr.send();
	}
	window.onload=function(){
		commList();
	}
	function commList(){
		let xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let dat=xhr.responseText;
				let comm=JSON.parse(dat);
				let div2=document.getElementById("commList");
				let child=div2.childNodes;
				for(let i=child.length-1;i>=0;i--){
					let c=child.item(i);
					div2.removeChild(c);
				}
				for(let i=0;i<comm.length;i++){
					var id=comm[i].id;
					var comments=comm[i].comments;
					var num=comm[i].num;
					var mnum=comm[i].mnum;
					var divv=document.createElement("div");
					divv.innerHTML="아이디 : "+id+"<br>"+"내용: "+comments+"<br>"
									+"<a href='javascript:delComm("+num+")'>삭제</a>";
					divv.className="comm";
					div2.appendChild(divv);
				}
				let startPage=data.startPage;
				let endPage=data.endPage;
				let pageCount=data.pageCount;
				let pageHTML="";
				if(startPage>5){
					pageHTML+="<a href='javascript:commList("+(startPage-1)+")'>이전</a>";
				}
				for(let i=startPage;i<=endPage;i++){
					if(i==pageNum){
						pageHTML+="<a href='javascript:commList("+i+")'><span style='color:blue'>["+i+"]</span></a>";
					}else{
						pageHTML+="<a href='javascript:commList("+i+")'><span style='color:gray'>["+i+"]</span></a>"
					}
				}
				if(endPage<pageCount){
					pageHTML+="<a href='javascript:commList("+(endPage+1)+")'>다음</a>";
				}
				var page=document.getElementById("page");
				page.innerHTML=pageHTML;
			}
		};
		//xhr.open('get','${pageContext.request.contextPath}/comm/list?mnum=${vo.mnum}&pageNum='+pageNum,true);
		xhr.open('get','${pageContext.request.contextPath}/comm/list?mnum=${vo.mnum}',true);
		xhr.send();
	}
	function addComm(){
		let xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let dat=xhr.responseText;
				let json=JSON.parse(dat);
				if(json.code=='success'){
					alert('글등록성공');
				}
			}
		};
		let id=document.getElementById("id").value;
		let comm=document.getElementById("comments").value;
		let param="id="+id+"&comments="+comments+"&mnum=${vo.mnum}";
		xhr.open('post','${pageContext.request.contextPath}/comm/insert',true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send(param);
	}
</script>
</head>
<body>
<div>
<h1>${vo.title }</h1>
<br>
내용 : ${vo.content }<br>
감독 : ${vo.director }<br>
</div>
<div>
	<div id="commList"></div>
	<div id="commAdd">
		아이디<br>
		<input type="text" id="id"><br>
		영화평<br>
		<textarea rows="3" cols="50" id="comments"></textarea><br>
		<input type="button" value="등록" onclick="addComm()">
	</div>
</div>
<form action="" enctype="application/x-www-form-urlencoded">
</form>
</body>
</html>