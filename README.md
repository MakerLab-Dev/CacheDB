# CacheDB

[Repo link](https://github.com/MakerLab-Dev/CacheDB)

CacheDB is a simple key-value store that stores data in memory and on disk. It is free of dependencies and is written in pure Java.
The app tough uses picocli for the command line interface.

### Mejoras:
- [X] Guardar los ficheros en subcarpetas para evitar tener demasiados ficheros en una misma carpeta y causar problemas de rendimiento. ([Referencia](https://softwareengineering.stackexchange.com/questions/301400/why-is-the-git-git-objects-folder-subdivided-in-many-sha-prefix-folders))
- [X] Tests unitarios para comprobar el correcto funcionamiento de la aplicación.
- [X] Parametro opcional para indicar el directorio donde se guardan los ficheros.
- [X] JavaDoc en el directorio 'JavaDoc'
- [X] El proyecto usa Git y está publicado en Github
- [X] Subcomandos CLI extras: 'clear', 'size' y 'keys'
- [ ] CI/CD en Github Actions (Aparentemente hay que cambiar el sistema de compilación a Maven o Gradle, actualmente usa Intellij)

### Instrucciones de uso:
El archivo .jar compilado y listo para usar se encuentra en la carpeta 'out/artifacts/CacheDB_jar'. Para ejecutarlo, se debe abrir una terminal en esa carpeta y ejecutar el siguiente comando:
```
java -jar CacheDB.jar
```
Uso del programa:
```
Usage: cachedb [-d=<dir>] [COMMAND]
A simple key-value store with persistence.
  -d, --dir=<dir>   directory where the cache will be stored (Defaults to ./)
Commands:
  add     Add or update the value associated to a key.
  clear   Remove all keys from the store.
  get     Get the value associated with the key passed as argument.
  keys    Get all keys stored in cache.
  remove  Remove a key from the store.
  size    Count the number of keys stored in cache.
```

El JavaDoc se encuentra en la carpeta 'JavaDoc'. Para abrirlo, se debe abrir el archivo 'index.html' en un navegador web.
