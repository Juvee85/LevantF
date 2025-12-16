# Tecnologías

Aquí se listan librerias y herramientas utilizadas en el proyecto y recursos para familiarizarse con ellas

- MPAndroidChart para las gráficas [Repositorio del proyecto](https://github.com/PhilJay/MPAndroidChart)
- Firebase para la base de datos [Documentación](https://firebase.google.com/docs/)
- RevenueCat como propuesta para gestión de suscripciones [Documentación](https://www.revenuecat.com/docs/) [Tutorial del uso de Paywalls](https://www.youtube.com/watch?v=PNiVCdExtkw)
- RoboElectric para pruebas en JVM simulando el entorno de Android [Documentación](https://robolectric.org/)
- Espresso para pruebas de IU sobre el emulador de Android [Documentación](https://developer.android.com/training/testing/espresso?hl=es-419)
- DXF para exportación en un formato utilizado en programas CAD [Especificación del formato](https://help.autodesk.com/view/OARX/2018/ENU/?guid=GUID-E1F884F8-AA90-4864-A215-3182D47A9C74)

**Nota importante** Para utilizar Firebase y RevenueCat es necesario levantar instancias propias para cada nuevo equipo de desarrollo para evitar depender de las cuentas de personas que ya no trabajan en el proyecto. En el caso de Firebase, es necesario reemplazar el archivo google-services.json y levantar la base de datos; y para RevenueCat, es necesario generar una llave para la API y agregarla en este segmento de la actividad MenuPrincipal:

```kotlin
Purchases.logLevel = LogLevel.DEBUG
Purchases.configure(PurchasesConfiguration.Builder(this, "<API_KEY>").build())
```

Adicionalmete, sera necesario configurar las ofertas de la aplicación (ejemplo, licencia valida por un semestre con 7 días de prueba) en el dashboard de RevenueCat y una pantalla de Paywall para desplegarlas. El video en el enlace tiene infomración sobre como hacerlo. NO es necesario configurar RevenueCat para probar la aplicación, sólo hazlo si hay algo que revisar en cuestión de la Paywall. De lo contrario, puedes dejar la línea de arriba tal cual como esta con "<API_KEY>".
