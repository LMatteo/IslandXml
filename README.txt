to use IslandXml.jar

java -jar command inputFile


PARSER

java -jar IslandXml.jar parse myfile.json

the input has to be well formed (following the island.schema) otherwise it will raise an error

output : myfile.xml
-*
the xml file can be open on a web browser, where you can enjoy the material design applied.


DATA PROCESSING

java -jar IslandXml.jar data myfile.xml

the input has to come from the above parser otherwise it will raise an error

output : myfile.html

