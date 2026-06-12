CREATE TABLE personas (
    idPersona INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    correo VARCHAR(100) NOT NULL UNIQUE,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE usuarios (
    idUsuario INT PRIMARY KEY,
    contrasenia VARCHAR(255) NOT NULL,
    idPersona INT NOT NULL,
    CONSTRAINT FK_usuarios_personas FOREIGN KEY (idPersona) REFERENCES personas(idPersona)
);

CREATE TABLE Cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(8) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    fechaRegistro DATE,
    segmento VARCHAR(50),
    estado VARCHAR(20)
);

CREATE TABLE Bono (
    idBono INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    porcentajeDescuento DECIMAL(5,2),
    vigencia DATE,
    estado VARCHAR(20)
);

CREATE TABLE Plantilla (
    idPlantilla INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    asunto VARCHAR(150),
    contenido LONGTEXT,
    tipoCanal VARCHAR(30),
    estado VARCHAR(20)
);

CREATE TABLE Campania (
    idCampania INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300),
    fechaInicio DATE,
    fechaFin DATE,
    fechaProgramada DATETIME,
    tipoNotificacion VARCHAR(30),
    estado VARCHAR(20),
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    idBono INT NULL,
    idPlantilla INT NULL,
    CONSTRAINT FK_Campania_Bono FOREIGN KEY (idBono) REFERENCES Bono(idBono),
    CONSTRAINT FK_Campania_Plantilla FOREIGN KEY (idPlantilla) REFERENCES Plantilla(idPlantilla)
);

CREATE TABLE CampaniaCliente (
    idCampaniaCliente INT PRIMARY KEY AUTO_INCREMENT,
    idCampania INT NOT NULL,
    idCliente INT NOT NULL,
    fechaAsignacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20),
    CONSTRAINT FK_CampaniaCliente_Campania FOREIGN KEY (idCampania) REFERENCES Campania(idCampania),
    CONSTRAINT FK_CampaniaCliente_Cliente FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE Notificacion (
    idNotificacion INT PRIMARY KEY AUTO_INCREMENT,
    mensaje VARCHAR(300),
    fechaEnvio DATETIME,
    tipoEnvio VARCHAR(30),
    estado VARCHAR(20),
    idCampania INT NOT NULL,
    idCliente INT NOT NULL,
    CONSTRAINT FK_Notificacion_Campania FOREIGN KEY (idCampania) REFERENCES Campania(idCampania),
    CONSTRAINT FK_Notificacion_Cliente FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE HistorialCampania (
    idHistorial INT PRIMARY KEY AUTO_INCREMENT,
    fechaProceso DATETIME DEFAULT CURRENT_TIMESTAMP,
    cantidadClientes INT,
    estadoEnvio VARCHAR(30),
    observacion VARCHAR(300),
    idCampania INT NOT NULL,
    CONSTRAINT FK_HistorialCampania_Campania FOREIGN KEY (idCampania) REFERENCES Campania(idCampania)
);

INSERT INTO personas (nombres, apellidos, direccion, correo, rol)
VALUES ('Admin', 'Spa', 'Oficina central', 'admin@spa.com', 'ADMIN')
ON DUPLICATE KEY UPDATE
    nombres = VALUES(nombres),
    apellidos = VALUES(apellidos),
    direccion = VALUES(direccion),
    rol = VALUES(rol);

INSERT INTO usuarios (idUsuario, contrasenia, idPersona)
SELECT 9999, '123456', p.idPersona
FROM personas p
WHERE p.correo = 'admin@spa.com'
ON DUPLICATE KEY UPDATE
    contrasenia = VALUES(contrasenia),
    idPersona = VALUES(idPersona);

INSERT INTO personas (nombres, apellidos, direccion, correo, rol)
VALUES ('Cliente', 'Demo', 'Direccion demo', 'cliente@spa.com', 'CLIENTE')
ON DUPLICATE KEY UPDATE
    nombres = VALUES(nombres),
    apellidos = VALUES(apellidos),
    direccion = VALUES(direccion),
    rol = VALUES(rol);

INSERT INTO usuarios (idUsuario, contrasenia, idPersona)
SELECT 2001, '123456', p.idPersona
FROM personas p
WHERE p.correo = 'cliente@spa.com'
ON DUPLICATE KEY UPDATE
    contrasenia = VALUES(contrasenia),
    idPersona = VALUES(idPersona);
