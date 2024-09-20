package io.github.dkexception.kmm.aqiapp.domain.models.profile

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class AuthUserData(
    val name: String,
    val emailId: String
) {

    fun encodeToString(): String = Json.encodeToString(
        serializer = serializer(),
        value = this
    )

    companion object {

        fun decodeFromString(stringData: String): AuthUserData = Json.decodeFromString(stringData)
    }
}
