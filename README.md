# Ice Dragon JAX-RS Plugin

This code compiles into a library that adds the capability of accepting time-based 
authorization cookies produced by the ice-dragon.ch platform.

# Configuration
This library can be configured either with environment variables or java properties.

| Parameter        |     Description           | Default  |
| ------------- |:-------------:| -----:|
| ICE_DRAGON_SHARED_SECRET      | shared secret provided by ice-dragon |  |
| ICE_DRAGON_ORIGIN      | ice-dragon website that is used when generating CORS headers | https://ice-dragon.ch |

# Build
In order to build the library jar with its dependencies, just execute 
```bash
./mvnw assembly:assembly -DdescriptorId=jar-with-dependencies
```
and include `jaxrs-plugin-0.0.1-jar-with-dependencies.jar` into your libraries directory.

# Runtime
We assume that you have a JAX-RS implementation running.

If you also have a `jakarta.enterprise.cdi-api` implementation, DragonCheck will be available as an `@ApplicationScoped` 
bean.