<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.NotificacionDao" %>
<%@ page import="modelo.Notificacion" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<Notificacion> listaNotificaciones = new NotificacionDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notificaciones | Spa Admin</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Prata&display=swap" rel="stylesheet">
    <link href="assets/css/spa-admin.css" rel="stylesheet">
</head>
<body>
<div class="shell">
    <aside class="sidebar">
        <div class="brand">Krasota Spa</div>
        <a class="nav-link" href="home.jsp">Home</a>
        <a class="nav-link" href="clientes.jsp">Clientes</a>
        <a class="nav-link" href="bonos.jsp">Bonos</a>
        <a class="nav-link" href="plantillas.jsp">Plantillas</a>
        <a class="nav-link" href="campanias.jsp">Campanas</a>
        <a class="nav-link" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link active" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Mantenimiento de Notificaciones</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <div class="grid-2">
            <section class="card">
                <h3>Registrar Notificacion</h3>
                <form action="NotificacionServlet" method="post">
                    <input type="hidden" name="accion" value="crear">
                    <div class="form-grid">
                        <div class="field"><label>ID Campana</label><input type="number" name="idCampania" required></div>
                        <div class="field"><label>ID Cliente</label><input type="number" name="idCliente" required></div>
                        <div class="field"><label>Tipo envio</label><input type="text" name="tipoEnvio"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                        <div class="field"><label>Fecha envio</label><input type="datetime-local" name="fechaEnvio"></div>
                        <div class="field full"><label>Mensaje</label><textarea name="mensaje"></textarea></div>
                    </div>
                    <div class="actions"><button class="btn" type="submit">Guardar</button></div>
                </form>
            </section>

            <section class="card">
                <h3>Cambiar estado</h3>
                <form action="NotificacionServlet" method="post">
                    <input type="hidden" name="accion" value="estado">
                    <div class="form-grid">
                        <div class="field"><label>ID Notificacion</label><input type="number" name="idNotificacion" required></div>
                        <div class="field"><label>Nuevo estado</label><input type="text" name="estado" required></div>
                    </div>
                    <div class="actions"><button class="btn alt" type="submit">Actualizar</button></div>
                </form>
            </section>
        </div>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Notificaciones (<%= listaNotificaciones.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr><th>ID</th><th>ID Campana</th><th>ID Cliente</th><th>Tipo envio</th><th>Estado</th><th>Fecha envio</th><th>Mensaje</th></tr>
                    </thead>
                    <tbody>
                    <% if (listaNotificaciones.isEmpty()) { %>
                        <tr><td colspan="7">Sin registros</td></tr>
                    <% } else { for (Notificacion n : listaNotificaciones) { %>
                        <tr>
                            <td><%= n.getIdNotificacion() %></td>
                            <td><%= n.getIdCampania() %></td>
                            <td><%= n.getIdCliente() %></td>
                            <td><%= n.getTipoEnvio() %></td>
                            <td><%= n.getEstado() %></td>
                            <td><%= n.getFechaEnvio() %></td>
                            <td><%= n.getMensaje() %></td>
                        </tr>
                    <% }} %>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>
</body>
</html>
