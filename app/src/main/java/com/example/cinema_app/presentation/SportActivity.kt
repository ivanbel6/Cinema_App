package com.example.cinema_app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cinema_app.R
import com.example.cinema_app.data.Api.DataClasses.FootballEvent.SportEvent
import com.example.cinema_app.data.DB.MainDb
import com.example.cinema_app.databinding.ActivitySportBinding


private lateinit var TestList: ArrayList<String>

class SportActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySportBinding
    private var isImageRotated = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)
        // Получаем Intent, который запустил эту активность
        val intent = intent
        val sportData: SportEvent? = intent.getSerializableExtra("data") as? SportEvent

        if (sportData != null) {
            InitializeView(sportData)
        }
//        sportData!!.videos.entries

        var addButtonClicked = false
        var rateButtonClicked = false
//        db.getDao().getAllFavouriteFilms().asLiveData().observe(this) {
//                it: List<FavouriteFilm> ->
//            it.forEach { item ->
//                Log.v("TestBb", "Current item : $item")
//            }
//        }
//        binding.AddButton.setOnClickListener {
//            //Создаем модальное окно
//            val modalBottomSheet = ModalBottomSheet(sportData)
//            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
//
//            addButtonClicked = !addButtonClicked // Переключаем состояние
//            val drawableResId = if (addButtonClicked) {
//                R.drawable.plus_active // Иконка при активном состоянии
//            } else {
//                R.drawable.plus_not_active // Иконка при неактивном состоянии
//            }
//            binding.AddButton.setImageResource(drawableResId)
//
//            if (addButtonClicked) {
//                Thread {
//                    db.getDao().insertItem(
//                        FavouriteFilm(
//                            PosterUrl = customData.bgImage.url,
//                            name = customData.name.toString(),
//                            date = customData.date,
//                            time = customData.time,
//                            ageRating = customData.ageRating,
//                            Genre = customData.Genre,
//                            description = customData.description,
//                            backdropURL = customData.backdrop.url
//                        )
//                    )
//                }.start()
//            }
//        }

        binding.RateImageButton.setOnClickListener {
            rateButtonClicked = !rateButtonClicked // Переключаем состояние
            val drawableResId = if (rateButtonClicked) {
                R.drawable.star_active // Иконка при активном состоянии
            } else {
                R.drawable.star_not_active // Иконка при неактивном состоянии
            }
            binding.RateImageButton.setImageResource(drawableResId)
        }


//        val imageView = binding.fillInfoImageView
//        val filmInfoLinear = binding.filmInfoLinear
//        filmInfoLinear.setOnClickListener {
//            if (isImageRotated) {
//                rotateImage(imageView, 90f, 0f)
//                //to do here
//                binding.Cast.visibility = View.INVISIBLE
//                binding.More.visibility = View.INVISIBLE
//
//                binding.hideFromBtn1.visibility = View.GONE
//                binding.hideFromBtn3.visibility = View.GONE
//
//            } else {
//                rotateImage(imageView, 0f, 90f)
//                binding.Cast.visibility = View.VISIBLE
//                binding.More.visibility = View.VISIBLE
//
//                binding.Cast.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
//                binding.More.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
//
//                binding.hideFromBtn1.visibility = View.VISIBLE
//                binding.hideFromBtn3.visibility = View.GONE
//
//                binding.CastRecyclerView.setHasFixedSize(false)
//                binding.CastRecyclerView.layoutManager =
//                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                binding.CastRecyclerView.adapter = SliderCastAdapter(customData.persons)
//
//                binding.Cast.setOnClickListener {
//                    binding.Cast.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
//                    binding.More.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
//
//                    binding.hideFromBtn1.visibility = View.VISIBLE
//                    binding.hideFromBtn3.visibility = View.GONE
//
//                    binding.CastRecyclerView.setHasFixedSize(false)
//                    binding.CastRecyclerView.layoutManager =
//                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                    binding.CastRecyclerView.adapter = SliderCastAdapter(customData.persons)
//
//
//                }
//                binding.More.setOnClickListener {
//                    binding.More.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
//                    binding.Cast.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
//
//                    binding.hideFromBtn1.visibility = View.GONE
//                    binding.hideFromBtn3.visibility = View.VISIBLE
//                }
//            }
//            isImageRotated = !isImageRotated
//        }
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
        binding.sportEventName.text = sportEventData.sportEventName
        binding.date.text = sportEventData.dateTime

        Glide.with(applicationContext)
            .load(sportEventData.team1LogoUrl)
            .into(binding.team1logo)
        binding.team1name.text = sportEventData.team1Name
        binding.team1country.text = sportEventData.team1Country

        Glide.with(applicationContext)
            .load(sportEventData.team2LogoUrl)
            .into(binding.team2logo)
        binding.team2name.text = sportEventData.team2Name
        binding.team2country.text = sportEventData.team2Country
    }

//    @SuppressLint("SimpleDateFormat")
//    private fun InitializeTime(customData: CustomDataClass) {
//        val rawRussiaPremiere = customData.premiere.russia
//        val rawWorldPremiere = customData.premiere.world
//        val russiaPremiereFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        val worldPremiereFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        val parsedRussiaPremiere = russiaPremiereFormat.parse(rawRussiaPremiere)
//        val parsedWorldPremiere = worldPremiereFormat.parse(rawWorldPremiere)
//        val formattedRussiaPremiere = parsedRussiaPremiere?.let {
//            SimpleDateFormat("dd.MM.yyyy").format(
//                it
//            )
//        }
//        val formattedWorldPremiere = parsedWorldPremiere?.let {
//            SimpleDateFormat("dd.MM.yyyy").format(
//                it
//            )
//        }
//
//        binding.MoreRussiaPremier.text = formattedRussiaPremiere
//        binding.MoreWorldPremier.text = formattedWorldPremiere
//    }
}
