## API REST TENPO


#### Definición del problema

1. Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:

- Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes consideraciones:

- El servicio externo puede ser un mock, tiene que devolver el % sumado.

- Dado que ese % varía poco, podemos considerar que el valor que devuelve ese servicio no va cambiar por 30 minutos.

- Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la api.

- Si el servicio falla, se puede reintentar hasta 3 veces.

2. Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado, y en caso de falla, no debe impactar el llamado al servicio principal.

- La api soporta recibir como máximo 3 rpm (request / minuto), en caso de superar ese umbral, debe retornar un error con el código http y mensaje adecuado.

- El historial se debe almacenar en una database PostgreSQL.

- Incluir errores http. Mensajes y descripciones para la serie 4XX.


3. Se deben incluir tests unitarios.

4. Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker. Recomendación usar docker compose

5. Debes agregar un Postman Collection o Swagger para que probemos tu API

6. Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.
7. Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una réplica del servicio funcionando en paralelo.


#### Solución planteada

- La api recibe dos parametros para sumar dos numeros. Tenemos un servicio mockeado que devuelve un numero aleatorio el cual se guarda en cache por 30 minutos
- Usamos interceptor para controlar los rpm del endpoint add y guardamos todo en forma async. (usamos la libreria bucket4j y @async de spring config)
- Agregamos test unitarios y un test de integration
- Para levantar la app usamos docker compose y java 17
```sh
#generamos el jar
$ ./mvnw packages
#corremos el docker compose
$ docker-compose up
```  
- Usamos swagger para documentar la api  http://localhost:8080/swagger-ui/index.html  y tambien comparto una coleccion de postman
-Para utilizar la app podemos usar la collection de postman o por la swagger-ui

- Con respecto a que la app funcionara en un sistema distribuido pense en lo siguiente. Podemos tener un load balancer adelante de la app para manejar la distruibucion. Tambien para un mejor manejo de cache centralizado podemos utilizar redis.
