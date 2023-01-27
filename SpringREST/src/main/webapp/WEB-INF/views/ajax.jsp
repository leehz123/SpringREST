<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ajax연습</title>
</head>
<body>

<!-- http://localhost:8888/restful/ajax -->

<h3>Ajax</h3>

<button id="btn-xhttp">XHttp 요청 보내기</button><br>
<br />
<div id="out"></div>


<hr>
name : 
	<select name= "name" id="select-pizza-name">
		<c:forEach items="${pizzas }" var="pizza">
		<option value="${pizza.p_id }">${pizza.p_name }</option>
	</c:forEach>	
</select><br>
price : <input type="text" id="input-pizza-price" placeholder="피자를 선택하면 값이 채워집니다."><br>
calories : <input type="text" id="input-pizza-calories" placeholder="피자를 선택하면 값이 채워집니다."><br>
pizza_id : <input type="text" id="input-pizza-id" placeholder="피자를 선택하면 값이 채워집니다." readonly><br>
<input  type="hidden" id ="input-pizza-name"/>
<button id="btn-xhttp-put">XHttp PUT 해보기 (Update)</button><br>
<br />
<div id="out2">아직 수정 전입니다.</div>


<hr>
name : <input id="post-pizza-name" type="text" />
price :	<input id="post-pizza-price" type="text" />
calories : <input id="post-pizza-calories" type="text" />
<button id="btn-xhttp-post">XHttp POST 해보기 (Insert)</button>
<br /><br />
<div id = "out3">아직 추가 전입니다.</div>


<script src="/restful/resources/js/ajax.js"></script>

</body>
</html>