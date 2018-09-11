[Documentation](https://docs.pistawecki.com)

Select on which client to perform the modifications. Set it to 0 to make changes on all clients.
Provide a list of users.
Provide a list of groups.


**Execution**
java -jar uc4_addUserToGroup
java -jar uc4_addUserToGroup uc4.config.json

**Config - uc4.config.json**
```
{
  "connection": {
    "host": "localhost",
    "port": 2217,
    "client": 0,
    "user": "user",
    "password": "password"
  },
  "options": {
    "inputFileName": "../config/uc4.input.json"
  }
}
```
**Input - uc4.input.json**
```
{
  "client": 2000,
  "groups": [
    "PHGROUP01",
    "PHGROUP02"
  ],
  "users": [
    "PH01",
    "PH02",
    "PH03",
    "PH04",
    "PH05"
  ]
}
```