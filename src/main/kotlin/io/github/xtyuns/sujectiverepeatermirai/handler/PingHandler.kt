package io.github.xtyuns.sujectiverepeatermirai.handler

import io.github.xtyuns.sujectiverepeatermirai.util.VertxUtils
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.Json
import io.vertx.kotlin.core.json.obj

/**
 * ping server
 */
object PingHandler: Handler<RoutingContext> {
  override fun handle(event: RoutingContext) {
    val result = mapOf(
      Pair("code", 0),
      Pair("msg", ""),
      Pair("data", "pong"),
    )
    VertxUtils.out(event, Json.obj(result).encode())
  }
}
