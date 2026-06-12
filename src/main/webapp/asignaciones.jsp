<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CampaniaClienteDao" %>
<%@ page import="modelo.CampaniaCliente" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<CampaniaCliente> listaAsignaciones = new CampaniaClienteDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignaciones | Spa Admin</title>
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
        <a class="nav-link active" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Asignacion Campana-Cliente</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <div class="grid-2">
            <section class="card">
                <h3>Nueva Asignacion</h3>
                <form action="CampaniaClienteServlet" method="post">
                    <input type="hidden" name="accion" value="asignar">
                    <div class="form-grid">
                        <div class="field"><label>ID Campana</label><input type="number" name="idCampania" required></div>
                        <div class="field"><label>ID Cliente</label><input type="number" name="idCliente" required></div>
                        <div class="field full"><label>Estado</label><input type="text" name="estado"></div>
                    </div>
                    <div class="actions"><button class="btn" type="submit">Asignar</button></div>
                </form>
            </section>

            <section class="card">
                <h3>Actualizar / Eliminar</h3>
                <form action="CampaniaClienteServlet" method="post">
                    <div class="form-grid">
                        <div class="field"><label>ID CampaniaCliente</label><input type="number" name="idCampaniaCliente" required></div>
                        <div class="field"><label>Accion</label>
                            <select name="accion">
                                <option value="estado">Cambiar estado</option>
                                <option value="eliminar">Eliminar</option>
                            </select>
                        </div>
                        <div class="field full"><label>Estado</label><input type="text" name="estado"></div>
                    </div>
                    <div class="actions"><button class="btn alt" type="submit">Aplicar</button></div>
                </form>
            </section>
        </div>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Asignaciones (<%= listaAsignaciones.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr><th>ID</th><th>ID Campana</th><th>ID Cliente</th><th>Fecha asignacion</th><th>Estado</th></tr>
                    </thead>
                    <tbody>
                    <% if (listaAsignaciones.isEmpty()) { %>
                        <tr><td colspan="5">Sin registros</td></tr>
                    <% } else { for (CampaniaCliente a : listaAsignaciones) { %>
                        <tr>
                            <td><%= a.getIdCampaniaCliente() %></td>
                            <td><%= a.getIdCampania() %></td>
                            <td><%= a.getIdCliente() %></td>
                            <td><%= a.getFechaAsignacion() %></td>
                            <td><%= a.getEstado() %></td>
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
