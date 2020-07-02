# UTN_LABV_TP_Integrador
TP Integrador | Laboratorio de Computación 5 | UTN FRGP | 2020-1C

## Dependencias previas
- Tomcat 9
- MySql
- Java

## Pasos para ejecutar la aplicación desde Eclipse
1) Botón derecho sobre la raíz del proyecto -> Run as -> Maven install
2) Ir a **src/main/resources/hibernate.cfg.xml** y modificar el valor de la propiedad "hbm2ddl.auto" de "update" a "create"
3) Pararse sobre **src/main/java/UTN/FRGP/TP_L5_GRUPO_1/Main.cpp**, botón derecho -> Run as -> Java application
4) Descartar los cambios del paso 2
5) Botón derecho sobre la raíz del proyecto -> Run as -> Run on server

## Pasos para ejecutar la aplicación desde IntellIj
1) Importar o abrir proyecto y seleccionar "Tipo Maven"
2) Desplegar solapa de "Maven" y presionar el botón "sincronizar"
2) Ir a **src/main/resources/hibernate.cfg.xml** y modificar el valor de la propiedad "hbm2ddl.auto" de "update" a "create"
3) Entrar al archivo **src/main/java/UTN/FRGP/TP_L5_GRUPO_1/Main.cpp** y ejecutar el método "Main"
4) Descartar los cambios del paso 2
5) Abrir una configuración nueva, seleccionar "Tomcat Local", seleccionar la ruta a la carpeta de instalación de Tomcat, agregar un "Artifact" y seleccionar la opción "War exploded"; modificar el "Application context" a **"/TP_L5_GRUPO_1"**

## Rutas habilitadas
#### Customer
- http://localhost:{PORT}/TP_L5_GRUPO_1/customers
- http://localhost:{PORT}/TP_L5_GRUPO_1/customers/add
- http://localhost:{PORT}/TP_L5_GRUPO_1/customers/edit/{id}
- http://localhost:{PORT}/TP_L5_GRUPO_1/customers/delete/{id}
#### Accounts
- http://localhost:{PORT}/TP_L5_GRUPO_1/accounts
- http://localhost:{PORT}/TP_L5_GRUPO_1/accounts/add
- http://localhost:{PORT}/TP_L5_GRUPO_1/accounts/edit/{cbu}
- http://localhost:{PORT}/TP_L5_GRUPO_1/accounts/delete/{cbu}
