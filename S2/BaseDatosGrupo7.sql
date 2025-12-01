set sql_safe_updates = 0;
create database polleria;
use polleria;
------------------------------------------------------------------------
-- PRODUCTOS:
create table productos (
    id varchar(5),
    categoria varchar(100),
    nombre varchar(200),
    precio decimal(10,2),
    stock int
);

insert into productos (id, categoria, nombre, precio, stock) values
('tA1','Pollos', 'Pollo a la brasa entero', 55.00, 15),
('tA2','Bebidas', 'Inca Kola 1L', 8.00, 40),
('tA3','Complementos', 'Ensalada fresca', 5.50, 20);

select * from productos;

-- Listar Productos
create procedure sp_ListarProductos()
select*from productos;

call sp_ListarProductos();

-- Insertar Productos
create procedure sp_InsertarProductos(
p_id varchar(5),
cate varchar(100),
nom varchar(50),
pre real,
s int
)
insert into productos (id, categoria, nombre, precio, stock) values(p_id, cate, nom, pre, s);

call sp_InsertarProductos('tA4','Helados', 'PesiDuri', 12.00, 15);
call sp_ListarProductos();

-- Eliminar Productos
create procedure sp_EliminarProductos(
  p_id varchar(5)
)
delete from productos where p_id=id;

call sp_EliminarProductos('tA1');
call sp_ListarProductos();

-- Editar Productos 
create procedure sp_EditarProductos(
  p_id varchar(5),
  nom varchar(50),
  pre real,
  s int
)
update productos set nombre=nom, precio=pre, stock=s
where id = p_id;

call sp_EditarProductos('tA2','Costal de papas',4.20,35);
call sp_ListarProductos();


-- Consultar Categoria
create procedure sp_ConsultarCategoria(
cate char(50)
)
select * from productos where categoria=cate;

call sp_ConsultarCategoria('Bebidas');

-- Editar Categoria
create procedure sp_EditarCategoria(
  p_id varchar(5),
  cate char(50)
)
update productos set categoria=cate
where id = p_id;

call sp_EditarCategoria('tA2','Abarrotes');
call sp_ListarProductos();
----------------------------------------------------------
-- Empleados:
create table empleados (
  NRC varchar(8),
  nombre varchar(200),
  cargo varchar(100),
  salario decimal(10,2),
  telefono varchar(50)
);

insert into empleados (NRC, nombre, cargo, salario, telefono) values
('N1','N1', 'Admin', 1500.50, '976543210'),
('N2','N2', 'Gerente', 1000.00, '965432109'),
('N3','N3', 'Supervisor', 1200.00, '987654321'),
('N4','N4', 'Almacenero', 1500.50, '976543210'),
('N5','N5', 'Cajero', 1000.00, '965432109'),
('N6','N6', 'Recursos Humanos', 1000.00, '965432109'),
('N7','N7', 'Auditor', 1000.00, '965432109');
select * from empleados;

-- Listar Empleados
create procedure sp_ListarEmpleados()
select * from empleados;

-- Ejemplo:
call sp_ListarEmpleados();

-- Insertar Empleados
create procedure sp_InsertarEmpleados(
  p_NRC varchar(8),
  p_cargo varchar(100),
  p_nombre varchar(200),
  p_salario decimal(10,2),
  p_telefono varchar(50)
)
insert into empleados (NRC, cargo, nombre, salario, telefono) values(p_NRC, p_cargo, p_nombre, p_salario, p_telefono);

-- Ejemplo:
call sp_InsertarEmpleados('N0051254','Administrador', 'Ana García', 2000.00, '954321098');
call sp_ListarEmpleados();

-- Eliminar:
create procedure sp_EliminarEmpleados(
  p_NRC varchar(8)
)
delete from empleados where p_NRC=NRC;

call sp_EliminarEmpleados('N0078421');
call sp_ListarEmpleados();

-- Editar Empleado 
create procedure sp_EditarEmpleados(
  p_NRC varchar(8),
  p_nombre varchar(200),
  p_cargo varchar(100),
  p_salario decimal(10,2),
  p_telefono varchar(50)
)
update empleados set nombre = p_nombre, cargo = p_cargo, salario = p_salario, telefono = p_telefono 
where NRC = p_NRC;

-- Ejemplo:
call sp_EditarEmpleados('N0051254', 'María López', 'Chef', 1600.00, '976543210');
call sp_ListarEmpleados();

-- Consultar empleados por cargo 
create procedure sp_ConsultarEmpleadoPorCargo(
  p_cargo varchar(100)
)
select * from empleados where cargo = p_cargo;

-- Ejemplo:
call sp_ConsultarEmpleadoPorCargo('Chef');

-- Editar cargo de un empleado (por NRC) 
create procedure sp_EditarCargoEmpleado(
  p_NRC varchar(8),
  p_nuevo_cargo varchar(100),
  p_nuevo_salario decimal(10,2)
)
update empleados set cargo = p_nuevo_cargo, salario = p_nuevo_salario
where NRC = p_NRC;

-- Ejemplo:
call sp_EditarCargoEmpleado('N0051254', 'Mensajero', '1092');
call sp_ListarEmpleados();
---------------------------------------------------------
-- Pedidos:
create table pedidos (
    codigo varchar(8),
    empleado varchar(200),
    producto varchar(200),
    cantidad int,
    total decimal(10,2),
    metodo_pago varchar(50)
);

-- Datos de ejemplo
insert into pedidos (codigo, empleado, producto, cantidad, total, metodo_pago) values
("p1",'Carlos Ramos', 'Pollo a la brasa entero', 1, 55.00, 'Efectivo'),
("p2",'Lucía Torres', 'Inca Kola 1L', 2, 16.00, 'Tarjeta'),
("p3",'Carlos Ramos', 'Papas fritas grandes', 3, 27.00, 'Yape');

select * from pedidos;

-- Listar pedidos
create procedure sp_ListarPedidos()
select * from pedidos;

-- Ejemplo:
call sp_ListarPedidos();

-- Insertar pedido si el producto existe en la tabla de productos
-- total = precio * cantidad
create procedure sp_InsertarPedido(
  p_codigo varchar(8),
  p_empleado varchar(200),
  p_producto varchar(200),
  p_cantidad int,
  p_metodo_pago varchar(50)
)
insert into pedidos (codigo, empleado, producto, cantidad, total, metodo_pago) select p_codigo, p_empleado, prod.nombre, p_cantidad, prod.precio * p_cantidad, p_metodo_pago from productos prod
where prod.nombre = p_producto;

-- Ejemplo:
-- Inserta porque el producto existe
call sp_InsertarPedido('e5','Ana', 'Costal de papas', 3, 'Efectivo');
call sp_ListarPedidos();
-- No inserta porque no existe
call sp_InsertarPedido('e6','Pedro', 'Chicharronsito', 2, 'Efectivo');
call sp_ListarPedidos();

-- Editar pedido si existe previamente
create procedure sp_EditarPedido(
  p_codigo varchar(8),
  p_empleado varchar(200),
  p_producto varchar(200),
  p_cantidad int,
  p_metodo_pago varchar(50)
)
update pedidos p join productos prod on prod.nombre = p_producto set p.empleado = p_empleado, p.producto = prod.nombre,
    p.cantidad = p_cantidad, p.total = prod.precio * p_cantidad, p.metodo_pago = p_metodo_pago
where p.codigo = p_codigo;

-- Ejemplo:
call sp_EditarPedido('p1', 'Carlos Ramos', 'PesiDuri', 2, 'Tarjeta');
call sp_ListarPedidos();

-- Anular pedido
create procedure sp_AnularPedido(
  p_codigo varchar(8)
)
delete from pedidos where p_codigo=codigo;

-- Ejemplo:
call sp_AnularPedido('e2');
call sp_ListarPedidos();

-- Consultar pedido por empleado
create procedure sp_ConsultarPedidoE(
  p_empleado varchar(200)
)
select * from pedidos where empleado = p_empleado;

-- Ejemplo:
call sp_ConsultarPedidoE('Carlos Ramos');

-- Consultar pedido por producto
create procedure sp_ConsultarPedidoP(
  p_producto varchar(200)
)
select * from pedidos where producto = p_producto;

-- Ejemplo:
call sp_ConsultarPedidoP('PesiDuri');

-----------------------------------------------------------
-- Tablitas:
select * from productos;
select * from pedidos;
select * from empleados;