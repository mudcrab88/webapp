<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Сканирование</title>
</head>
<body>
<button id = "scanButton">Сканировать логи</button>
<div>Результат: <%=request.getAttribute("scanResult")%></div>
<script >
    let response = fetch('/webapp/scan', {
        method: 'POST',
        body: 'info'
    }); 
    let button = document.getElementById("scanButton");
    button.onclick = async function() {
        let response = await fetch('/webapp/scan', {
            method: 'POST',
            body: 'scan'
        });
        console.log(response);        
    }
</script>
</body>
</html>
