package io.github.xtyuns.sujectiverepeatermirai.util

import io.vertx.ext.web.RoutingContext

/**
 * Vertx Utils
 */
object VertxUtils {
    fun data(ctx: RoutingContext, data: String) {
        ctx.json(
            mapOf(
                "code" to 0,
                "msg" to null,
                "data" to data
            )
        )
    }

    fun error(ctx: RoutingContext, msg: String) {
        ctx.json(
            mapOf(
                "code" to -1,
                "msg" to msg,
                "data" to null
            )
        )
    }
}
