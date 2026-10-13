CREATE TABLE IF NOT EXISTS elecciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(500),
    fecha DATE NOT NULL,
    activa BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_elecciones_activa ON elecciones(activa);
CREATE INDEX idx_elecciones_fecha ON elecciones(fecha);

CREATE TABLE IF NOT EXISTS candidatos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    partido VARCHAR(150) NOT NULL,
    eleccion_id BIGINT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (eleccion_id) REFERENCES elecciones(id)
);

CREATE INDEX idx_candidatos_eleccion ON candidatos(eleccion_id);
CREATE INDEX idx_candidatos_activo ON candidatos(activo);

CREATE TABLE IF NOT EXISTS votos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eleccion_id BIGINT NOT NULL,
    candidato_id BIGINT NOT NULL,
    dni_ciudadano VARCHAR(20) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (eleccion_id) REFERENCES elecciones(id),
    FOREIGN KEY (candidato_id) REFERENCES candidatos(id),
    UNIQUE KEY uk_voto_eleccion_ciudadano (eleccion_id, dni_ciudadano)
);

CREATE INDEX idx_votos_eleccion ON votos(eleccion_id);
CREATE INDEX idx_votos_candidato ON votos(candidato_id);
CREATE INDEX idx_votos_dni ON votos(dni_ciudadano);