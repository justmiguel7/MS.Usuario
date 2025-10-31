-- Creación de usuarios iniciales

--INSERT INTO usuario (
    account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled,
    contrasena,
    correo,
    dni,
    rol
) VALUES
-- Paciente
--(true, true, true, true, '$2a$10$0G5g9xq8a.Dd7Lv6jLxU5Oi6sL8vA7o2mxnT0Uok5F5Q2B9s.4dBe', 'paciente@clinicadental.com', '10000001', 'PACIENTE'),

-- Odontólogo
--(true, true, true, true, '$2a$10$0G5g9xq8a.Dd7Lv6jLxU5Oi6sL8vA7o2mxnT0Uok5F5Q2B9s.4dBe', 'odontologo@clinicadental.com', '20000002', 'ODONTOLOGO'),

-- Recepcionista
-- (true, true, true, true, '1234', 'recepcionista@clinicadental.com', '30000003', 'RECEPCIONISTA'),

-- Administrador
--(true, true, true, true, '$2a$10$0G5g9xq8a.Dd7Lv6jLxU5Oi6sL8vA7o2mxnT0Uok5F5Q2B9s.4dBe', 'admin@clinicadental.com', '40000004', 'ADMINISTRADOR');
