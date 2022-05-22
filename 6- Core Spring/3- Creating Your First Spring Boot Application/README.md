# Creating Your First Spring Boot Application
Demo Application: [Conference Scheduling](https://github.com/MahmoudAbdelazim/Pluralsight-Java-Spring-Track/tree/main/6-%20Core%20Spring/3-%20Creating%20Your%20First%20Spring%20Boot%20Application/Conference%20Scheduling)
<hr>

## Spring Boot Configuration
### Order Of Precedence
1. Command Line Arguments (External)
2. SPRING_APPLICATION_JSON args (Internal)
3. Servlet Parameters (Internal)
4. JNDI (External)
5. Java System Properties (Internal)
6. OS Environment Variables (External)
7. Profile Properties (Internal)
8. Application Properties (Internal)
9. @PropertySource Annotations (Internal)
10. Default Properties (Internal)

It's a good idea to choose one internal method to set default configurations (like application properties), and one external method for overrides (like OS environment variables).

## Handling Different Environment in Spring Boot
We can use Environmental configuration to edit environment-specific properties in the application.properties.

for example we can create these files: `application-dev.properties` and `application-prod.properties`.

and set the `-Dspring.profiles.active=dev` in the VM options in the run configuration or the command line.

<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)