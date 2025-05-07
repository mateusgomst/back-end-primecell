FROM maven:3.8.4-openjdk-17

WORKDIR /app

# Copiar apenas os arquivos necessários para resolver dependências
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Resolver dependências para cache (não falha se não tiver mvnw)
RUN mvn dependency:go-offline -B || echo "Continuando sem mvnw"

# Variáveis de ambiente para configurar o DevTools
ENV SPRING_DEVTOOLS_RESTART_ENABLED=true
ENV SPRING_DEVTOOLS_LIVERELOAD_ENABLED=true

# Em desenvolvimento, a aplicação será iniciada com spring-boot:run
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true'"]