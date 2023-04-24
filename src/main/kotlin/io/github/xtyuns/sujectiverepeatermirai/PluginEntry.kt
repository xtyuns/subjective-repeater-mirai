package io.github.xtyuns.sujectiverepeatermirai

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.xtyuns.sujectiverepeatermirai.config.PluginConfig
import io.github.xtyuns.sujectiverepeatermirai.handler.GroupMessageHandler
import io.github.xtyuns.sujectiverepeatermirai.handler.PingHandler
import io.github.xtyuns.sujectiverepeatermirai.handler.VerifyHandler
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.json.jackson.DatabindCodec
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
        PluginConfig.reload()
        logger.info("plugin [subjective-repeater-mirai] load config, port: ${PluginConfig.port}, key: ${PluginConfig.key}")

        DatabindCodec.mapper()
            .registerKotlinModule()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val vertx = Vertx.vertx()
        server = vertx.createHttpServer()
        server.requestHandler(registerRoute(vertx))
    }

    private fun registerRoute(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.get("/ping").handler(PingHandler)

        router.post("/send/group")
            .handler(BodyHandler.create())
            .handler(VerifyHandler)
            .handler(GroupMessageHandler)

        return router
    }

    override fun onEnable() {
        server.listen(PluginConfig.port)
    }

    override fun onDisable() {
        server.close()
    }
}
