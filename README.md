# Documentos de NLP

Este proyecto administra los pasos iniciales de los documentos cargados al detector de plagio de la materia NLP de la UTN FRBA. El objetivo es permitirle también llevar seguimiento de las diferentes etapas de tratamiento de los archivos, así como servir de punto de reparto de información y pasamanos de requests.

# Setup

### MongoDB

Para la capa de persistencia se utiliza MongoDB. Existe un archivo de configuración *application.properties* dentro de la carpeta Resources para editar los detalles de conexión. Los valores por defecto son:

- **Host:** localhost
- **Puerto:** 27017
- **Database Name:** nlp-documents

### Lombok

El proyecto incluye la dependencia lombok para la automatización de código. Para compilarlo en IDE es necesario añadir el soporte para el plugin dentro del mismo:

- [Eclipse IDE](https://projectlombok.org/setup/eclipse "Eclipse IDE")
- [IntelliJ IDEA](https://projectlombok.org/setup/intellij "IntelliJ IDEA")
- [Netbeans](https://projectlombok.org/setup/netbeans "Netbeans")
- [Otros](https://projectlombok.org/setup/overview "Otros")

### Otras configuraciones

En el archivo *application.properties* existen otras configuraciones adicionales:

- **Puerto de escucha de API:** 6570

## Rutas

Se realiza documentación automática a través de Swagger.