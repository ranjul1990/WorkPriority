# WorkPriority
Project on assigning work priority

Note the instructions below to deploy and use the project:

1. Import the project WorkPriority in eclipse.

2. Provided apache is running, use rest services to send requests to the system and get response.

3.Please find the Rest urls for the below end points:

> An endpoint for adding a ID to queue (enqueue). This endpoint should

accept two parameters, the ID to enqueue and the time at which the ID

was added to the queue.

url: http://localhost:8080/WorkPriority/rest/UserService/addOrderedData

eg request: 
{
    "orderID" : "1",
    "orderDate" : "2017 07 06 10:37:01"
}

> An endpoint for getting the top ID from the queue and removing it (de-
queue). This endpoint should return the highest ranked ID and the time

it was entered into the queue.

url: http://localhost:8080/WorkPriority/rest/UserService/getHighestRankId


> An endpoint for getting the list of IDs in the queue. This endpoint should

return a list of IDs sorted from highest ranked to lowest.

url:http://localhost:8080/WorkPriority/rest/UserService/getListOfOrderIds


> An endpoint for removing a specific ID from the queue. This endpoint

should accept a single parameter, the ID to remove.

url: http://localhost:8080/WorkPriority/rest/UserService/removeOrderId

request: any order id

> An endpoint to get the position of a specific ID in the queue. This endpoint

should accept one parameter, the ID to get the position of. It should return

the position of the ID in the queue indexed from 0.

url: http://localhost:8080/WorkPriority/rest/UserService/getPositionInQueue

request: any order id

> An endpoint to get the average wait time. This endpoint should accept a

single parameter, the current time, and should return the average (mean)

number of seconds that each ID has been waiting in the queue.

url: http://localhost:8080/WorkPriority/rest/UserService/getAverageWaitTime

eg request: 2017 07 05 10:14:30
