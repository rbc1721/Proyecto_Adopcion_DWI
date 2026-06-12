<%-- 
    Document   : index
    Created on : 10/06/2026
    Author     : Lizbeth Huaman Ventura -1420446
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>                     
            <title>Inicio | Krasota Spa</title>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="icon" type="image/png" href="assets/img/Logo-KrasotaSPA.png"/>
            <!-- Bootstrap 5 -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet"/>
            <link href="assets/css/estilos.css" rel="stylesheet" type="text/css"/>
            <style>
                :root {
                    --rose-50: #f8e3e2;
                    --rose-100: #edd2d1;
                    --rose-200: #d8b4b1;
                    --ink-900: #2f2728;
                    --ink-700: #5a4e50;
                }

                body {
                    margin: 0;
                    min-height: 100vh;
                    background:
                        radial-gradient(circle at 10% 10%, rgba(255, 255, 255, 0.3), transparent 40%),
                        radial-gradient(circle at 90% 80%, rgba(255, 255, 255, 0.2), transparent 35%),
                        linear-gradient(135deg, var(--rose-50), var(--rose-100));
                    color: var(--ink-900);
                }

                .hero {
                    background: linear-gradient(145deg, var(--rose-100), var(--rose-200));
                    border: 1px solid rgba(255, 255, 255, 0.5);
                    border-radius: 18px;
                    box-shadow: 0 14px 30px rgba(94, 51, 64, 0.15);
                    color: var(--ink-900);
                    padding: 90px 20px;
                    margin: 28px auto;
                    max-width: 1120px;
                    text-align: center;
                }

                .hero h1 {
                    font-size: 3rem;
                    font-weight: bold;
                }

                .hero p {
                    font-size: 1.3rem;
                    margin-top: 20px;
                    color: var(--ink-700);
                }

                .btn-spa {
                    margin-top: 30px;
                    background: #8f686d;
                    border: none;
                }

                .btn-spa:hover {
                    background: #78575c;
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
                    <h1>Krasota Spa</h1>
                    <p>Bienvenida a una experiencia de bienestar premium.<br><strong>Descubre promociones exclusivas en masajes y tratamientos relajantes.</strong></p>
                    <a href="home.jsp" class="btn btn-success btn-lg btn-spa">Ver promociones del spa</a>
                </div>

                <section id="promociones" class="container pb-5">
                    <div class="row g-4">
                        <div class="col-md-4">
                            <div class="card h-100 shadow-sm border-0">
                                <div class="card-body">
                                    <h5 class="card-title">Masaje Relajante 30% OFF</h5>
                                    <p class="card-text">Incluye aromaterapia y musica terapeutica. Valido de lunes a jueves.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card h-100 shadow-sm border-0">
                                <div class="card-body">
                                    <h5 class="card-title">Pack Antiestres</h5>
                                    <p class="card-text">Masaje de espalda + reflexologia podal con precio especial para nuevos clientes.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card h-100 shadow-sm border-0">
                                <div class="card-body">
                                    <h5 class="card-title">Ritual Premium de Bienestar</h5>
                                    <p class="card-text">Masaje de cuerpo completo con piedras calientes y descuento del 20% en reservas online.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>

            <!-- Footer -->
            <%@ include file="WEB-INF/componentes/footer.jspf"%>
            <!-- Footer -->           

        </body>
    </html>
</f:view>
