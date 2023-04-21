package io.github.xtyuns.sujectiverepeatermirai

import io.github.xtyuns.sujectiverepeatermirai.handler.PingHandler
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin


/**
 * Mirai Console Entry
 */
object PluginEntry : KotlinPlugin(JvmPluginDescription.loadFromResource()) {
    private lateinit var server: HttpServer

    override fun PluginComponentStorage.onLoad() {
        val vertx = Vertx.vertx()
        server = vertx.createHttpServer()
        server.requestHandler(registerRoute(vertx))
    }

    private fun registerRoute(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/ping").handler(PingHandler::handle)
        return router
    }

    override fun onEnable() {
        server.listen(16010)
    }

    override fun onDisable() {
        server.close()
    }
}
