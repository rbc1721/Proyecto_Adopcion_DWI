<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8"/>
        <title>Cuenta - Iniciar sesión</title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="icon" type="image/png" href="assets/img/Logo-KrasotaSPA.png"/>
        <!-- Incluye Bootstrap desde CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet"/>
        <link href="assets/css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="fondo-login">

        <!-- Header -->
        <%@ include file="WEB-INF/componentes/header.jspf" %>

        <!-- Contenido principal -->
        <main class="container py-5">
            <div class="form-container">
                <form action="LoginServlet" method="post"> 
                    <h2 class="mb-4">Iniciar Sesión</h2>
                    <div class="mb-3">
                        <label for="email" class="form-label">Correo electrónico</label>
                        <input type="email" id="email" name="email" class="form-control" required />
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" id="password" name="password" class="form-control" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Entrar</button>
                </form>
                <c:if test="${param.error == '1'}">
                    <div class="alert alert-danger mt-3" role="alert">
                        Usuario o contraseña incorrectos.
                    </div>
                </c:if>
                <div class="alert alert-secondary mt-3" role="alert">
                    <strong>Credenciales de prueba:</strong><br/>
                    Admin: admin@spa.com / 123456<br/>
                    Cliente: cliente@spa.com / 123456
                </div>
                <p class="mt-3">
                    ¿No tienes una cuenta? <a href="registro.jsp">Regístrate aquí</a>
                </p>
            </div>
        </main>

        <div class="fixed-bottom">
            <!-- Footer -->
        <%@ include file="WEB-INF/componentes/footer.jspf" %>
        </div>

    </body>
</html>