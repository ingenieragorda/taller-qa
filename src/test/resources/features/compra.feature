# language: es
@ui
Característica: Compra de productos
  Como cliente de la tienda
  quiero comprar un producto
  para recibirlo en mi casa

  Escenario: Agregar un producto al carrito
    Dado que soy un cliente con sesión iniciada
    Cuando agrego la mochila al carrito
    Entonces el carrito muestra 1 producto

  Escenario: Compra completa
    Dado que soy un cliente con sesión iniciada
    Cuando agrego la mochila al carrito
    Y completo la compra con nombre "Ana", apellido "Pérez" y código postal "110111"
    Entonces veo la confirmación "Thank you"
