<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <title>Cuenta - Registro</title>
    <!-- Bootstrap desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/estilos.css" rel="stylesheet" type="text/css"/>
</head>
<body class="fondo-registro">

<!-- Header -->
<%@ include file="WEB-INF/componentes/header.jspf" %>

<!-- Contenido principal -->
<main class="container py-5">
    <form action="RegistrarUsuarioServlet" method="post" class="form-container" autocomplete="off">
        <h2 class="mb-4">Registro de Usuario</h2>
        <c:if test="${param.msg == 'registro_ok'}">
        <div class="alert alert-success" role="alert">
        ¡Registro exitoso! Ahora puedes <a href="login.jsp" class="alert-link">iniciar sesión</a>.
        </div>
        </c:if>
        <c:if test="${param.msg == 'error'}">
            <div class="alert alert-danger" role="alert">
                Ocurrió un error durante el registro. Intenta nuevamente.
            </div>
        </c:if>
        <c:if test="${param.msg == 'correo_existe'}">
            <div class="alert alert-warning" role="alert">
                El correo [<c:out value="${param.correo}" />] ya está registrado. Inicia sesión o usa otro correo.
            </div>
        </c:if>
        <div class="mb-3">
            <label for="nombres" class="form-label">Nombres</label>
            <input type="text" class="form-control" id="nombres" name="nombres" required />
        </div>
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="apellidos" name="apellidos" required />
        </div>     
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion" required />
        </div>
        <div class="mb-3">
            <label for="correo" class="form-label">Correo electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required autocomplete="off" />
        </div>
        <div class="mb-3">
            <label for="contrasenia" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="contrasenia" name="contrasenia" required autocomplete="new-password" />
        </div>
        <div class="mb-3">
            <label class="form-label">Rol asignado</label>
            <input type="text" class="form-control" value="Cliente" readonly />
        </div>
        <button type="submit" class="btn btn-success w-100">Registrarse</button>
    </form>

    <p class="mt-3 text-white">
        ¿Ya tienes una cuenta? <a href="login.jsp" class="text-light">Inicia sesión aquí</a>
    </p>
</main>

<c:if test="${param.msg == 'correo_existe'}">
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var inputCorreo = document.getElementById('correo');
            var inputContrasenia = document.getElementById('contrasenia');
            if (inputCorreo) {
                inputCorreo.value = '';
                inputCorreo.focus();
            }
            if (inputContrasenia) {
                inputContrasenia.value = '';
            }
        });
    </script>
</c:if>

<!-- Footer -->
<%@ include file="WEB-INF/componentes/footer.jspf" %>
</body>
</html>