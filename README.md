Script mysql:

DROP DATABASE IF EXISTS bd_flavors_feast;
CREATE DATABASE bd_flavors_feast;
USE bd_flavors_feast;

-- Tabla: usuario
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    username varchar(100),
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    fecha_creacion DATE NOT NULL,
    rol VARCHAR(20) DEFAULT 'cliente'
);

-- Tabla: reserva
CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    fecha_reserva DATE NOT NULL,
    hora_reserva TIME NOT NULL,
    personas INT NOT NULL,
    sucursal VARCHAR(100) NOT NULL,
    estado VARCHAR(20) DEFAULT 'pendiente',
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE categoria (
	id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla: producto
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(8,2) NOT NULL,
    imagen_url VARCHAR(255),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Tabla: pedido
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    fecha_pedido DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'pendiente',
    tipo_entrega VARCHAR(20) CHECK (tipo_entrega IN ('retiro', 'delivery')),
    direccion VARCHAR(255) NULL,
    sucursal VARCHAR(255) NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla: detalle_pedido
CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(8,2) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

INSERT INTO categoria VALUES
(1, 'Sabores Costeños'),
(2, 'De la Sierra'),
(3, 'Comida Selvática'),
(4, 'Deliciosos Postres');

INSERT INTO producto VALUES
-- Sabores Costeños (categoria_id = 1)
(1, 1, 'Causa Limeña', 'La causa limeña, un plato típico peruano con una historia rica y versátil.', 18.00, 'images-products/Causa.jpg'),
(2, 1, 'Ají de Gallina', 'Ají o crema espesa con pechuga de gallina deshilachada, lechuga, huevos y aceitunas.', 31.00, 'images-products/aji-de-gallina.jpg'),
(3, 1, 'Arroz con Mariscos', 'Deliciosa preparación que combina el sabor y la textura del arroz con una variedad de mariscos y pescados frescos.', 32.00, 'images-products/ArrozMariscos.jpg'),
(4, 1, 'Arroz Chaufa', 'Mezcla de arroz frito acompañada de verduras, tortilla de huevo y carnes.', 33.00, 'images-products/ArrozChaufa.jpg'),
(5, 1, 'Carapulcra', 'Potaje indígena, preparado con papa seca sancochada y guisada con diversas carnes como pollo, gallina y chancho.', 32.00, 'images-products/carapulcra.jpg'),
(6, 1, 'Lomo Saltado', 'Lomo fino salteado con cebolla, tomate, y culantro servido con arroz y papas fritas.', 39.00, 'images-products/lomo-saltado.jpg'),
(7, 1, 'Sudado de Pescado', 'Consiste en un aderezo de ajo, cebolla, cilantro, tomate, pescado y ají amarillo.', 33.00, 'images-products/sudado-de-pescado.jpg'),
(8, 1, 'Seco de cabrito', 'Guiso de origen árabe guisado con culantro, ají amarillo y zapallo loche.', 30.00, 'images-products/seco-de-cabrito.jpg'),

-- De la Sierra (categoria_id = 2)
(9, 2, 'Pachamanca', 'Un plato que trasciende la historia peruana y posee un exquisito sabor.', 29.00, 'images-products/pachamanca.jpg'),
(10, 2, 'Trucha frita', 'Trucha, pez de carne blanca o color salmón, tiene un delicioso sabor.', 28.00, 'images-products/trucha-frita.jpg'),
(11, 2, 'Chicharrón', 'Trozos de carne del cerdo recién beneficiado, frita en aceite de cocina bien caliente, condimentado con un poco de sal.', 28.00, 'images-products/chicharron.jpg'),
(12, 2, 'Puchero', 'Plato típico de la sierra peruana, sobre todo en la temporada de carnavales.', 24.00, 'images-products/puchero.jpg'),
(13, 2, 'Chiri Uchu', 'Plato estrella de la ciudad del Cusco. Proviene del quechua Chiri que significa «frío».', 24.00, 'images-products/Chiri_uchu.jpg'),
(14, 2, 'Olluco con Charqui', 'Preparada con charqui, principalmente de carne de oveja, en una deliciosa receta típica de las serranías.', 28.00, 'images-products/ollucos-con-charqui.jpg'),
(15, 2, 'Rocoto relleno', 'Plato típico delicioso y bueno para aclimatarse en el frío típico de la sierra del Perú.', 24.00, 'images-products/rocotorelleno.jpg'),
(16, 2, 'Lawa de Choclo', 'Plato típico ariqueño que combina el picante del rocoto con el relleno de carne molida y queso.', 26.00, 'images-products/lawa-de-choclo.jpg'),

-- Platos Selváticos (categoria_id = 3)
(17, 3, 'Juane', 'Plato típico de la selva peruana que destaca por la fusión de sabores auténticos y técnicas ancestrales.', 32.00, 'images-products/juane.jpg'),
(18, 3, 'Tacacho', 'Hecha con plátanos asados, y posteriormente machacados y amasados en bolas con manteca y trozos de cecina.', 28.00, 'images-products/tacacho.jpeg'),
(19, 3, 'Patarashca', 'Pescado fresco marinado, envuelto en hoja de bijao y cocido a la parrilla. Se acompaña con arroz, yuca frita, arvejas u otros.', 34.00, 'images-products/patarashca.jpg'),
(20, 3, 'Arroz Chaufa de Charapa', 'Un arroz chaufa oriental, mezclado con los típicos ingredientes de la selva peruana como la cecina y el chorizo amazónico.', 32.00, 'images-products/arrozchaufacharapa.jpg'),
(21, 3, 'Inchicapi de gallina', 'Una sopa a base de harina de maíz y maní, con una presa de gallina.', 30.00, 'images-products/inchicapi.jpg'),
(22, 3, 'Palometa Frita', 'Plato habitual en la Amazonía peruana que se come acompañada de patacones o tostones.', 30.00, 'images-products/palometafrita.jpg'),
(23, 3, 'Guiso de majaz', 'El majaz o paca es una especie de roedor de carne muy apreciada y de consumo común en la región neotropical.', 32.00, 'images-products/Guiso-de-majaz.jpg'),

-- Postres (categoria_id = 4)
(24, 4, 'Mazamorra Morada', 'Postre peruano espeso hecho de maíz morado y frutas.', 15.00, 'images-products/mazamorra-morada.jpg'),
(25, 4, 'Mazamorra Cochina', 'Postre peruano hecho con harina de maíz, chancaca y especias, de textura espesa y sabor dulce.', 15.00, 'images-products/mazamorra-cochina.jpg'),
(26, 4, 'Arroz con leche', 'Postre cremoso hecho con arroz, leche, azúcar y canela.', 15.00, 'images-products/arrozconleche.jpg'),
(27, 4, 'Crema volteada', 'Postre peruano similar al flan, hecho con leche, huevos y azúcar caramelizada.', 18.00, 'images-products/crema-volteada.jpg'),
(28, 4, 'Piononos de Manjar', 'Los piononos de manjar son rollos de bizcocho rellenos de manjar blanco, suaves y dulces.', 14.00, 'images-products/pionono-de-manjar.jpg'),
(29, 4, 'Pie de manzana', 'Postre de masa crujiente rellena de manzanas dulces y especiadas.', 16.00, 'images-products/pie-de-manzana.jpg'),
(30, 4, 'Leche asada', 'Postre suave y cremoso, hecho con leche, huevos y azúcar, que se hornea hasta obtener una capa dorada.', 18.00, 'images-products/leche-asada.jpg'),
(31, 4, 'Picarones', 'Postre frito, hechos con una masa de camote y calabaza, servidos con miel de chancaca.', 18.00, 'images-products/picarones.jpg'),
(32, 4, 'Turrón de Doña Pepa', 'Postre tradicional hecho con capas de galletas bañadas en miel de chancaca, decorado con confites de colores.', 18.00, 'images-products/turrondoñapepa.jpg');


select * from producto;
select * from detalle_pedido;
select * from usuario;
select * from reserva;
select * from pedido;
