package io.github.xtyuns.sujectiverepeatermirai.handler

import io.github.xtyuns.sujectiverepeatermirai.util.VertxUtils
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot

/**
 * 发送群消息
 */
object GroupMessageHandler: Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val data = event.body().asJsonObject().mapTo(Data::class.java)
        Bot.instances.filter { data.sender.contains(it.id) }
            .forEach { it.getGroup(data.group)?.run { runBlocking { sendMessage(data.msg) } } }
        VertxUtils.data(event, "success")
    }

    data class Data(val sender: Array<Long>, val group: Long, val msg: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Data

            if (!sender.contentEquals(other.sender)) return false
            if (group != other.group) return false
            return msg == other.msg
        }

        override fun hashCode(): Int {
            var result = sender.contentHashCode()
            result = 31 * result + group.hashCode()
            result = 31 * result + msg.hashCode()
            return result
        }
    }
}
