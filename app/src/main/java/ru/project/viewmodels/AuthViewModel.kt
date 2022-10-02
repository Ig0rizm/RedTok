package ru.project.viewmodels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.TokenRequest
import ru.project.extensions.default
import ru.project.extensions.set
import ru.project.repository.AuthRepository

sealed class AuthState {
    class DefaultState: AuthState()
    class LoginState(val intent: Intent): AuthState()
    class ErrorState: AuthState()
    class SuccessAuthState : AuthState()
}

class AuthViewModel(app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<AuthState>().default(AuthState.DefaultState())
    val sharedPref = app.getSharedPreferences("RedTokPreference", Context.MODE_PRIVATE)

    init {
        if (sharedPref.getString("apiToken", "") != "")
            state.set(AuthState.SuccessAuthState())
    }

    var tokenType: String = "bearer"

    private val authService = AuthorizationService(app)
    private val authRepository = AuthRepository()

    fun openAuthPage() {
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(false)
            .build()

        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRepository.getAuthRequest(),
            customTabsIntent
        )

        state.set(AuthState.LoginState(openAuthPageIntent))
    }

    fun performTokenRequest(tokenRequest: TokenRequest) {
        val authCode = tokenRequest.authorizationCode!!
        authService.performTokenRequest(tokenRequest,
            ClientSecretBasic(authCode)
        ) { response, ex ->
            when {
                response != null -> {
                    sharedPref.edit().putString("apiToken", "$tokenType ${response.accessToken}").apply()
                    state.set(AuthState.SuccessAuthState())
                }
                else -> {
                    state.set(AuthState.ErrorState())
                    Log.e("RedTok", ex.toString())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authService.dispose()
    }
}