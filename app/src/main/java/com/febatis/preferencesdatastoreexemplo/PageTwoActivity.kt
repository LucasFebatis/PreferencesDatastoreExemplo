package com.febatis.preferencesdatastoreexemplo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.febatis.preferencesdatastoreexemplo.databinding.ActivityPageTwoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.UUID

class PageTwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPageTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveRandomInformation.setOnClickListener {
            runBlocking(Dispatchers.IO) {
                updatePreferenceWithNewRandomUUID()
            }

            Toast.makeText(
                this,
                "Salvo, abra e feche o app para ver o novo valor salvo",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private suspend fun updatePreferenceWithNewRandomUUID() {
        val uniqueId = UUID.randomUUID().toString()

        val exampleValueKey = stringPreferencesKey("example_value_key")
        dataStore.edit { settings ->
            settings[exampleValueKey] = uniqueId
        }
    }
}