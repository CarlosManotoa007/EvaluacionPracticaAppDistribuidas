# AforoSeguro

Ejercicio de Java RMI para el aporte práctico de la Unidad I de Aplicaciones Distribuidas. El coordinador registra las admisiones de tres clientes independientes y aplica un límite de cinco personas.

## Compilar y ejecutar

Se requiere JDK 21. El código usa la biblioteca estándar de Java. Desde la raíz del repositorio, ejecute:

```sh
javac -encoding UTF-8 -d out src/Aforo.java src/AforoImpl.java src/ServidorRMI.java src/ClienteRMI_Puerta01.java src/ClienteRMI_Puerta02.java src/ClienteRMI_Puerta03.java
```

Mantenga el coordinador en ejecución en una terminal:

```sh
java -cp out ServidorRMI
```

Desde otra terminal, ejecute los tres clientes en este orden, dejando que cada uno termine antes de arrancar el siguiente:

```sh
java -cp out ClienteRMI_Puerta01
java -cp out ClienteRMI_Puerta02
java -cp out ClienteRMI_Puerta03
```

Cada cliente hace dos intentos de entrada y termina. Con el coordinador recién iniciado, el resultado esperado es:

| Cliente | Primer intento | Segundo intento |
| --- | --- | --- |
| P1 | Aforo actual: 1 | Aforo actual: 2 |
| P2 | Aforo actual: 3 | Aforo actual: 4 |
| P3 | Aforo actual: 5 | Aforo lleno |

Detenga el coordinador con `Ctrl+C` y vuelva a iniciarlo para reiniciar el contador. Estos comandos corresponden al código del commit `6c0dc2f`.

## Comunicación y datos

| Elemento | Implementación |
| --- | --- |
| Registro RMI | El coordinador inicia el registro en el puerto TCP `1099`. Los clientes consultan `localhost:1099` y buscan el nombre `Aforo`. |
| Invocación | La interfaz remota `Aforo` expone `int SumarAforo() throws RemoteException`. La solicitud no tiene parámetros y el objeto remoto se exporta en un puerto asignado por el sistema. |
| Respuesta | Un entero de 1 a 5 indica que se admitió la entrada y expresa el nuevo total. El valor `-1` indica que se alcanzó la capacidad. |
| Middleware | El stub de RMI codifica la invocación, la envía al objeto remoto y decodifica el valor devuelto. RMI gestiona la delimitación de los mensajes y reporta fallos de comunicación con `RemoteException`. |

El método `SumarAforo()` usa `synchronized` para comprobar el límite e incrementar el contador bajo el mismo bloqueo. El límite, el host y el puerto están definidos en el código.

## Evidencia y alcance

- **E0.** La [captura registrada en `fb6f14c`](evidencia/Evidencia%20conexion%20desactivada.png) muestra «No conectado» en Windows. En la imagen faltan la lista de adaptadores, el usuario, el equipo y la fecha y hora.
- **Comunicación RMI.** El commit `5e8e69d`, de las 09:56:05, ya permite ejecutar un cliente y un servidor que se comunican por RMI. El contador compartido con exclusión mutua aparece en `6c0dc2f`, a las 10:31:54. Ambas horas corresponden al 23 de noviembre de 2026 (UTC−5).
- **Comprobaciones posteriores.** Con el límite de cinco, un auxiliar de revisión externo al repositorio lanzó 30 llamadas concurrentes y obtuvo 5 admisiones y 25 rechazos. Al detener el coordinador e iniciar P1, el cliente informó un error de conexión.

Quedaron pendientes las órdenes de salida y estado, la configuración externa, la autenticación, `PruebaCarga`, el modo inseguro, los latidos, los relojes de Lamport, las bitácoras y `OrdenTotal`. El [informe](docs/informe.pdf) detalla el estado de cada escenario de la guía.

## Compilar el informe

Se requiere una distribución LaTeX con `pdflatex` y los paquetes `fontenc`, `lmodern`, `babel` con soporte para español, `geometry`, `graphicx` y `array`, además de la captura incluida en `evidencia/`. Desde la raíz del repositorio:

```sh
cd docs
pdflatex -interaction=nonstopmode -halt-on-error informe.tex
pdflatex -interaction=nonstopmode -halt-on-error informe.tex
```

El archivo principal es [`docs/informe.tex`](docs/informe.tex) y el resultado es `docs/informe.pdf`.

## Código reutilizado

No se reutilizó código. Todo lo desarrollado se hizo durante la evaluación