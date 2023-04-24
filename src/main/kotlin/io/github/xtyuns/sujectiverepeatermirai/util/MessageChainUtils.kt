package io.github.xtyuns.sujectiverepeatermirai.util

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.message.data.*
import java.net.URL

/**
 * MessageChain Utils
 * @see net.mamoe.mirai.message.data.MessageContent
 *
 * <pre>
 * [
 *     {"type": "At", "data": {"target": "$QQ"}},
 *     {"type": "PlainText", "data": {"content": "$Text"}},
 *     {"type": "Image", "data": {"imageUrl": "$ImageUrl"}}
 * ]
 * </pre>
 */
object MessageChainUtils {
    fun deserialize(bot: Contact, msg: Array<Msg>): MessageChain {
        val messageChainBuilder = MessageChainBuilder()
        for (msgObj in msg) {
            when (msgObj.type) {
                "PlainText" -> {
                    messageChainBuilder.add(PlainText(msgObj.data["content"].toString()))
                }
                "Image" -> {
                    val imageUrl = msgObj.data["imageUrl"].toString()
                    val uploadImage = runBlocking { bot.uploadImage(URL(imageUrl).openStream()) }
                    messageChainBuilder.add(Image(uploadImage.imageId))
                }
                "At" -> {
                    messageChainBuilder.add(At(msgObj.data["target"].toString().toLong()))
                }
            }
        }
        return messageChainBuilder.build()
    }

    data class Msg(val type: String, val data: Map<String, Any>)
}
