docker-compose up  (runs MySQL)


System API calls:
# Change threads count endpoint 
```
POST localhost:8080/addThreads
{
    "consumersCount" : 1,
    "producersCount" : 1
}
```

# Change counter endpoint 
```
POST localhost:8080/changeCounter
{
    "newValue" : 80
}
```