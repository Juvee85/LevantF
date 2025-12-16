# Pruebas
Aqui se listan agunas recomendaciones para realizar pruebas sobre el proyecto

## Pruebas Unitarias
- Todas las pruebas unitarias estan en el directorio de test (No androidTest) 
- Las pruebas unitarias son realizadas con el framework JUnit
- Las pruebas unitarias verifican lógica de programación sin intervenir con el ecosistema de android, se recomienda hacerlas sobre el código que implemente los cálculos o que manipule datos del dominio, así como funciones auxiliares

## Pruebas de Componentes
- Verifican alguna interacción con la interfaz gráfica menor que requiere el ecosistema de android
- Se recomienda realizarlas con Roboelectric con pruebas en el directorio tes; sin embargo, es posible realizarlas con Espresso en el directorio androidTest (No es recomendado por que arranca el emulador, lo que hace que la prueba sea más lenta)
- Un ejemplo puede ser verificar que se active un botón al actualizar un dato
- **Nota importante:** Es posible que si se corren todas las pruebas del directorio test al mismo tiempo, las que utilizan Roboelectric aparezca que fallen, basta con correr las clases que tienen estas pruebas individualmente para verificar si se ejecutan correctamente

## Pruebas de IU
- Pruebas grandes que verifican el flujo completo de una pantalla, usualmente ejecutadas sobre el Emulador con el Framework Espresso
- Es recomendado mantener este tipo de pruebas al minimo ya que ejecutar decenas de estas puede ser tardado

## Pruebas manuales
- Para las pruebas manuales es recomendado utilizar datos consistentes para conocer los resultados esperados (revisa los archivos del directorio datosPruebas), alternativamente, consultar con el profesor si los resultados generados son correctos
- Para pruebas más sencillas 
- Las pruebas manuales son necesarias incluso después de desarrollar las pruebas de IU para verificar que todos los elementos son lo suficientemente visibles y fáciles de utilizar
- Ya que la aplicación genera archivos, parte de las pruebas debe ser verificar que estos se desplieguen con el formato adecuado en software que los utilice, además de asegurar que el formato es útil para el uso en SolidWorks que el profesor necesita

El archivo de [Tecnologías](tech.md) tiene enlaces a la documentación de los frameworks de pruebas utilizados en el proyecto
