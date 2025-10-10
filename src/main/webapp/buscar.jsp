<%-- 
    Document   : buscar2
    Created on : 7 oct. 2025, 9:30:49 p. m.
    Author     : Eduardo Olea
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <style>
            .form-control {
                border: 1px solid #d0d0d0;
                box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            }

            .form-control:focus {
                border-color: #999;
                box-shadow: 0 0 6px rgba(0,0,0,0.15);
            }

            .fondo{
                backdrop-filter: blur(10px);
                background-color: rgba(255, 255, 255, 0.6);
            }

            body {
                background: linear-gradient(135deg, #F5D1B3, #F5C194); 
                min-height: 100vh;
                margin: 0;
            }
        </style>

    </style>
</head>
<body>
    <!-- Header -->
    <%@ include file="WEB-INF/componentes/header.jspf"%>
    <!-- Header -->
    <main class="container">
        <div class="row">
            <h1>Registrar mi mascota perdida</h1>   
            <div class="formulario column-md-4 col-sm-6 card shadow-lg p-4 rounded-4" >                         
                <h3>Datos de mi mascota</h3>                       
                <form action="BuscarServlet" method="post" enctype="form-data" class="p-4 rounded-4 shadow-lg bg-body-tertiary bg-opacity-75">
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
                        <option value="Pequenio">Pequeño</option>
                        <option value="Mediano">Mediano</option>
                        <option value="Grande">Grande</option>
                        <option value="MuyGrande">Muy Grande</option>
                    </select>

                    <label for="direccion" class="form-label">Dirección:</label>
                    <input type="text" id="direccion" name="direccion" class="form-control" required placeholder="Dirección donde se perdió">

                    <label for="descripcion" class="form-label">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="5" cols="50" class="form-control" placeholder="Describe brevemente a tu mascota, incluye datos importante como accesorios" required></textarea>

                    <label for="foto" class="form-label">Foto (max 50 kb):</label>
                    <input type="file" id="foto" name="foto" accept="image/*" class="form-control" >
                    <!-- Pendiente implementar envíod e imagen -->

                    <div class="col-12 text-center mt-4">
                        <button type="submit" class="btn btn-primary px-5">Registrar</button>
                    </div>
                </form>

                <c:if test="${not empty mensaje}">
                    <p>${mensaje}</p>
                </c:if>
            </div>

            <div class="container-fluid mt-4">
                <h3>Buscar Mascota</h3>
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
                                    <option value="Pequenio">Pequeño</option>
                                    <option value="Mediano">Mediano</option>
                                    <option value="Grande">Grande</option>
                                    <option value="MuyGrande">Muy Grande</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Distrito</label>
                                <input type="text" class="form-control" name="distrito" placeholder="Ej. Miraflores">
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Aplicar Filtros</button>
                        </form>

                    </aside>

                    <main class="col-md-9 col-lg-10">
                        <div class="row" id="galeria">
                            <div class="row row-cols-1 row-cols-md-3 g-4">
                                <c:forEach var="mascota" items="${listaMascotas}">
                                    <div class="col">
                                        <div class="card shadow-sm h-100">
                                            <img src="${mascota.imagen}" class="card-img-top" alt="Mascota">
                                            <div class="card-body">
                                                <h5 class="card-title">${mascota.nombre}</h5>
                                                <p class="card-text text-muted">
                                                    ${mascota.tipo} - ${mascota.tamano}<br>
                                                    Zona: ${mascota.distrito}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </main>

                </div>
            </div>
        </div>

    </main>
    <!-- Footer -->
    <%@ include file="WEB-INF/componentes/footer.jspf"%>
    <!-- Footer -->
</body>
</html>

