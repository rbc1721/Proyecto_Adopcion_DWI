<%-- 
    Document   : buscar2
    Created on : 7 oct. 2025, 9:30:49 p. m.
    Author     : Eduardo Olea
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
--%>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Buscando a mi mascota</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <style>
            .form-control, .form-select {
                border: 1px solid #d0d0d0;
                box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            }

            .form-control:focus {
                border-color: #999;
                box-shadow: 0 0 6px rgba(0,0,0,0.15);
            }

            input.form-control, select.form-select, textarea.form-control {
                font-size: 0.9rem;
                padding: 4px 6px;
            }

            .glass-card {
                background: rgba(255, 255, 255, 0.2);
                backdrop-filter: blur(10px);
                -webkit-backdrop-filter: blur(10px);
                border: 1px solid rgba(255, 255, 255, 0.3);
                box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            }
            /*
                .card-img-top {
                    height: 180px;
                    object-fit: cover;
                }
            */
            body{
                background-color: #ecdbd1;

            }
        </style>

    </style>
</head>
<body>
    <!-- Header -->
    <%@ include file="WEB-INF/componentes/header.jspf"%>
    <!-- Header -->
    <main class="container-fluid ">
        <div class="row">            
            <div class="w-100 px-5 py-3 text-light" style="background-image: url(assets/img/fondo.avif); background-size: cover; background-position: center">  
                <!--<h1 class="p-2 text-center">Registrar mi mascota perdida</h1>-->
                <div class="formulario col-sm-12 col-md-4 col-lg-4 card glass-card  shadow-lg p-4 rounded-4 bg-opacity-25 my-5">
                    <h3 class="text-light">Datos de mi mascota</h3>                       
                    <form action="BuscarServlet" method="post" enctype="multipart/form-data" class="p-4 rounded-4 text-light">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control border-seconday-subtle" required>

                        <label for="especie" class="form-label">Especie:</label>
                        <input type="text" id="especie" name="especie" class="form-control" required>

                        <label for="raza" class="form-label">Raza:</label>
                        <input type="text" id="raza" name="raza" class="form-control" required>

                        <label for="color" class="form-label">Color:</label>
                        <input type="text" id="color" name="color" class="form-control" required>

                        <label for="edad" class="form-label">Edad:</label>
                        <input type="number" id="edad" name="edad" min="0" step="1" class="form-control" required>

                        <label for="tamanio" class="form-label">Tamaño:</label>
                        <select id="tamanio" name="tamanio" class="form-select" required>
                            <option value="" disabled selected>Seleccione</option>
                            <option value="MuyPequenio">Muy Pequeño</option>
                            <option value="Pequeño">Pequeño</option>
                            <option value="Mediano">Mediano</option>
                            <option value="Grande">Grande</option>
                            <option value="MuyGrande">Muy Grande</option>
                        </select>

                        <label for="direccion" class="form-label">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" class="form-control" required placeholder="Dirección donde se perdió">

                        <label for="descripcion" class="form-label">Descripción:</label>
                        <textarea id="descripcion" name="descripcion" rows="4" cols="50" class="form-control" placeholder="Describe brevemente a tu mascota, incluye datos importante como accesorios" required></textarea>

                        <label for="foto" class="form-label">Foto (max 50 kb):</label>
                        <input type="file" id="foto" name="foto" accept="image/*" class="form-control" >
                        <!-- Pendiente implementar envíod e imagen -->

                        <div class="col-12 text-center mt-4">                        
                            <input type="reset" class="btn btn-secondary px-5" value="Cancelar" name="cancelar" disabled="disabled" />
                            <button type="submit" class="btn btn-primary px-5">Registrar</button>
                        </div>
                    </form>

                    <c:if test="${not empty param.msg}">
                        <p class="text-light">${param.msg}</p>
                    </c:if>
                </div>
            </div>

            <div class="container-fluid mt-4  px-5 py-3">
                <h3 class="p-2">Mascotas Encontradas</h3>
                <div class="row">                      
                    <aside class="col-md-3 col-lg-2 bg-light p-3 rounded-3 card shadow-sm fondo" >
                        <h5 class="fw-bold mb-3">Filtrar Mascotas</h5>
                        <form method="get" action="BuscarServlet">
                            <div class="mb-3">
                                <label class="form-label">Tipo</label>
                                <select class="form-select" name="tipo">
                                    <option value="">Todos</option>
                                    <option value="perro">Perro</option>
                                    <option value="gato">Gato</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Tamaño</label>
                                <select class="form-select" name="tamanio">
                                    <option value="">Cualquiera</option>
                                    <option value="MuyPequenio">Muy Pequeño</option>
                                    <option value="Pequeño">Pequeño</option>
                                    <option value="Mediano">Mediano</option>
                                    <option value="Grande">Grande</option>
                                    <option value="MuyGrande">Muy Grande</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Distrito</label>
                                <input type="text" class="form-control" name="distrito" placeholder="Ej. Miraflores">
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Buscar</button>

                        </form>

                    </aside>

                    <div class="col-md-9 col-lg-10">
                        <div class="row" id="galeria">
                            <div class="row row-cols-1 row-cols-md-3 g-4">
                                <c:forEach var="mascotaEncontrada" items="${listaReportes}">
                                    <div class="col col-lg-2 col-md-3 cool-sm6 h-80">
                                        <div class="card shadow-sm h-100">
                                            <img src="${mascotaEncontrada.foto}" class="card-img-top" alt="Mascota" loading="lazy" onerror="this.onerror=null; this.src='https://placehold.co/300x200?text=Sin+imagen';">
                  
                                            <div class="card-body">
                                                <h5 class="card-title">${mascotaEncontrada.especie}</h5>
                                                <p class="card-text text-muted">
                                                    ${mascotaEncontrada.raza} - ${mascotaEncontrada.tamanio}<br>
                                                    Zona: ${mascotaEncontrada.direccion}
                                                </p>      
                                            </div>                                    
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>

    </main>
    <!-- Footer -->
    <%@ include file="WEB-INF/componentes/footer.jspf"%>
    <!-- Footer -->
</body>
</html>

