package com.ailyan.ergomindpro2.ui.views.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ailyan.ergomindpro2.databinding.ActivityLoginBinding;
import com.ailyan.ergomindpro2.ui.viewModels.LoginViewModel;
import com.ailyan.ergomindpro2.utilities.LoginCallback;
import com.ailyan.ergomindpro2.utilities.Shared;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username;
    private String password;
    private boolean usernameValid;
    private boolean passwordValid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextWatcher usernameListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.error.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkUsername();
                binding.register.setEnabled(usernameValid && passwordValid);
            }
        };
        TextWatcher passwordListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.error.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkPassword();
                binding.register.setEnabled(usernameValid && passwordValid);
            }
        };

        binding.username.addTextChangedListener(usernameListener);
        binding.password.addTextChangedListener(passwordListener);

        binding.register.setOnClickListener(v -> {
            binding.error.setError(null);
            binding.loading.setVisibility(View.VISIBLE);
            username = Objects.requireNonNull(binding.username.getText()).toString();
            password = Objects.requireNonNull(binding.password.getText()).toString();
            LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            loginViewModel.login(username, password, new LoginCallback() {
                @Override
                public void onSuccess() {
                    Shared shared = Shared.getInstance(LoginActivity.this);
                    shared.put(username, "username");
                    shared.put(password, "password");
                    Intent intent = new Intent(LoginActivity.this, SectionsActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                }

                @Override
                public void onFailed(String message) {
                    binding.loading.setVisibility(View.INVISIBLE);
                    binding.error.requestFocus();
                    binding.error.setError(message);
                }
            });
        });
    }

    private void checkUsername() {
        String username = Objects.requireNonNull(binding.username.getText()).toString();
        if (!username.isEmpty()) {
            binding.layoutUsername.setError(null);
            usernameValid = true;
        } else {
            binding.layoutUsername.setError("Veuillez fournir votre nom d'utilisateur");
            usernameValid = false;
        }
    }

    private void checkPassword() {
        String password = Objects.requireNonNull(binding.password.getText()).toString();
        if (!password.isEmpty()) {
            if (password.length() < 3 || password.length() > 20) {
                binding.layoutPassword.setError("Le mot de passe doit comporter entre 8 et 16 caractères");
                passwordValid = false;
            } else {
                binding.layoutPassword.setError(null);
                passwordValid = true;
            }
        } else {
            binding.layoutPassword.setError("Veuillez fournir votre mot de passe");
            passwordValid = false;
        }
    }
}