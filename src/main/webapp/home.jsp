<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Spa Admin</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Prata&display=swap" rel="stylesheet">
    <link href="assets/css/spa-admin.css" rel="stylesheet">
</head>
<body>
<div class="shell">
    <aside class="sidebar">
        <div class="brand">Krasota Spa</div>
        <a class="nav-link active" href="home.jsp">Home</a>
        <a class="nav-link" href="clientes.jsp">Registro de Clientes</a>
        <a class="nav-link" href="bonos.jsp">Bonos</a>
        <a class="nav-link" href="plantillas.jsp">Gestion de Plantillas</a>
        <a class="nav-link" href="campanias.jsp">Gestion de Campanas</a>
        <a class="nav-link" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Panel Administrativo</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <section class="card">
            <h3>Gestion por modulos</h3>
            <p>Selecciona un modulo para trabajar formularios y listados de forma separada.</p>
            <div class="home-grid">
                <a class="link-card" href="clientes.jsp">
                    <h3>Clientes</h3>
                    <p>Registro, actualizacion y eliminacion de clientes.</p>
                </a>
                <a class="link-card" href="bonos.jsp">
                    <h3>Bonos</h3>
                    <p>Gestion de descuentos y vigencias promocionales.</p>
                </a>
                <a class="link-card" href="plantillas.jsp">
                    <h3>Plantillas</h3>
                    <p>Mantenimiento de contenido y canal de mensajes.</p>
                </a>
                <a class="link-card" href="campanias.jsp">
                    <h3>Campanas</h3>
                    <p>Creacion y administracion de campanas.</p>
                </a>
                <a class="link-card" href="asignaciones.jsp">
                    <h3>Asignaciones</h3>
                    <p>Relacion entre campanas y clientes.</p>
                </a>
                <a class="link-card" href="notificaciones.jsp">
                    <h3>Notificaciones</h3>
                    <p>Envio y control de estados de notificaciones.</p>
                </a>
                <a class="link-card" href="historial.jsp">
                    <h3>Historial</h3>
                    <p>Seguimiento de ejecucion de campanas.</p>
                </a>
            </div>
        </section>
    </main>
</div>
</body>
</html>
