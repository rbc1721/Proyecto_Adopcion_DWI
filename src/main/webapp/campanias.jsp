<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CampaniaDao" %>
<%@ page import="modelo.Campania" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<Campania> listaCampanias = new CampaniaDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campanas | Spa Admin</title>
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
        <a class="nav-link active" href="campanias.jsp">Campanas</a>
        <a class="nav-link" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Mantenimiento de Campanas</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <div class="grid-2">
            <section class="card">
                <h3>Registrar Campana</h3>
                <form action="CampaniaServlet" method="post">
                    <input type="hidden" name="accion" value="crear">
                    <div class="form-grid">
                        <div class="field"><label>Nombre</label><input type="text" name="nombre" required></div>
                        <div class="field"><label>Tipo notificacion</label><input type="text" name="tipoNotificacion"></div>
                        <div class="field"><label>Fecha inicio</label><input type="date" name="fechaInicio"></div>
                        <div class="field"><label>Fecha fin</label><input type="date" name="fechaFin"></div>
                        <div class="field"><label>Fecha programada</label><input type="datetime-local" name="fechaProgramada"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                        <div class="field"><label>ID Bono</label><input type="number" name="idBono"></div>
                        <div class="field"><label>ID Plantilla</label><input type="number" name="idPlantilla"></div>
                        <div class="field full"><label>Descripcion</label><textarea name="descripcion"></textarea></div>
                    </div>
                    <div class="actions"><button class="btn" type="submit">Guardar</button></div>
                </form>
            </section>

            <section class="card">
                <h3>Actualizar / Eliminar</h3>
                <form action="CampaniaServlet" method="post">
                    <div class="form-grid">
                        <div class="field"><label>ID Campana</label><input type="number" name="idCampania" required></div>
                        <div class="field"><label>Accion</label>
                            <select name="accion">
                                <option value="actualizar">Actualizar</option>
                                <option value="eliminar">Eliminar</option>
                            </select>
                        </div>
                        <div class="field"><label>Nombre</label><input type="text" name="nombre"></div>
                        <div class="field"><label>Tipo notificacion</label><input type="text" name="tipoNotificacion"></div>
                        <div class="field"><label>Fecha inicio</label><input type="date" name="fechaInicio"></div>
                        <div class="field"><label>Fecha fin</label><input type="date" name="fechaFin"></div>
                        <div class="field"><label>Fecha programada</label><input type="datetime-local" name="fechaProgramada"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                        <div class="field"><label>ID Bono</label><input type="number" name="idBono"></div>
                        <div class="field"><label>ID Plantilla</label><input type="number" name="idPlantilla"></div>
                        <div class="field full"><label>Descripcion</label><textarea name="descripcion"></textarea></div>
                    </div>
                    <div class="actions"><button class="btn alt" type="submit">Aplicar</button></div>
                </form>
            </section>
        </div>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Campanas (<%= listaCampanias.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr><th>ID</th><th>Nombre</th><th>Inicio</th><th>Fin</th><th>Tipo</th><th>Estado</th><th>ID Bono</th><th>ID Plantilla</th></tr>
                    </thead>
                    <tbody>
                    <% if (listaCampanias.isEmpty()) { %>
                        <tr><td colspan="8">Sin registros</td></tr>
                    <% } else { for (Campania c : listaCampanias) { %>
                        <tr>
                            <td><%= c.getIdCampania() %></td>
                            <td><%= c.getNombre() %></td>
                            <td><%= c.getFechaInicio() %></td>
                            <td><%= c.getFechaFin() %></td>
                            <td><%= c.getTipoNotificacion() %></td>
                            <td><%= c.getEstado() %></td>
                            <td><%= c.getIdBono() %></td>
                            <td><%= c.getIdPlantilla() %></td>
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
