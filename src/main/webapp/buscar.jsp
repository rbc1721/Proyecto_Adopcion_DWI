<%-- 
    Document   : buscar2
    Created on : 7 oct. 2025, 9:30:49 p. m.
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="Hello World!"/></h1>
            Buscar a mi mascota
        
        <form action="conexion.jsp" method="post">
            <label for="nombre">Nombre del Cliente:</label>
            <input type="text" id="nombre" name="nombre" required>
            <label for="ciudad">Ciudad:</label>
            <input type="text" id="ciudad" name="ciudad" required>
            <button type="submit">Probar Conexión</button>
        </form>
        </body>
    </html>
</f:view>
