package io.github.xtyuns.sujectiverepeatermirai.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value
import java.util.*

/**
 * 插件配置
 */
object PluginConfig: AutoSavePluginConfig("config") {
    var port by value(16010)
    var key by value(UUID.randomUUID().toString().substring(0, 8))
}
