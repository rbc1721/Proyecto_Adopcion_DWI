<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.BonoDao" %>
<%@ page import="modelo.Bono" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<Bono> listaBonos = new BonoDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bonos | Spa Admin</title>
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
        <a class="nav-link active" href="bonos.jsp">Bonos</a>
        <a class="nav-link" href="plantillas.jsp">Plantillas</a>
        <a class="nav-link" href="campanias.jsp">Campanas</a>
        <a class="nav-link" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Mantenimiento de Bonos</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <div class="grid-2">
            <section class="card">
                <h3>Registrar Bono</h3>
                <form action="BonoServlet" method="post">
                    <input type="hidden" name="accion" value="crear">
                    <div class="form-grid">
                        <div class="field"><label>Nombre</label><input type="text" name="nombre" required></div>
                        <div class="field"><label>Descuento (%)</label><input type="number" name="porcentajeDescuento" step="0.01"></div>
                        <div class="field"><label>Vigencia</label><input type="date" name="vigencia"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                        <div class="field full"><label>Descripcion</label><textarea name="descripcion"></textarea></div>
                    </div>
                    <div class="actions"><button class="btn" type="submit">Guardar</button></div>
                </form>
            </section>

            <section class="card">
                <h3>Actualizar / Eliminar</h3>
                <form action="BonoServlet" method="post">
                    <div class="form-grid">
                        <div class="field"><label>ID Bono</label><input type="number" name="idBono" required></div>
                        <div class="field"><label>Accion</label>
                            <select name="accion">
                                <option value="actualizar">Actualizar</option>
                                <option value="eliminar">Eliminar</option>
                            </select>
                        </div>
                        <div class="field"><label>Nombre</label><input type="text" name="nombre"></div>
                        <div class="field"><label>Descuento (%)</label><input type="number" name="porcentajeDescuento" step="0.01"></div>
                        <div class="field"><label>Vigencia</label><input type="date" name="vigencia"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                        <div class="field full"><label>Descripcion</label><textarea name="descripcion"></textarea></div>
                    </div>
                    <div class="actions"><button class="btn alt" type="submit">Aplicar</button></div>
                </form>
            </section>
        </div>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Bonos (<%= listaBonos.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr><th>ID</th><th>Nombre</th><th>Descripcion</th><th>Descuento</th><th>Vigencia</th><th>Estado</th></tr>
                    </thead>
                    <tbody>
                    <% if (listaBonos.isEmpty()) { %>
                        <tr><td colspan="6">Sin registros</td></tr>
                    <% } else { for (Bono b : listaBonos) { %>
                        <tr>
                            <td><%= b.getIdBono() %></td>
                            <td><%= b.getNombre() %></td>
                            <td><%= b.getDescripcion() %></td>
                            <td><%= b.getPorcentajeDescuento() %></td>
                            <td><%= b.getVigencia() %></td>
                            <td><%= b.getEstado() %></td>
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
