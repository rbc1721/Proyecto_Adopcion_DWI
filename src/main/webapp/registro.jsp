<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
    <form action="ProcesarRegistroServlet" method="post" class="form-container">
        <h2 class="mb-4">Registro de Usuario</h2>

        <div class="mb-3">
            <label for="nombres" class="form-label">Nombres</label>
            <input type="text" class="form-control" id="nombres" name="nombres" required />
        </div>
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="apellidos" name="apellidos" required />
        </div>
        <div class="mb-3">
            <label for="correo" class="form-label">Correo electrónico</label>
            <input type="email" class="form-control" id="correo" name="correo" required />
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion" required />
        </div>
        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" required />
        </div>
        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <select class="form-select" id="rol" name="rol" required>
                <option value="" disabled selected>Seleccione un rol</option>
                <option value="administrador">Administrador</option>
                <option value="persona">Persona</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success w-100">Registrarse</button>
    </form>

    <p class="mt-3 text-white">
        ¿Ya tienes una cuenta? <a href="login.jsp" class="text-light">Inicia sesión aquí</a>
    </p>
</main>

<!-- Footer -->
<%@ include file="WEB-INF/componentes/footer.jspf" %>
</body>
</html>