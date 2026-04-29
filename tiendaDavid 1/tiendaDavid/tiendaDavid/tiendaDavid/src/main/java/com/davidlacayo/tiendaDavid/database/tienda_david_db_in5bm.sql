drop schema if exists tienda_david_db_in5bm;
create database if not exists tienda_david_db_in5bm;
use tienda_david_db_in5bm;

create table Clientes (
    dpi_cliente int primary key,
    nombre_cliente varchar(50),
    apellido_cliente varchar(50),
    direccion varchar(100),
    estado boolean
);

create table Usuarios (
    codigo_usuario int auto_increment primary key,
    username varchar(45),
    password varchar(45),
    email varchar(60),
    rol varchar(45),
    estado boolean
);

create table Productos (
    codigo_producto int auto_increment primary key,
    nombre_producto varchar(60),
    precio decimal(10,2),
    stock int,
    estado boolean
);

create table Ventas (
    codigo_venta int auto_increment primary key,
    fecha_venta date,
    total decimal(10,2),
    estado boolean,
    Clientes_dpi_cliente int,
    Usuarios_codigo_usuario int,

    foreign key (Clientes_dpi_cliente) references Clientes(dpi_cliente),
    foreign key (Usuarios_codigo_usuario) references Usuarios(codigo_usuario)
);

create table DetalleVenta (
    codigo_detalle_venta int auto_increment primary key,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal(10,2),
    Productos_codigo_producto int,
    Ventas_codigo_venta int,
    
    foreign key (Productos_codigo_producto) references Productos(codigo_producto),
    foreign key (Ventas_codigo_venta) references Ventas(codigo_venta)
);