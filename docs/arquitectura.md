# Arquitectura

## Flujo básico

El objetivo de la aplicación es ayudar en el diseño de levas, para esto, el usuarioo provee datos de cada tramo del recorrido de la leva y la rotación con la que se mueve para calcular la velocidad, aceleración y sacudimiento. 

Después de pasar por el menú, en la pantalla principal, Radial_LinealActivity, se ingresan los datos de manera que el usuario ingresa los parámetros por cada tramo que decide agregar; en caso de que se seleccione un tramo de detenimiento, se debe ocultar el campo de ecuación y de altura, el eje x total debe terminar en 360, y la altura en 0 (subidas - bajadas).

Posteriormente, se revisan las gráficas SVAJ en la pantalla GraphsActivity, y se puede continuar a generar el perfil. En la siguiente pantalla se indica el diametro de la base y del rodillo; sin embargo, el del rodillo aún no es utilizado en los cálculos posteriores. En la última pantalla del flujo, se despliega el perfil geométrico y da 3 opciones para exportar, se realiza con objetos que implementan la interfaz Exportador como se muestra en el snippet de PerfilGraphActivity

```kotlin
val seleccionExportador = binding.btnGrSeleccionExportar.checkedButtonId
when (seleccionExportador) {
    R.id.btnExcel -> exportador = ExportadorExcel()
    R.id.btnDxf -> exportador = ExportadorDXF()
    R.id.btnTxt -> exportador = ExportadorTXT()
}

exportador.exportar(perfil, this, selectedFolderUri)
```

En la carpeta datosPrueba en la raíz del repositorio, hay archivos con datos para probar el flujo de la aplicación desde el inicio hasta generar un perfil.

## Calculadoras SVAJ

La aplicación utiliza el patrón de diseño de fabrica abstracta para determinar de manera dinamica que calculo realizar dependiendo de las opciones seleccionadas por el usuario. La estructura se muestra en el siguiente diagrama:

![Diagrama del patrón de fabrica aplicado a las calculadoras de funciones SVAJ](imagenes/patronFabrica.png)

La pantalla que genera las gráficas (GraphsActivity) depende de as interfaces de CalculadoraSVAJ y GeneradorCalculadora, lo que permite utilizar generadores para los distintos segmentos (subida y bajada) que cada uno sea capaz de crear las calculadoras para las distintas funciones.

- GeneradorCalculadora: Es utilizado para crear calculadoras, depende del tipo de segmento
- CalculadoraSVAJ: Implementa el cálculo del desplazamiento, velocidad, aceleración y sacudimiento de las distintas funciones de levas; actualmente, existe la calculadora cicloidal, MAS, 3-4-5, y 4-5-6-7.

Para agregar una función (que calcule todo el SVAJ) se crea una nueva clase que implemente CalculadoraSVAJ y se agrega un método a los generadores para que devuelvan un objeto de ese tipo.

Todas las clases e interfaces que conforman el patrón se encuentran en el paquete svaj.

El código que utiliza las calculadoras en GraphsActivity se ve así

```kotlin
private fun obtenerCalculadora(tramo: Radial_LinealActivity.Tramo): CalculadoraSVAJ {
        val segmento = tramo.segmento.lowercase()
        val ecuacion = tramo.ecuacion.lowercase()
        var calculadora: CalculadoraSVAJ
        val generador: GeneradorCalculadora = if (segmento == "subida") GeneradorCalculadoraSubida()
        else GeneradorCalculadoraBajada()

        when (segmento) {
            "subida", "bajada" -> {
                calculadora = when (ecuacion) {
                    "cicloidal" -> generador.crearCalculadoraCicloidal()
                    "mas" -> generador.crearCalculadoraMAS()
                    "3-4-5" -> generador.crearCalculadora345()
                    "4-5-6-7" -> generador.crearCalculadora4567()
                    else -> generador.crearCalculadoraCicloidal()
                }
            }
            "det. alto" -> {
                calculadora = CalculadoraDetenimientoAlto()
            }
            else -> {
                calculadora = CalculadoraDetenimientoBajo()
            }
        }

        return calculadora
    }
```

Así, el cálculo se hace de la siquiente manera

```kotlin
var xAnterior = 0
for (tramo in tramos) {
    val calculadora = obtenerCalculadora(tramo)
    val entries = calcularDatosGrafica(tramo, rpm, calculadora, "desplazamiento",
         xAnterior, paso)
     entriesDesplazamiento.addAll(entries)
     loadChartData(desplazamientoChart, entriesDesplazamiento, "Desplazamiento")
     xAnterior += tramo.ejeX.toInt()
     alturaInicial = alturaAcumulada
}
```
xAnterior se utiliza para determinar en donde termina cada tramo, se selecciona la calculadora con la información del tramo y se realizan los calculos para generar entradas de la gráfica
