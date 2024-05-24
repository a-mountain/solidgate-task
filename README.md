### Implementation notes

#### HTTP request size

One million records in JSON format that matches Map<Int, Int> should take about 15mb (calculated by ChatGPT)
from my experience I know that HTTP body in Tomcat is less than 5 mb by default, so I set max value to 30 mb.

#### Functional tests

I wrote only one IT test for the endpoint using Testcontainers. I think writing unit tests is unnecessary in this case. 
There is no business logic that can be tested in isolated unit. 

### Possible improvement

Taking into account the fact that set-user-balance request can contain up to a million records.
I've implemented the update using JDBCTemplate batch functionality.
This approach strikes a good balance between performance and code complexity.
For further performance improvements, if they are needed, we need to set up performance tests to be able to compare our
solutions.
Depending on requirements, we need to find the balance between execution time of the update operation and its impact to
the other system. 
For instance,
increasing batch size reduces the number of networks requests to the db, but makes transactions longer which may force
other operations in the db to wait.
I would use the following metrics and testing environment.

#### Metrics
- Throughput of the system: the number of difference concurrent executed requests will show the impact of the update to the rest of the system
- Response time of the update: measure the time taken to complete the set-users-balance request.
### Testing environment
- Use a staging environment that mirrors the production setup as closely as possible
- Create a dataset with varying numbers of users to simulate different loads.
- Use tools like JMeter for load testing  

I see a few possible optimizations:
- Change batch size
- Move balance to its own table not to block other user-related operations
- Try to experiment with more sophisticated batch update techniques in postgres. I found some articles, for instance: https://dou.ua/forums/topic/35261/, https://minhajuddin.com/2020/10/17/how-to-do-batch-updates-in-postgresql/
