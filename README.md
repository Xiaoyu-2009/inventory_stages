[English](#english) | [中文](#中文)

---

## 中文

在 `kubejs/server_scripts/` 目录下创建js文件：

```javascript
InventoryStagesEvents.playerLoggedIn(event => {
    // 默认提供"inventory_row_1~3"阶段来解锁背包阶段
    event.removeSetRow1Stage("inventory_row_1")  // 设置第1行不需要"inventory_row_1"阶段
    event.removeSetRow2Stage("inventory_row_2")  // 设置第2行不需要"inventory_row_2"阶段
    event.removeSetRow3Stage("inventory_row_3")  // 设置第3行不需要"inventory_row_3"阶段
    // 默认玩家带有inventory_hotbar=玩家快捷栏默认不会被禁用
    /* event.removeSetRow4Stage("inventory_hotbar")  // 设置第4行不需要"inventory_hotbar"阶段[快捷栏] */

    event.addSetRow1Stage("1")  // 设置第1行需要"1"阶段
    event.addSetRow2Stage("2")  // 设置第2行需要"2"阶段
    event.addSetRow3Stage("3")  // 设置第3行需要"3"阶段
    /* event.addSetRow4Stage("4")  // 设置第4行需要"4"阶段[快捷栏] */
})

// 调用全局方法自动重载配置 让/reload立即生效
reloadInventoryStages.run()
```

### API 方法

- `event.addSetRow1Stage(stageName)` - 添加背包第一行的阶段要求
- `event.addSetRow2Stage(stageName)` - 添加背包第二行的阶段要求
- `event.addSetRow3Stage(stageName)` - 添加背包第三行的阶段要求
- `event.removeSetRow1Stage(stageName)` - 移除背包第一行的阶段要求
- `event.removeSetRow2Stage(stageName)` - 移除背包第二行的阶段要求
- `event.removeSetRow3Stage(stageName)` - 移除背包第三行的阶段要求

### 说明

- 默认未解锁任何阶段 只有快捷栏可使用
- `inventory_row_1`、`inventory_row_2`、`inventory_row_3` 为默认提供的阶段
- 可以通过游戏阶段的指令 `add` 则逐渐恢复对应的背包对应行 `remove` 则相反

---

## English

Place script in `kubejs/server_scripts/` directory:

```javascript
InventoryStagesEvents.playerLoggedIn(event => {
    // Default provided "inventory_row_1~3" stages to unlock inventory stages
    event.removeSetRow1Stage("inventory_row_1")  // Remove "inventory_row_1" stage requirement for row 1
    event.removeSetRow2Stage("inventory_row_2")  // Remove "inventory_row_2" stage requirement for row 2
    event.removeSetRow3Stage("inventory_row_3")  // Remove "inventory_row_3" stage requirement for row 3
    // Default player has inventory_hotbar=player hotbar is not disabled by default
    /* event.removeSetRow4Stage("inventory_hotbar")  // Remove "inventory_hotbar" stage requirement for row 4[hotbar] */
    
    event.addSetRow1Stage("1")  // Add "1" stage requirement for row 1
    event.addSetRow2Stage("2")  // Add "2" stage requirement for row 2
    event.addSetRow3Stage("3")  // Add "3" stage requirement for row 3
    /* event.addSetRow4Stage("4")  // Add "4" stage requirement for row 4[hotbar] */
})

// Call global method to auto-reload config, making /reload take effect immediately
reloadInventoryStages.run()
```

### API Methods

- `event.addSetRow1Stage(stageName)` - Add stage requirement for inventory row 1
- `event.addSetRow2Stage(stageName)` - Add stage requirement for inventory row 2
- `event.addSetRow3Stage(stageName)` - Add stage requirement for inventory row 3
- `event.removeSetRow1Stage(stageName)` - Remove stage requirement for inventory row 1
- `event.removeSetRow2Stage(stageName)` - Remove stage requirement for inventory row 2
- `event.removeSetRow3Stage(stageName)` - Remove stage requirement for inventory row 3

### Notes

- By default, no stages are unlocked and only the hotbar is accessible
- `inventory_row_1`, `inventory_row_2`, `inventory_row_3` are default provided stages
- Use game stage commands `add` to gradually unlock inventory rows, `remove` for the opposite