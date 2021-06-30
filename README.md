# intellisense.io
 Code challange

Hi,
My proposal for the code challenge is a Java Spring Boot API solution.

Instructions how to run the application:

Steps 2 and 3 are optionals.

1. Download the project from Github (https://github.com/jboixaderN/intellisense.io)
2. Create executable jar. This step is optional. It's for creating the jar file with maven. Jar file is already uploaded into the repository.
   Go to the jar folder location (..\intellisense.io\minute\) and execute the following maven command line.
   **mvn clean install**
   This command will create a jar file (minute-0.0.1-SNAPSHOT.jar) into target folder
3. Run the applicatin. This step is optional Go to the jar folder location  (..\intellisense.io\minute\target))  
   cd "...\\minute\target"
   **java -jar minute-0.0.1-SNAPSHOT.jar**
   This command will run an embedded tomcat in port 8080
4. Build docker image
   Go to the folder containing Dockerfile. (..\intellisense.io\minute\) and execute the following command
   **docker build -t minute:1.0 .**
5. Run docker contain
   Execute the following command:
   docker run -d -p 8080:8080 -t minute:1.0
    This command will run the aplication on port 8080.
6. Make a post request with Curl
   curl -H "Content-Type: application/json" -X POST http://localhost:8080/minute -d "{\"period\":\"10\"}"






