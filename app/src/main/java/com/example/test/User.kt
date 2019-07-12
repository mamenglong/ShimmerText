package com.example.test

import androidx.databinding.ObservableField

data class User(var name: ObservableField<String> = ObservableField(), var sex:ObservableField<String> = ObservableField())