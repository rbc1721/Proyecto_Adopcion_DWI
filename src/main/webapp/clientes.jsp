<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ClienteDao" %>
<%@ page import="modelo.Cliente" %>
<%@ include file="WEB-INF/componentes/adminGuard.jspf" %>
<%
    List<Cliente> listaClientes = new ClienteDao().listar();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes | Spa Admin</title>
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
        <a class="nav-link active" href="clientes.jsp">Clientes</a>
        <a class="nav-link" href="bonos.jsp">Bonos</a>
        <a class="nav-link" href="plantillas.jsp">Plantillas</a>
        <a class="nav-link" href="campanias.jsp">Campanas</a>
        <a class="nav-link" href="asignaciones.jsp">Asignaciones</a>
        <a class="nav-link" href="notificaciones.jsp">Notificaciones</a>
        <a class="nav-link" href="historial.jsp">Historial</a>
        <a class="nav-link" href="LogoutServlet">Cerrar sesion</a>
    </aside>

    <main class="main">
        <div class="top">
            <h1>Mantenimiento de Clientes</h1>
            <div class="user-chip">Hola, <%= usuarioLogueado.getPersona().getNombres() %></div>
        </div>

        <div class="grid-2">
            <section class="card">
                <h3>Registrar Cliente</h3>
                <form action="ClienteServlet" method="post">
                    <input type="hidden" name="accion" value="crear">
                    <div class="form-grid">
                        <div class="field"><label>DNI</label><input type="text" name="dni" maxlength="8" required></div>
                        <div class="field"><label>Correo</label><input type="email" name="correo" required></div>
                        <div class="field"><label>Nombre</label><input type="text" name="nombre" required></div>
                        <div class="field"><label>Apellido</label><input type="text" name="apellido" required></div>
                        <div class="field"><label>Telefono</label><input type="text" name="telefono"></div>
                        <div class="field"><label>Fecha registro</label><input type="date" name="fechaRegistro"></div>
                        <div class="field"><label>Segmento</label><input type="text" name="segmento"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                    </div>
                    <div class="actions"><button class="btn" type="submit">Guardar</button></div>
                </form>
            </section>

            <section class="card">
                <h3>Actualizar / Eliminar</h3>
                <form action="ClienteServlet" method="post">
                    <div class="form-grid">
                        <div class="field"><label>ID Cliente</label><input type="number" name="idCliente" required></div>
                        <div class="field"><label>Accion</label>
                            <select name="accion">
                                <option value="actualizar">Actualizar</option>
                                <option value="eliminar">Eliminar</option>
                            </select>
                        </div>
                        <div class="field"><label>DNI</label><input type="text" name="dni"></div>
                        <div class="field"><label>Correo</label><input type="email" name="correo"></div>
                        <div class="field"><label>Nombre</label><input type="text" name="nombre"></div>
                        <div class="field"><label>Apellido</label><input type="text" name="apellido"></div>
                        <div class="field"><label>Telefono</label><input type="text" name="telefono"></div>
                        <div class="field"><label>Fecha registro</label><input type="date" name="fechaRegistro"></div>
                        <div class="field"><label>Segmento</label><input type="text" name="segmento"></div>
                        <div class="field"><label>Estado</label><input type="text" name="estado"></div>
                    </div>
                    <div class="actions"><button class="btn alt" type="submit">Aplicar</button></div>
                </form>
                <% if (request.getParameter("msg") != null) { %>
                    <div class="note">Operacion: <%= request.getParameter("msg") %></div>
                <% } %>
            </section>
        </div>

        <section class="card" style="margin-top:12px;">
            <h3>Listado de Clientes (<%= listaClientes.size() %>)</h3>
            <div class="table-wrap">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th><th>DNI</th><th>Nombre</th><th>Apellido</th><th>Correo</th><th>Telefono</th><th>Segmento</th><th>Estado</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (listaClientes.isEmpty()) { %>
                        <tr><td colspan="8">Sin registros</td></tr>
                    <% } else { for (Cliente c : listaClientes) { %>
                        <tr>
                            <td><%= c.getIdCliente() %></td>
                            <td><%= c.getDni() %></td>
                            <td><%= c.getNombre() %></td>
                            <td><%= c.getApellido() %></td>
                            <td><%= c.getCorreo() %></td>
                            <td><%= c.getTelefono() %></td>
                            <td><%= c.getSegmento() %></td>
                            <td><%= c.getEstado() %></td>
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
