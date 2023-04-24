package io.github.xtyuns.sujectiverepeatermirai.handler

import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Contact

/**
 * 发送群消息
 */
object GroupMessageHandler: AbstractSendHandler() {
    override fun getTarget(bot: Bot, data: Data): Contact? {
        return bot.getGroup(data.target)
    }
}
