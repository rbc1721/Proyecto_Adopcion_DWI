<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.NotificacionDao" %>
<%@ page import="modelo.Notificacion" %>
<%@ include file="WEB-INF/componentes/clienteGuard.jspf" %>
<%
    List<Notificacion> misNotificaciones = new NotificacionDao().listarPorCliente(usuarioLogueado.getIdUsuario());
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Promociones | Krasota Spa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet"/>
    <style>
        :root {
            --rose-50: #f8e3e2;
            --rose-100: #edd2d1;
            --rose-200: #d8b4b1;
        }

        body {
            min-height: 100vh;
            margin: 0;
            background:
                radial-gradient(circle at 10% 10%, rgba(255, 255, 255, 0.3), transparent 40%),
                radial-gradient(circle at 90% 80%, rgba(255, 255, 255, 0.2), transparent 35%),
                linear-gradient(135deg, var(--rose-50), var(--rose-100));
        }

        .wrapper {
            max-width: 1100px;
            margin: 24px auto;
            padding: 0 12px;
        }

        .hero {
            background: linear-gradient(145deg, var(--rose-100), var(--rose-200));
            border: 1px solid rgba(255, 255, 255, 0.5);
            border-radius: 18px;
            box-shadow: 0 14px 30px rgba(94, 51, 64, 0.15);
            padding: 24px;
            margin-bottom: 16px;
        }
    </style>
</head>
<body>
<%@ include file="WEB-INF/componentes/header.jspf" %>

<div class="wrapper">
    <section class="hero">
        <h2 class="mb-2">Mis promociones asignadas</h2>
        <p class="mb-0">Aqui puedes ver las promociones que el administrador configuro especialmente para ti.</p>
    </section>

    <div class="row g-3">
        <% if (misNotificaciones.isEmpty()) { %>
            <div class="col-12">
                <div class="alert alert-light border shadow-sm">
                    No tienes promociones asignadas en base de datos aun. Aqui van dos ejemplos para demo:<br/>
                    1) Masaje relajante 2x1 para clientes frecuentes.<br/>
                    2) 25% OFF en masaje con piedras calientes (vigente hasta fin de mes).
                </div>
            </div>
        <% } else { %>
            <% for (Notificacion n : misNotificaciones) { %>
                <div class="col-md-6">
                    <div class="card border-0 shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Promo asignada #<%= n.getIdNotificacion() %></h5>
                            <p class="card-text"><%= n.getMensaje() %></p>
                            <p class="mb-1"><strong>Estado:</strong> <%= n.getEstado() %></p>
                            <p class="mb-0"><strong>Fecha envio:</strong> <%= n.getFechaEnvio() %></p>
                        </div>
                    </div>
                </div>
            <% } %>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
