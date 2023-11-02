# Spring Batch : XML to JSON Array

<p>
A Spring Boot Project that uses Spring Batch to parse an XML file and converts the fragments into a JSONArray of Recording Objects.
</p> 

### Prerequisites : 
<Center>
To run this application you would need  : <br>
</Center> 

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### How To Run The Application With Maven: 

You can run this application either by going to the `XmlToJsonApplication.java`  class and clicking the `Run` button. 
Or you can run it from th terminal using the maven command : <br> <br>
`mvn clean spring-boot:run`
<br>
<br>
Once the application is up and running, you can use either postman or the web to test the application. The endpoint for the 
application is `localhost:8080/api/v1/getJSON` .Below is a sample showing how to 
test the application with Postman :  <br> <br>

- Go to  Postman 
- Type in the  URL endpoint `localhost:8080/api/v1/getJSON` <br>
- Add your filepath as the first query parameter: <br> 
&nbsp; &nbsp; &nbsp; For example : If the filepath is `C/user/downloads/myXml.xml`  then the whole request URL would look like : 
`localhost:8080/api/v1/getJSON?filepath=C/user/downloads/myXML.xml`
<br>
- Next add the XML tags and the Names you'd want the JSON objects to have. For Example : <br><br> If you'd like your Recording's title property to be called recordingTitle, then pass in the value like this : `RECORDING-TITLE-COLLECTING-SOCIETY=recordingTitle`
- Not that it will only be able to map with values if the Tag exists in the XML file. The entire URL would look something like this :
<br> <br> `localhost:8080/api/v1/getJSON?filepath=C/user/downloads/myXml.xmll&RECORDING-TITLE-COLLECTING-SOCIETY=recordingTitle&MAIN-ARTIST-NAME-COLLECTING-SOCIETY=Artist`


>> {"Recordings":[{"Title":"PRAVI 2017","Main Artist":[{"Artist":"JANA"}]},{"Title":"DOWN 2011","Main Artist":[{"Artist":"JAY SEAN"}]}]}