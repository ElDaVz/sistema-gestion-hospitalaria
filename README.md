# 🏥 Sistema de Gestión Hospitalaria

Sistema integral de gestión hospitalaria desarrollado en Java con interfaz gráfica Swing. Permite administrar personal médico, citas, nóminas y generar reportes estadísticos.

## 📋 Características

- ✅ Gestión de personal médico (Doctores, Cirujanos, Enfermeras, Terapeutas)
- ✅ Sistema de citas con reagendamiento automático
- ✅ Cálculo de nóminas con polimorfismo
- ✅ Detección y resolución de conflictos de horario
- ✅ Reportes de utilización y eficiencia
- ✅ Historial de reagendamientos
- ✅ Búsqueda avanzada de personal disponible

## 🛠️ Tecnologías

- **Java 24**
- **Java Swing** (GUI)
- **Maven** (Gestión de dependencias)
- **Arquitectura MVC**

## 📂 Estructura del Proyecto
```
src/main/java/
├── models/          # Clases de modelo (GeneralMedic, Appointment, etc.)
├── controllers/     # Lógica de negocio (HospitalManager, MedicalAppointmentsController)
├── views/           # Interfaz gráfica (HospitalView)
├── enums/           # Enumeraciones (EstadoActual)
└── Main.java        # Punto de entrada
```

## 🚀 Cómo abrir y ejecutar el proyecto

### Opción 1: Eclipse (Recomendado)

1. **Clonar el repositorio:**
```bash
   git clone https://github.com/ElDaVz/sistema-gestion-hospitalaria.git
```

2. **Abrir Eclipse**

3. **Importar el proyecto Maven:**
   - `File` → `Import`
   - `Maven` → `Existing Maven Projects`
   - `Next`
   - `Browse` → Seleccionar la carpeta del proyecto clonado
   - `Finish`

4. **Esperar a que Maven descargue las dependencias**

5. **Ejecutar el proyecto:**
   - Buscar la clase `Main.java` o `App.java`
   - Clic derecho → `Run As` → `Java Application`

### Opción 2: Línea de comandos
```bash
# Clonar repositorio
git clone https://github.com/ElDaVz/sistema-gestion-hospitalaria.git
cd sistema-gestion-hospitalaria

# Compilar con Maven
mvn clean compile

# Ejecutar
mvn exec:java -Dexec.mainClass="com.hospital.App"
```

### Opción 3: IntelliJ IDEA

1. `File` → `Open` → Seleccionar la carpeta del proyecto
2. IntelliJ detectará automáticamente que es un proyecto Maven
3. Esperar a que sincronice las dependencias
4. Ejecutar `Main.java`

## 📖 Uso del Sistema

1. **Gestión de Personal:** Contratar/despedir médicos, ver reportes
2. **Gestión de Citas:** Programar, reagendar y cancelar citas
3. **Reportes:** Ver nóminas, análisis de utilización, eficiencia
4. **Búsqueda:** Encontrar personal disponible por especialidad y fecha

## 👥 Autor

[Daniel Vasquez]

## 📄 Licencia

Este proyecto fue desarrollado como parte de un proyecto académico.