<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.HistorialCampaniaDao" %>
<%@ page import="modelo.HistorialCampania" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<HistorialCampania> listaHistorial = new HistorialCampaniaDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial | Spa Admin</title>
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
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link active" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Historial de Campanas</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <section class="card">
            <h3>Registrar Historial</h3>
            <form action="HistorialCampaniaServlet" method="post">
                <div class="form-grid">
                    <div class="field"><label>ID Campana</label><input type="number" name="idCampania" required></div>
                    <div class="field"><label>Cantidad clientes</label><input type="number" name="cantidadClientes" required></div>
                    <div class="field"><label>Estado envio</label><input type="text" name="estadoEnvio"></div>
                    <div class="field full"><label>Observacion</label><textarea name="observacion"></textarea></div>
                </div>
                <div class="actions"><button class="btn" type="submit">Guardar</button></div>
            </form>
        </section>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Historial (<%= listaHistorial.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr><th>ID</th><th>ID Campana</th><th>Fecha proceso</th><th>Cantidad clientes</th><th>Estado envio</th><th>Observacion</th></tr>
                    </thead>
                    <tbody>
                    <% if (listaHistorial.isEmpty()) { %>
                        <tr><td colspan="6">Sin registros</td></tr>
                    <% } else { for (HistorialCampania h : listaHistorial) { %>
                        <tr>
                            <td><%= h.getIdHistorial() %></td>
                            <td><%= h.getIdCampania() %></td>
                            <td><%= h.getFechaProceso() %></td>
                            <td><%= h.getCantidadClientes() %></td>
                            <td><%= h.getEstadoEnvio() %></td>
                            <td><%= h.getObservacion() %></td>
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
