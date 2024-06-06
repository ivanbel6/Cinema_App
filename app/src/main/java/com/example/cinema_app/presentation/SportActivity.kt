package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.decode.SvgDecoder
import coil.load
import com.bumptech.glide.Glide
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.adapters.ScoreAdapter
import com.example.cinema_app.databinding.ActivitySportBinding
import com.example.cinema_app.domain.UseCases.CreateScoreList


private lateinit var TestList: ArrayList<String>

class SportActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySportBinding
    private var isImageRotated = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем Intent, который запустил эту активность
        val intent = intent
        val sportData: SportEvent = intent.getSerializableExtra("data") as SportEvent

        if (sportData != null) {
            InitializeView(sportData)
        }

        var rateButtonClicked = false

        binding.RateImageButton.setOnClickListener {
            rateButtonClicked = !rateButtonClicked // Переключаем состояние
            val drawableResId = if (rateButtonClicked) {
                R.drawable.star_active// Иконка при активном состоянии
            } else {
                R.drawable.star_not_active // Иконка при неактивном состоянии
            }
            binding.RateImageButton.setImageResource(drawableResId)
        }


        val imageView = binding.fillInfoImageView
        val filmInfoLinear = binding.filmInfoLinear
        filmInfoLinear.setOnClickListener {
            if (isImageRotated) {
                rotateImage(imageView, 90f, 0f)
                //to do here
                binding.League.visibility = View.INVISIBLE
                binding.Score.visibility = View.INVISIBLE

                binding.hideFromBtn1.visibility = View.GONE
                binding.hideFromBtn3.visibility = View.GONE

            } else {
                rotateImage(imageView, 0f, 90f)
                binding.League.visibility = View.VISIBLE
                binding.Score.visibility = View.VISIBLE

                binding.League.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                binding.Score.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                binding.hideFromBtn1.visibility = View.VISIBLE
                binding.hideFromBtn3.visibility = View.GONE

                binding.League.setOnClickListener {
                    binding.League.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                    binding.Score.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                    binding.hideFromBtn1.visibility = View.VISIBLE
                    binding.hideFromBtn3.visibility = View.GONE
                }

                binding.Score.setOnClickListener {
                    binding.Score.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                    binding.League.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                    binding.hideFromBtn1.visibility = View.GONE
                    binding.hideFromBtn3.visibility = View.VISIBLE

                    binding.scoreRecycleView.setHasFixedSize(false)
                    binding.scoreRecycleView.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.scoreRecycleView.adapter = ScoreAdapter(CreateScoreList().create(sportData.type, sportData.firstTimeScore,
                        sportData.fullTimeScore, sportData.extraTimeScore, sportData.penaltyScore, sportData.firstPeriodScore,
                        sportData.secondPeriodScore, sportData.thirdPeriodScore, sportData.fourthPeriodScore, sportData.fifthPeriodScore))
                }
            }
            isImageRotated = !isImageRotated
        }
    }

    private fun rotateImage(imageView: ImageView, fromDegrees: Float, toDegrees: Float) {
        val duration = 500L // milliseconds
        val rotateAnimation = RotateAnimation(
            fromDegrees,
            toDegrees,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = duration
        rotateAnimation.fillAfter = true
        imageView.startAnimation(rotateAnimation)
    }

    private fun InitializeView(sportEventData: SportEvent) {
        Glide.with(applicationContext)
            .load(sportEventData.imageUrl)
            .into(binding.MainImage)
        binding.sportEventName.text = sportEventData.sportEventName
        binding.date.text = sportEventData.dateTime


        Log.d("test", sportEventData.team1LogoUrl)
        Glide.with(applicationContext)
            .load(sportEventData.team1LogoUrl)
            .into(binding.team1logo)
        binding.team1name.text = sportEventData.team1Name
        binding.team1Score.text = sportEventData.team1Score
        binding.team1country.text = sportEventData.team1Country

        Glide.with(applicationContext)
            .load(sportEventData.team2LogoUrl)
            .into(binding.team2logo)
        binding.team2name.text = sportEventData.team2Name
        binding.team2Score.text = sportEventData.team2Score
        binding.team2country.text = sportEventData.team2Country

        binding.matchStatus.text = sportEventData.matchStatus

        binding.leagueFlag.load(sportEventData.leagueFlagUrl)
        {
            decoderFactory {result, options, _ -> SvgDecoder(result.source, options)}
        }
        Glide.with(applicationContext)
            .load(sportEventData.leagueLogoUrl)
            .into(binding.leagueLogo)
        binding.leagueName.text = sportEventData.leagueName
        binding.leagueCountry.text = sportEventData.leagueCountry
        binding.leagueSeason.text = sportEventData.leagueSeason
        binding.leagueType.text = sportEventData.leagueType
    }
}
