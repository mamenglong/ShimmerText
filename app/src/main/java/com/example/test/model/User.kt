package com.example.test.model

import androidx.databinding.ObservableField

data class User(var name: ObservableField<String> = ObservableField(), var sex:ObservableField<String> = ObservableField())