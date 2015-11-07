# es-webcrawler
Simple application with a few REST endpoints, which takes a URL, parses the content, indexes it in an embedded Elasticsearch instance and provides a REST endpoint to search the index.

## Package and run the application
This project includes the Maven Wrapper. To build the project, just run

`./mvnw clean package`

The wrapper will automatically download the required Maven distribution (unrestricted internet access necessary).

After that, run `java -jar target/webcrawler*.jar`

## Index a webpage

The application exposes a REST endpoint at `http://localhost:8080/import?url=`, which expects an encoded URL as parameter value.

## Search for results

For a search use the endpoint `http://localhost:8080/search?q={searchterm}`

The result is returned as JSON.