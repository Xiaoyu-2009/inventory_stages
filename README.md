[English](#english) | [中文](#中文)

---

## 中文

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

### 说明

- 默认未解锁任何阶段 只有快捷栏可使用
- `inventory_row_1`、`inventory_row_2`、`inventory_row_3` 为默认提供的阶段
- 可以通过游戏阶段的指令 `add` 则逐渐恢复对应的背包对应行 `remove` 则相反

---

## English

Place script in `kubejs/server_scripts/` directory:

```javascript
PlayerEvents.loggedIn(event => {
    event.setRow1Stage("basic_inventory")
    event.setRow2Stage("advanced_inventory")
    event.setRow3Stage("expert_inventory")
})
```

### API Methods

- `event.setRow1Stage(stageName)` - Set stage requirement for inventory row 1
- `event.setRow2Stage(stageName)` - Set stage requirement for inventory row 2  
- `event.setRow3Stage(stageName)` - Set stage requirement for inventory row 3

### Notes

- By default, no stages are unlocked and only the hotbar is accessible
- `inventory_row_1`, `inventory_row_2`, `inventory_row_3` are default provided stages
- Use game stage commands `add` to gradually unlock inventory rows, `remove` for the opposite
