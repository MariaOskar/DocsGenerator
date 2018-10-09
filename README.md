# DocsGenerator
**JDK 9.0.4**  <br/>
This application creates excel/pdf files with randomly generated data.

## To run app with exec-maven-plugin:
#### Compile:
```
mvn compile
```
> **note:** To compile code run this command from the project's directory. 
Source and target java levels should be set in the pom.xml (maven-compiler-plugin configuration)
#### Run:
```
mvn exec:java
```
> **note:** mainClass should be set in the pom.xml (exec-maven-plugin configuration)

## To run with jar file:
#### Build jar:
```
mvn install
```
> **note:** mainClass, jar-descriptor, execution configurations  should be set in the pom.xml (maven-assembly-plugin configuration)


#### Run:
```
java -jar target\homework3-1.0-jar-with-dependencies.jar
```

> **note:** If you use Windows terminal, make sure that you've changed encoding ( default code page - 866 ) in order to see cyrillic symbols right. 
To change encoding use command: `chcp 1251`

## Results examples:

![PDF Example](https://mariaoskar.github.io/DocsGeneratorResults/PDFTable.jpg)
<https://mariaoskar.github.io/DocsGeneratorResults/Персональные%20данные_10-10-18_01-04-56.pdf>

![Excel Example](https://mariaoskar.github.io/DocsGeneratorResults/excelTable.jpg)
<https://mariaoskar.github.io/DocsGeneratorResults/Персональные%20данные_10-10-18_01-04-55.xlsx>
