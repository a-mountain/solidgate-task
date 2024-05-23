### Implementation notes

#### HTTP request size

One million records in JSON format that matches Map<Int, Int> should take about 15mb (calculated by ChatGPT)
from my experience I know that HTTP body in Tomcat is less than 5 mb by default, so I set max value to 30 mb.

#### Functional tests

I wrote only one IT test for the endpoint using Testcontainers. I think writing unit tests is unnecessary in this case. 
There is no business logic that can be tested in isolated unit. 
