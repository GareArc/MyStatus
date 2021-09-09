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
GET | /api/papi/server/{placeholder} | Get server info using proper placeholder.
GET | /api/papi/player/{username}/{placeholder} | Get player info using proper placeholder. 

```text
Examples:
1. /api/papi/server/server_name => A Minecraft Server
2. /api/papi/player/Gare_TH/player_name => Gare_TH
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
2. /api/admin/whitelist/aaa => false
3. /api/admin/whitelist/admin/KEKW => succeed
4. /api/admin/whitelist/admin/KEKW => Player already in whitelist
```