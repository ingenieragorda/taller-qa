# language: es
@ui
Característica: Inicio de sesión
  Como cliente de la tienda
  quiero iniciar sesión con mi usuario
  para ver y comprar los productos

  Antecedentes:
    Dado que estoy en la página de login

  Escenario: Credenciales válidas
    Cuando inicio sesión con usuario "standard_user" y clave "secret_sauce"
    Entonces veo la página de productos

  Esquema del escenario: Credenciales no válidas
    Cuando inicio sesión con usuario "<usuario>" y clave "<clave>"
    Entonces veo un mensaje de error que contiene "<mensaje>"

    Ejemplos:
      | usuario         | clave          | mensaje      |
      | standard_user   | clave_mala     | do not match |
      | locked_out_user | secret_sauce   | locked out   |
