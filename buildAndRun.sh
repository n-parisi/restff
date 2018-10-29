cd /home/nparisi/dev/restff/
./mvnw clean install -DskipTests=true
cd target
java -jar restff-0.0.1-SNAPSHOT.jar
