[English](#english) | [中文](#中文)

---

## 中文

### 使用方法

在 `kubejs/server_scripts/` 目录下创建js文件：

```javascript
PlayerEvents.loggedIn(event => {
    event.setRow1Stage("basic_inventory")
    event.setRow2Stage("advanced_inventory")
    event.setRow3Stage("expert_inventory")
})
```

### API 方法

- `event.setRow1Stage(stageName)` - 设置背包第一行的阶段要求
- `event.setRow2Stage(stageName)` - 设置背包第二行的阶段要求
- `event.setRow3Stage(stageName)` - 设置背包第三行的阶段要求

### 默认未解锁任何阶段只有快捷栏可使用。

---

## English

### Usage

Place script in `kubejs/server_scripts/` directory:

```javascript
PlayerEvents.loggedIn(event => {
    event.setRow1Stage("basic_inventory");
    event.setRow2Stage("advanced_inventory");
    event.setRow3Stage("expert_inventory");
});
```

### API Methods

- `event.setRow1Stage(stageName)` - Set stage requirement for inventory row 1
- `event.setRow2Stage(stageName)` - Set stage requirement for inventory row 2  
- `event.setRow3Stage(stageName)` - Set stage requirement for inventory row 3

### By default, no stages are unlocked and only the quick bar can be used.
