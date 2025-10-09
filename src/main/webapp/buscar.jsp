<%-- 
    Document   : buscar2
    Created on : 7 oct. 2025, 9:30:49 p. m.
    Author     : Eduardo Olea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Buscar a mi mascota</h1>            

        <h3>Datos de mi mascota</h3>   
        <!-- Pendiente implementar envíod e imagen -->
        Pendiente implementar envío de imagen
        <form action="BuscarServlet" method="post" enctype="form-data">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
            
            <label for="especie">Especie:</label>
            <input type="text" id="especie" name="especie" required>
            
            <label for="raza">Raza:</label>
            <input type="text" id="raza" name="raza" required>
            
            <label for="color">Color:</label>
            <input type="text" id="color" name="color" required>
            
            <label for="edad">Edad:</label>
            <input type="number" id="edad" name="edad" step="1" required>
            
            <label for="tamanio">Tamaño:</label>
            <select id="tamanio" name="tamanio" required>
                <option value="" disabled selected>Seleccione</option>
                <option value="MuyPequenio">Muy Pequeño</option>
                <option value="Pequenio">Pequeño</option>
                <option value="Mediano">Mediano</option>
                <option value="Grande">Grande</option>
                <option value="MuyGrande">Muy Grande</option>
            </select>
            
            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required placeholder="Dirección donde se perdió">
            
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="5" cols="50" placeholder="Describe brevemente a tu mascota, incluye datos importante como accesorios" required></textarea>

            <label>:</label>
            <input type="file" id="foto" name="foto" accept="image/*" >
               Pendiente implementar envío de imagen
            <button type="submit">Registrar</button>
        </form>
        
    <c:if mensaje ="${not empty mensaje}">
        <p>${mensaje}</p>
    </c:if>
    </body>
</html>

