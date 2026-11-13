# language: es
Característica: Persistencia de Pedidos en DynamoDB
  Como automatizador de pruebas
  Quiero validar las operaciones de escritura y lectura de pedidos en DynamoDB
  Para asegurar la integridad de los datos del sistema de gestión de pedidos

  # Fondo: configuración del entorno de pruebas
  Antecedentes:
    Dado que el sistema de gestión de pedidos está configurado para usar DynamoDB
    Y la tabla de pedidos está disponible en el endpoint configurado

  # Escenario 1: Escritura exitosa de un nuevo pedido
  @escritura
  @orden-exitosa
  Escenario: Guardar un nuevo pedido en DynamoDB
    Cuando creo un pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-001   | CUST-001   | PENDING   | PROD-001  | 5        | 150.00   |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-001"
    Y los datos del pedido recuperado deben coincidir con los datos originales

  # Escenario 2: Escritura de pedido con múltiples items
  @escritura
  @orden-multiple-items
  Escenario: Guardar un pedido con múltiples productos
    Cuando creo un pedido con múltiples items:
      | orderId   | customerId | status    |
      | ORD-002   | CUST-002   | CONFIRMED |
    Y el pedido contiene los siguientes productos:
      | productId | quantity | price    |
      | PROD-001  | 3        | 100.00   |
      | PROD-002  | 2        | 250.50   |
      | PROD-003  | 1        | 75.25    |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-002"
    Y la cantidad total de items debe ser 3

  # Escenario 3: Lectura de pedido existente
  @lectura
  @pedido-existente
  Escenario: Recuperar un pedido existente de DynamoDB
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-003   | CUST-003   | SHIPPED   | PROD-003  | 10       | 50.00    |
    Cuando busco el pedido por orderId "ORD-003"
    Entonces debo obtener los datos completos del pedido
    Y el customerId debe ser "CUST-003"
    Y el status debe ser "SHIPPED"

  # Escenario 4: Actualización de estado de pedido
  @actualizacion
  @cambio-estado
  Escenario: Actualizar el estado de un pedido existente
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    |
      | ORD-004   | CUST-004   | PENDING   |
    Cuando actualizo el estado del pedido "ORD-004" a "PROCESSING"
    Y busco el pedido por orderId "ORD-004"
    Entonces el estado del pedido debe ser "PROCESSING"
    Y los demás datos deben permanecer sin cambios

  # Escenario 5: Eliminación de pedido
  @eliminacion
  @borrar-pedido
  Escenario: Eliminar un pedido de DynamoDB
    Dado que existe un pedido guardado en DynamoDB:
      | orderId   | customerId | status    |
      | ORD-005   | CUST-005   | CANCELLED |
    Cuando elimino el pedido con orderId "ORD-005"
    Y busco el pedido por orderId "ORD-005"
    Entonces no debo obtener ningún resultado
    Y el pedido no debe existir en la tabla

  # Escenario 6: Flujo completo de persistencia
  @flujo-completo
  @integracion
  Escenario: Validar el flujo completo de persistencia de pedidos
    Cuando creo un nuevo pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-006   | CUST-006   | PENDING   | PROD-006  | 8        | 200.00   |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Cuando actualizo el estado del pedido "ORD-006" a "CONFIRMED"
    Y busco el pedido por orderId "ORD-006"
    Entonces el estado del pedido debe ser "CONFIRMED"
    Cuando actualizo el estado del pedido "ORD-006" a "SHIPPED"
    Y busco el pedido por orderId "ORD-006"
    Entonces el estado del pedido debe ser "SHIPPED"
    Y los datos del cliente deben ser "CUST-006"

  # Escenario 7: Validación de datos con valores límite
  @escritura
  @valores-limite
  Escenario: Guardar pedido con cantidad máxima y precio mínimo
    Cuando creo un pedido con los siguientes datos:
      | orderId   | customerId | status    | productId | quantity | price    |
      | ORD-007   | CUST-007   | PENDING   | PROD-007  | 9999     | 0.01     |
    Y guardo el pedido en DynamoDB
    Entonces el pedido debe ser almacenado correctamente
    Y debo poder recuperar el pedido por su orderId "ORD-007"
    Y la cantidad debe ser 9999
    Y el precio debe ser 0.01