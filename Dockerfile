# ------------------------------------------------------------
# - DOCKERFILE
# - AUTOR: Wilman Ortiz
# - FECHA: 14-Mayo-2022
# - DESCRIPCION: Dockerfile que permite la creacion del
# -              contenedor para el encoder-server
# ------------------------------------------------------------

# Run Stage
FROM adoptopenjdk/openjdk11:alpine-jre

# Parametrizacion del nombre del archivo que genera spring boot
ARG JAR_FILE=server-encoder.jar
ARG BUILD_DATE
ARG BUILD_VERSION
ARG BUILD_REVISION

ENV APP_HOME="/app" \
    ENCODER_PORT=1535

# Informacion de la persona que mantiene la imagen
LABEL org.opencontainers.image.created=$BUILD_DATE \
	  org.opencontainers.image.authors="Wilman Ortiz Navarro " \
	  org.opencontainers.image.url="https://github.com/wortiz1027/server-encoder/blob/master/Dockerfile" \
	  org.opencontainers.image.documentation="" \
	  org.opencontainers.image.source="https://github.com/wortiz1027/server-encoder/blob/master/Dockerfile" \
	  org.opencontainers.image.version=$BUILD_VERSION \
	  org.opencontainers.image.revision=$BUILD_REVISION \
	  org.opencontainers.image.vendor="https://developer.io" \
	  org.opencontainers.image.licenses="" \
	  org.opencontainers.image.title="Server Encoder" \
	  org.opencontainers.image.description="El siguiente servidor gestiona la encriptacion de passwords"

# Creando directorios de la aplicacion y de carga temporal de los archivos
RUN mkdir $APP_HOME

# Puerto de exposicion del servicio
EXPOSE $ENCODER_PORT

# Copiando el compilado desde builder
COPY target/$JAR_FILE $APP_HOME/
COPY target/dependency-jars/ $APP_HOME/dependency-jars/

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${APP_HOME}/server-encoder.jar"]