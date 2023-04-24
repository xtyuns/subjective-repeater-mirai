package io.github.xtyuns.sujectiverepeatermirai.handler

import io.github.xtyuns.sujectiverepeatermirai.util.VertxUtils
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

/**
 * ping server
 */
object PingHandler: Handler<RoutingContext> {
  override fun handle(event: RoutingContext) {
    VertxUtils.data(event, "pong")
  }
}
