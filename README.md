# MyStatus
MyStatus is a REST plugin for PaperMC minecraft servers .

## API List
+ /api is default path, you can change it in config.yml, as well as port.

### - PAPI

```text
1. Username is player name(ID).
2. The placeholder strings do not require %% symbols.
(For example, use /api/papi/server/server_tps to request %server_tps%.)
```

Method | API | Description
:---|:---|:---
GET | /api/papi/server/{placeholder} | Get server info using placeholder.
GET | /api/papi/player/{username}/{placeholder} | Get player info using placeholder with name. 
GET | /api/papi/player/uuid/{uuid}/{placeholder} | Get player info using placeholder with uuid.

```text
Examples:
1. /api/papi/server/server_name => A Minecraft Server.
2. /api/papi/player/Gare_TH/player_name => Gare_TH
3. /api/papi/player/uuid/87143f9f-b764-47f7-b00b-4c96f65ee83a/player_name => Gare_TH
```

### - Admin

```txt
1. Username is player name(ID).
```

Method | API | Description
:---|:---|:---
GET | /api/admin/whitelist/{username} | Check if a player is in whitelist.\[True \ False\] 
POST | /api/admin/whitelist/{auth}/{username} | Add a player into whitelist. Authentication required.

```text
Examples:
1. /api/admin/whitelist/Gare_TH => true
2. /api/admin/whitelist/SomeoneNotInWhiteList => false
3. /api/admin/whitelist/adminPass/KEKW => succeed
4. /api/admin/whitelist/adminPass/KEKW => Player already in whitelist
```