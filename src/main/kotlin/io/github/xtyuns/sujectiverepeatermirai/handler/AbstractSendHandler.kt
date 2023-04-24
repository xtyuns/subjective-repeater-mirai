package io.github.xtyuns.sujectiverepeatermirai.handler

import io.github.xtyuns.sujectiverepeatermirai.util.VertxUtils
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Contact

/**
 * 发送消息流程
 */
abstract class AbstractSendHandler: Handler<RoutingContext> {
    override fun handle(event: RoutingContext) {
        val data = event.body().asJsonObject().mapTo(Data::class.java)
        Bot.instances.filter { data.sender.contains(it.id) }
            .forEach {
                getTarget(it, data)?.run { runBlocking { sendMessage(data.msg) } }
            }
        VertxUtils.data(event, "success")
    }

    abstract fun getTarget(bot: Bot, data: Data): Contact?

    data class Data(val sender: Array<Long>, val target: Long, val msg: String) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Data

            if (!sender.contentEquals(other.sender)) return false
            if (target != other.target) return false
            return msg == other.msg
        }

        override fun hashCode(): Int {
            var result = sender.contentHashCode()
            result = 31 * result + target.hashCode()
            result = 31 * result + msg.hashCode()
            return result
        }
    }
}
