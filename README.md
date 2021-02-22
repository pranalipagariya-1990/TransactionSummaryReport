## Daily Transaction Summary Report
This application is used to parse the transaction file to generate summary report.

## Installation
If running through jar following is the command :
* You can specify the fileName in the arguments, or it will pick the default one
* Run using command line -> java -jar assignment.jar TransactionSummaryReport
* Install any IDE of you choice and run following commands:
   - mvn clean install
   - RUN TransactionSummaryReport.java
   - You can pass the input file path in the input.properties 
* An output file get created in the classpath
  
## Property Configuration
* Add in more property data for the fields to be parsed from the transaction file
* src/main/resources/input.properties - location of the file 
* add/update the length for each field
* can add/update the combination of fields 

## Troubleshooting
* Check logs for errors -> transaction-summary-report.log
* check if the property present in the input.properties file

## Libaries And Version 
* Java 1.8
* lombok 
* log4j
* commons-csv
* junit-jupiter
* jackson-dataformat-csv
