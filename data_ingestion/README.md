# csv-to-dynamodb-data-ingestion 

Quick demo of using lambda to ingest csv data from your S3 bucket and stote it to Dynamodb.

## Deployment 
Using cloudformation to spin up a Dynamodb table, S3 bucket and a lambda that will upload the data to Dynamodb table. 

Use the sample data provided in consumption.csv in required.

Template  Variable  |  Description
------------------- |  -------------
DynamoDBTableName   |  Name for your Dynamodb table
BucketName          |  Name to assign to your S3 bucket

    
![AWS Components](docs/template.png)
    
##### Command:

    aws cloudformation deploy \
    --capabilities CAPABILITY_NAMED_IAM \
    --template-file cloudformation.yml \
    --stack-name data-ingestion

### Assumptions
- Lambda is triggered twice a day(6am, 7pm), and it should process all new files.
- Delete csv files after processing

### ToDo
- build pipeline
- perhaps send processed items to cold storage 
- design document/ADRs
- telemetry 
- Refine Security (IAM roles)
- immutable infrastructure (check)
- add test