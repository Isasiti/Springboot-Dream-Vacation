-- Datos iniciales para la base de datos
INSERT INTO usuarios (nombre_usuario, contrasena, email) VALUES ('juan', 'password123', 'juan@example.com');
INSERT INTO usuarios (nombre_usuario, contrasena, email) VALUES ('maria', 'pass456', 'maria@example.com');
INSERT INTO usuarios (nombre_usuario, contrasena, email) VALUES ('carlos', 'mipassword789', 'carlos@example.com');

INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('España', 'Barcelona', 'Sagrada Familia', 'Basílica icónica de Gaudí', 1);
INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('España', 'Madrid', 'Museo del Prado', 'Galería de arte con obras maestras', 1);
INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('Francia', 'París', 'Torre Eiffel', 'Monumento emblemático de París', 2);
INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('Italia', 'Roma', 'Coliseo Romano', 'Anfiteatro histórico', 2);
INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('México', 'Ciudad de México', 'Teotihuacán', 'Ruinas de la pirámide del sol', 3);
INSERT INTO vacaciones (pais, ciudad, lugar_turistico, descripcion, usuario_id) VALUES 
('Perú', 'Cusco', 'Machu Picchu', 'Ciudadela Inca antigua', 3);
