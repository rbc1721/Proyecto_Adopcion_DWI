<%-- 
    Document   : index
    Created on : 12 oct. 2025, 12:27:08 p. m.
    Author     : Eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>                     
            <title>Inicio | Proyecto Adopción Huellitas</title>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <!-- Bootstrap 5 -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet"/>
            <style>
                .hero {
                    background: linear-gradient(rgba(0,0,0,0.4), rgba(0,0,0,0.4)),
                        url('https://images.unsplash.com/photo-1601758123927-1962b8c4d82e') no-repeat center center;
                    background-size: cover;
                    color: white;
                    padding: 100px 20px;
                    text-align: center;
                }

                .hero h1 {
                    font-size: 3rem;
                    font-weight: bold;
                }

                .hero p {
                    font-size: 1.3rem;
                    margin-top: 20px;
                }

                .btn-mascota {
                    margin-top: 30px;
                }
            </style>       
        </head>

        <body>

            <!-- Header -->
            <%@ include file="WEB-INF/componentes/header.jspf"%>
            <!-- Header -->

            <main>
                <!-- HERO -->
                <div class="hero">
                    <h1>¿Perdiste a tu mascota?</h1>
                    <p>Te ayudamos a encontrarla!!.<br><strong>Â¡Avisemos a todos, cada segundo cuenta!</strong></p>
                    <a href="buscar.jsp" class="btn btn-success btn-lg btn-mascota">Buscar o Reportar Mascota</a>
                </div>
            </main>

            <!-- Footer -->
            <%@ include file="WEB-INF/componentes/footer.jspf"%>
            <!-- Footer -->           

        </body>
    </html>
</f:view>
