## Observation
* An extra GraphQL TypeScript file must be managed even when no business logic or request manipulation is required.
* The client specifies the desired response fields, reducing network bandwidth.
* Clients need to have a good understanding of the schema.
* Single endpoint for all operations.
* Since a single endpoint handles all requests, it becomes challenging to manage the API at the load balancer level, such as implementing throttling, blocking, and other controls.
* Real-Time Data using subscriptions.

## Curl
#### 1) Insert data
``` 
curl --location 'http://localhost:8080/v1/test/add' \
--header 'Content-Type: application/json' \
--data-raw '[{
    "id":123,
    "name":"John",
   "mobile":"8012345678",
   "email":"abc@xyz.com",
   "address":["Address1","Address2"]
},
{
    "id":456,
    "name":"Mark",
   "mobile":"8087654321",
   "email":"dummy@xyz.com",
   "address":["Address4","Address5"]
}

]'
```

#### 2) Fetch all record & set of  fields in response
**Request**
```
curl --location 'http://localhost:8080/v1/test/fetchdata' \
--header 'Content-Type: text/plain' \
--data '{
    getAllPerson{
        name
        email
    }
}'
```
**Response**
```
{
    "errors": [],
    "data": {
        "getAllPerson": [
            {
                "name": "John",
                "email": "abc@xyz.com"
            },
            {
                "name": "Mark",
                "email": "dummy@xyz.com"
            }
        ]
    },
    "extensions": null,
    "dataPresent": true
}
```
#### 3) Fetch record with condition & set of  fields in response
**Request**
``` 
curl --location 'http://localhost:8080/v1/test/fetchdata' \
--header 'Content-Type: text/plain' \
--data-raw '{
    findPerson(email:"abc@xyz.com"){
        name
        email
    }
}'
```
**Response**
```
{
    "errors": [],
    "data": {
        "findPerson": {
            "name": "John",
            "email": "abc@xyz.com"
        }
    },
    "extensions": null,
    "dataPresent": true
}
```
