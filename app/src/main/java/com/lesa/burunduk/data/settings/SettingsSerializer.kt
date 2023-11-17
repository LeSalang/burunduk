package com.lesa.burunduk.data.settings

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer: Serializer<SettingsData> {
    override val defaultValue: SettingsData
        get() = SettingsData()

    override suspend fun readFrom(input: InputStream): SettingsData {
        return try {
            Json.decodeFromString(
                deserializer = SettingsData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            SettingsData()
        }
    }

    override suspend fun writeTo(t: SettingsData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = SettingsData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}