package com.fitareq.harrypotter.data.ui.main

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import com.fitareq.harrypotter.data.models.Character
import com.fitareq.harrypotter.data.models.Data
import com.fitareq.harrypotter.data.ui.details.DetailsActivity
import com.fitareq.harrypotter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setTitleTextColor(Color.WHITE)

        viewModel.characters.observe(this) {
            when (it) {
                is Data.Loading -> {
                    showLoadingScreen()
                }

                is Data.Success -> {
                    loadAdapter(it.data)
                    hideLoadingScreen()
                }

                is Data.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    hideLoadingScreen()
                }
            }
        }
        viewModel.getAllCharacters()
    }

    private lateinit var adapter: MainAdapter
    private fun loadAdapter(data: List<Character>) {
        adapter = MainAdapter(data) {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
        binding.mainRv.adapter = adapter
    }

    private var dialog: Dialog? = null
    fun showLoadingScreen() {
        if (dialog == null) {
            dialog = Dialog(this)
            dialog?.apply {
                setContentView(ProgressBar(this@MainActivity))
                window?.apply {
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setLayout(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }
                setCancelable(false)

            }
        }
        dialog!!.show()
    }

    fun hideLoadingScreen() {
        dialog?.dismiss()
    }
}