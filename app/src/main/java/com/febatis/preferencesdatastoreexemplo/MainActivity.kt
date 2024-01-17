package com.febatis.preferencesdatastoreexemplo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import com.febatis.preferencesdatastoreexemplo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exampleValueKey = stringPreferencesKey("example_value_key")
        val exampleCounterFlow: Flow<String?> = dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[exampleValueKey]
            }

        runBlocking(Dispatchers.IO) {
            val myValue = exampleCounterFlow.first()
            Log.i("MyTag", "Value: $myValue")
            binding.textView.text = "Valor salvo em \"example_value_key\" é: $myValue"
        }

        binding.btnNextPage.setOnClickListener {
            startActivity(Intent(this, PageTwoActivity::class.java))
        }
    }
}