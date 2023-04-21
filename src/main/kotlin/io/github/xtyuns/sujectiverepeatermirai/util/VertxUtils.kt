package io.github.xtyuns.sujectiverepeatermirai.util

import io.vertx.ext.web.RoutingContext

/**
 * Vertx Utils
 */
object VertxUtils {
  fun out(ctx: RoutingContext, msg: String) {
    ctx.response().putHeader("Content-Type", "application/json; charset=utf-8")
      .end(msg)
  }
}
