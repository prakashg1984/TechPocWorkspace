This is a sample API to store request to ElasticSearch from Kafka and Search on it

Requirements : Kafka, ElasticSearch

Payload : OrderEvent Payload

EventDetails : localhost:8080/orders/event/10-153456561978108
OrderDetails : localhost:8080/orders/10-153456561978108
Search : localhost:8080/orders/?searchString=%7B%22sourceDetail.channel%22%3A%22CRU-MOBILITY%22%2C%22requestType%22%3A%22SOR%22%7D