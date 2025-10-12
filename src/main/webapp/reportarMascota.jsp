<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reportar Mascota Perdida</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 40px auto;
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
        }
        input[type="text"], textarea, select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            margin-top: 5px;
        }
        input[type="file"] {
            margin-top: 10px;
        }
        input[type="submit"] {
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 6px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🐾 Reportar Mascota Perdida</h1>
        <form action="MascotaServlet" method="post" enctype="multipart/form-data">
            <label>Nombre (si lo sabe):</label>
            <input type="text" name="nombre">

            <label>Tipo de Mascota:</label>
            <select name="especie">
                <option value="Perro">Perro</option>
                <option value="Gato">Gato</option>
                <option value="Otro">Otro</option>
            </select>

            <label>Color:</label>
            <input type="text" name="color">

            <label>Descripción / Señales particulares:</label>
            <textarea name="descripcion" rows="3"></textarea>

            <label>Ubicación (Calle o referencia del lugar):</label>
            <input type="text" name="ubicacion">

            <label>Foto de la mascota:</label>
            <input type="file" name="foto" accept="image/*">

            <label>Lugar donde se dejará (refugio, veterinaria, etc.):</label>
            <input type="text" name="lugar">

            <input type="submit" value="Registrar Mascota Perdida">
        </form>
    </div>
</body>
</html>