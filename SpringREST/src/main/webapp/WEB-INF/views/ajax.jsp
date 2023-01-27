<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ajax����</title>
</head>
<body>

<!-- http://localhost:8888/restful/ajax -->

<h3>Ajax</h3>

<button id="btn-xhttp">XHttp ��û ������</button><br>
<br />
<div id="out"></div>


<hr>
name : 
	<select name= "name" id="select-pizza-name">
		<c:forEach items="${pizzas }" var="pizza">
		<option value="${pizza.p_id }">${pizza.p_name }</option>
	</c:forEach>	
</select><br>
price : <input type="text" id="input-pizza-price" placeholder="���ڸ� �����ϸ� ���� ä�����ϴ�."><br>
calories : <input type="text" id="input-pizza-calories" placeholder="���ڸ� �����ϸ� ���� ä�����ϴ�."><br>
pizza_id : <input type="text" id="input-pizza-id" placeholder="���ڸ� �����ϸ� ���� ä�����ϴ�." readonly><br>
<input  type="hidden" id ="input-pizza-name"/>
<button id="btn-xhttp-put">XHttp PUT �غ��� (Update)</button><br>
<br />
<div id="out2">���� ���� ���Դϴ�.</div>


<hr>
name : <input id="post-pizza-name" type="text" />
price :	<input id="post-pizza-price" type="text" />
calories : <input id="post-pizza-calories" type="text" />
<button id="btn-xhttp-post">XHttp POST �غ��� (Insert)</button>
<br /><br />
<div id = "out3">���� �߰� ���Դϴ�.</div>


<script src="/restful/resources/js/ajax.js"></script>

</body>
</html>