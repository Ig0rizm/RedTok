package ru.project.repository

import android.net.Uri
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration

class AuthRepository {

    private val serviceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse("https://www.reddit.com/api/v1/authorize.compact"),
        Uri.parse("https://www.reddit.com/api/v1/access_token")
    )

    fun getAuthRequest(): AuthorizationRequest {
        val callbackURL = "ru.project.redtok.oauth://localhost/callback".toUri()

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            "UzyrMROzN20sIEtVG-gEvQ",
            "code",
            callbackURL
        ).setScopes("read", "modconfig").build()
    }
}