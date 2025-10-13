<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Cuenta - Iniciar sesión</title>
    <!-- Incluye Bootstrap desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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
        <p class="mt-3">
            ¿No tienes una cuenta? <a href="registro.jsp">Regístrate aquí</a>
        </p>
    </div>
</main>

<!-- Footer -->
<%@ include file="WEB-INF/componentes/footer.jspf" %>

</body>
</html>