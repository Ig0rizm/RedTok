package ru.project.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.project.data.reddit.RedditJsonPost
import java.lang.reflect.Type

class RepliesStringJsonAdapter : JsonDeserializer<Any> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Any? {
        if (json != null) {
            if (json.isJsonObject) {
                if (context != null) {
                    return context.deserialize(json, RedditJsonPost.PostItem.Data.Children.DataX.Replies::class.java)
                }
            }

            if (json.isJsonPrimitive) {
                return json.asString
            }
        }

        return null
    }

}