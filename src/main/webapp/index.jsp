<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<a href="${pageContext.request.contextPath}/consumeApi">Cargar datos de las APIs</a>
<head>
    <title>API Results</title>
</head>
<body>
<h1>TRM para Colombia</h1>
<p>${trmData}</p>

<h1>Clima Actual</h1>
<p>${weatherData}</p>

<h1>Personajes de Rick and Morty</h1>
<p>${rickMortyData}</p>

</body>
</html>
