package com.fitareq.harrypotter.data.ui.details

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.fitareq.harrypotter.R
import com.fitareq.harrypotter.data.models.Character
import com.fitareq.harrypotter.data.models.Data
import com.fitareq.harrypotter.data.ui.main.MainViewModel
import com.fitareq.harrypotter.databinding.ActivityDetailsBinding
import com.fitareq.harrypotter.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Details"
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawable = binding.toolbar.navigationIcon
        drawable?.setTint(Color.WHITE)


        viewModel.character.observe(this) {
            when (it) {
                is Data.Loading -> {
                    showLoadingScreen()
                }

                is Data.Success -> {
                    loadData(it.data[0])
                    hideLoadingScreen()
                }

                is Data.Error -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    hideLoadingScreen()
                }
            }
        }

        viewModel.getCharacter(id!!)
    }

    private fun loadData(character: Character) {
        val image = character.image?:""
        val name = character.name?:"No Data"
        val speciesValue = character.species?:"No Data"
        val genderValue = character.gender?:"No Data"
        val houseValue = character.house?:"No Data"
        val dobValue = character.dateOfBirth?:"No Data"
        val actorValue = character.actor?:"No Data"

        binding.apply {
        profileImage.clipToOutline = true
            if (image.isNotEmpty())
                Picasso.get().load(image).into(profileImage)
            profileName.text = name
            species.text = getString(R.string.species, speciesValue)
            gender.text = getString(R.string.gender, genderValue)
            house.text = getString(R.string.house, houseValue)
            dob.text = getString(R.string.date_of_birth, dobValue)
            actor.text = getString(R.string.actor, actorValue)
        }


    }

    private fun showLoadingScreen() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            mainLayout.visibility = View.GONE
        }
    }

    private fun hideLoadingScreen() {
        binding.apply {
            progressBar.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}