package io.github.xtyuns.sujectiverepeatermirai.handler

import io.github.xtyuns.sujectiverepeatermirai.config.PluginConfig
import io.github.xtyuns.sujectiverepeatermirai.util.VertxUtils
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

/**
 * 认证
 */
object VerifyHandler : Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val key = event.body().asJsonObject().getString("key")
        if (PluginConfig.key != key) {
            VertxUtils.error(event, "Invalid key")
            return
        } else {
            event.next()
        }
    }
}
