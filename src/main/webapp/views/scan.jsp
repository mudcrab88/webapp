<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<title>Сканирование</title>
</head>
<body>
<button id = "scanButton">Сканировать логи</button>
<div id = "scanResult"><%=request.getAttribute("scanResult")%></div>
<script >
    let response = fetch('/webapp/scan', {
        method: 'POST',
        body: 'info'
    }); 
    let scanButton = document.getElementById("scanButton");
    let resultDiv = document.getElementById("scanResult");
    scanButton.onclick = async function() {
        let timerId = setInterval(() => console.log('tick'), 1000);
        let response = await fetch('/webapp/scan', {
            method: 'POST',
            body: 'scan'
        }).then(function (response) {
            console.log('timerId');
            return response.json();
        }).then(function (data) {
            console.log('data', data);
            resultDiv.innerHTML = data;
        }).catch(function (error) {
            console.log('error', error);
        });
        clearInterval(timerId);
    }
</script>
</body>
</html>
