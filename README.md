# AshManPro
### `AshManPro`是一款功能丰富的NK清理实体插件

---

## API
#### 添加不清理的实体
````java
AshManPro.addExemptedEntity(long eid);
AshManPro.addExemptedEntity(Entity entity);
AshManPro.addExemptedEntity(long eid,Entity entity);
````

#### 删除不清理的实体
````java
AshManPro.removeExemptedEntity(long eid);
AshManPro.removeExemptedEntity(Entity entity);
````

## 命令
````
/am force | 强制清除所有实体
````

## 配置文件：
````yaml
# ____                           ____
#|  _ \ _ __ ___  __ _ _ __ ___ / ___|  ___ _ ____   _____ _ __
#| | | | '__/ _ \/ _` | '_ ` _ \\___ \ / _ \ '__\ \ / / _ \ '__|
#| |_| | | |  __/ (_| | | | | | |___) |  __/ |   \ V /  __/ |
#|____/|_|  \___|\__,_|_| |_| |_|____/ \___|_|    \_/ \___|_|
#    _        _     __  __             ____
#   / \   ___| |__ |  \/  | __ _ _ __ |  _ \ _ __ ___
#  / _ \ / __| '_ \| |\/| |/ _` | '_ \| |_) | '__/ _ \
# / ___ \\__ \ | | | |  | | (_| | | | |  __/| | | (_) |
#/_/   \_\___/_| |_|_|  |_|\__,_|_| |_|_|   |_|  \___/


# 清理前的提示信息
beforehand-message:
  20: 将在20秒后清理所有实体和物品
  10: 将在10秒后清理所有实体和物品
# {ic}为清理的物品数量,{ec}为清理的实体数量
terminate-message: 本次清理一共清理了{ic}个物品,{ec}个实体
# 清理方式
eliminate-type:
  "0": # 清理所有世界
    enable: true
    # 以下世界将不会被清理
    eliminate-level-except: ["example world"]
  "1": # 仅清理有玩家的世界
    enable: false
    # 清理玩家大于阈值的世界
    threshold: 1
    # 以下世界将不会被清理
    eliminate-level-except: ["example world"]
  "2": # 清理玩家附近的实体或物品
    enable: false
    # X轴的偏移
    offsetX: 16
    # Y轴的偏移
    offsetY: 5
    # Z轴的偏移
    offsetZ: 16
    # 以下玩家将会不会被清理附近的实体或物品
    eliminate-player-except: ["pn1"]
# 开启清理物品
eliminate-items: true
# 不清理带NBT的物品
eliminate-items-except-nbtcontains: true
# 清理实体
eliminate-entity: true
# 强制清理所有实体,会无视保留实体的限制
eliminate-entity-force: false
# 在区块卸载的时候删除所有在区块里的实体
eliminate-entity-after-chunk-unload: false
# 触发清理的条件
eliminate-trigger:
  "0": # 当服务器实体总数大于某值时清理
    enable: true
    # 检测到实体数量大于该值将会启动清理
    threshold: 50
    # 检测的时间 单位秒
    detect-interval: 300
  "1": # 当服务器平均TPS小于某值时清理
    enable: false
    # 检测到平均TPS小于该值将会启动清理
    threshold: 19.2
    # 检测的时间 单位秒
    detect-interval: 300
  "2": # 当服务器实体总数大于某值并当服务器平均TPS小于某值时清理
    enable: false
    # 实体数量需大于该值才会启动清理
    entityThreshold: 50
    # 平均TPS需小于该值才会启动清理
    AvgTPSThreshold: 19.2
    # 检测的时间 单位秒
    detect-interval: 300

````


