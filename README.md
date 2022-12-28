# CacheDB

[Repo link](https://github.com/MakerLab-Dev/CacheDB)

CacheDB is a simple key-value store that stores data in memory and on disk. It is free of dependencies and is written in pure Java.
The app tough uses picocli for the command line interface.

### Mejoras:
- [X] Guardar los ficheros en subcarpetas para evitar tener demasiados ficheros en una misma carpeta y causar problemas de rendimiento. ([Referencia](https://softwareengineering.stackexchange.com/questions/301400/why-is-the-git-git-objects-folder-subdivided-in-many-sha-prefix-folders))
- [X] Tests unitarios para comprobar el correcto funcionamiento de la aplicación.
- [X] Parametro opcional para indicar el directorio donde se guardan los ficheros.
- [X] JavaDoc en el directorio 'JavaDoc'
