# MyStatus
MyStatus is a REST plugin for PaperMC minecraft servers .

## API List

### - PAPI

```txt
1. UUID is NOT player name(ID).
2. The placeholder strings do not require %% symbols.
(For example, use /api/papi/server/server_tps to request %server_tps%.)
```

Method | API | Description
:---|:---|:---
GET | /api/papi/server/{placeholder} | Get server info using proper placeholder.
GET | /api/papi/player/{uuid}/{placeholder} | Get player info using proper placeholder. 

### - Admin

```txt
1. Username is player name(ID).
```

Method | API | Description
:---|:---|:---
Get | /api/admin/whitelist/{username} | Check if a player is in whitelist.\[True \ False\] 
POST | /api/admin/whitelist/{username} | Add a player into whitelist.