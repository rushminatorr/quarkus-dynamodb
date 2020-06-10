# csv-to-dynamodb-data-ingestion 

Quick demo of using lambda to ingest csv data from your S3 bucket and stote it to Dynamodb.

## Deployment 
Using cloudformation to spin up a Dynamodb table, S3 bucket and a lambda that will upload the data to Dynamodb table. 

Template  Variable  |  Description
------------------- |  -------------
ServiceName         |  A name for the service
ImageUrlBase        |  The url of a docker image
ContainerPort       |  port number docker container is binding to
ContainerCpu        |  Amount of CPU to allocate
ContainerMemory     |  amount of memory to allocate
Path                |  A path on the public load b
DesiredCount        |  Count of service task to run
    
![AWS Components](docs/template.png)
    
##### Command:

    aws cloudformation deploy \
    --capabilities CAPABILITY_NAMED_IAM \
    --template-file cloudformation.yml \
    --stack-name energy-ingestion

### Assumptions
- Lambda is triggered twice a day, and it should process new files.
- S3 should be configured to send processed items to cold storage

### ToDo
- build pipeline
- add test suite!!!
- clean and organize code
- S3 should be configured to send processed items to cold storage
- design document/ADRs
- telemetry 
- Refine Security (IAM roles)
- immutable infrastructure 
