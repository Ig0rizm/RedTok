package ru.project.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import ru.project.R
import ru.project.app.RedTok
import ru.project.databinding.FragmentAuthBinding
import ru.project.extensions.toast
import ru.project.viewmodels.AuthState
import ru.project.viewmodels.AuthViewModel
import javax.inject.Inject

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding by viewBinding(FragmentAuthBinding::bind)
    private val viewModel: AuthViewModel by viewModels()

    private val getAuthResponse = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val dataIntent = it.data
        handleAuthResponseIntent(dataIntent!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            loginButton.setOnClickListener {
                viewModel.openAuthPage()
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is AuthState.DefaultState -> {}
                is AuthState.LoginState -> openAuthPage(it.intent)
                is AuthState.ErrorState -> toast("Error")
                is AuthState.SuccessAuthState -> findNavController()
                    .navigate(AuthFragmentDirections.actionAuthFragmentToDataFragment())
            }
        }
    }

    private fun openAuthPage(intent: Intent) {
        getAuthResponse.launch(intent)
    }

    private fun handleAuthResponseIntent(intent: Intent) {
        // пытаемся получить ошибку из ответа. null - если все ок
        val exception = AuthorizationException.fromIntent(intent)
        // пытаемся получить запрос для обмена кода на токен, null - если произошла ошибка
        val tokenExchangeRequest = AuthorizationResponse.fromIntent(intent)
            ?.createTokenExchangeRequest()
        when {
            // авторизация завершались ошибкой
            exception != null -> println("HANDLE $exception")
            // авторизация прошла успешно, меняем код на токен
            tokenExchangeRequest != null -> viewModel.performTokenRequest(tokenExchangeRequest)
        }
    }
}