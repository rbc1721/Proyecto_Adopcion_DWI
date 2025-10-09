<%-- 
    Document   : buscar2
    Created on : 7 oct. 2025, 9:30:49 p. m.
    Author     : Eduardo Olea
--%>

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
        </style>
    </head>
    <body>
        <header>

        </header>
        <main class="container">
            <div class="row">
                <div class="formulario column-md-4 col-sm-6 card shadow-lg p-4 rounded-4" >
                    <h1>Buscando a mi mascota</h1>            

                    <h3>Datos de mi mascota perdida</h3>                       

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

                    <c:if mensaje ="${not empty mensaje}">
                        <p>${mensaje}</p>
                    </c:if>
                </div>
                <div class="buscar column-md-4 col-sm-6">

                </div>
            </div>

        </main>
        <footer>

        </footer>
    </body>
</html>

