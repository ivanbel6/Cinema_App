package com.example.cinema_app.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinema_app.data.Api.DataClasses.CustomDataClass
import com.example.cinema_app.data.SliderCastAdapter
import com.example.cinema_app.databinding.ActivityFilmBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var TestList: ArrayList<String>

class FilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmBinding
    private var isImageRotated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем Intent, который запустил эту активность
        val intent = intent
        val customData: CustomDataClass? = intent.getSerializableExtra("param1") as? CustomDataClass
        Log.v("Test_film", customData.toString())

        Glide.with(applicationContext)
            .load(customData!!.bgImage.url)
            .into(binding.MainImage)

        binding.Description.text = customData.description


        val imageView = binding.fillInfoImageView
        val filmInfoLinear = binding.filmInfoLinear
        filmInfoLinear.setOnClickListener {
            if (isImageRotated) {
                rotateImage(imageView, 90f, 0f)
                //to do here
                binding.Cast.visibility = View.INVISIBLE
                binding.Trailer.visibility = View.INVISIBLE
                binding.More.visibility = View.INVISIBLE

                binding.hideFromBtn1.visibility = View.GONE
                binding.hideFromBtn2.visibility = View.GONE
                binding.hideFromBtn3.visibility = View.GONE

            } else {
                rotateImage(imageView, 0f, 90f)
                binding.Cast.visibility = View.VISIBLE
                binding.Trailer.visibility = View.VISIBLE
                binding.More.visibility = View.VISIBLE

                binding.Cast.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                binding.Trailer.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
                binding.More.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                binding.hideFromBtn1.visibility = View.VISIBLE
                binding.hideFromBtn2.visibility = View.GONE
                binding.hideFromBtn3.visibility = View.GONE

                binding.CastRecyclerView.setHasFixedSize(false)
                binding.CastRecyclerView.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.CastRecyclerView.adapter = SliderCastAdapter(customData.persons)

                binding.Cast.setOnClickListener {
                    binding.Cast.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                    binding.Trailer.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
                    binding.More.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                    binding.hideFromBtn1.visibility = View.VISIBLE
                    binding.hideFromBtn2.visibility = View.GONE
                    binding.hideFromBtn3.visibility = View.GONE

                    binding.CastRecyclerView.setHasFixedSize(false)
                    binding.CastRecyclerView.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.CastRecyclerView.adapter = SliderCastAdapter(customData.persons)


                }
                binding.Trailer.setOnClickListener {
                    binding.Trailer.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                    binding.Cast.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
                    binding.More.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                    binding.hideFromBtn1.visibility = View.GONE
                    binding.hideFromBtn2.visibility = View.VISIBLE
                    binding.hideFromBtn3.visibility = View.GONE
                }
                binding.More.setOnClickListener {
                    binding.More.setTextColor(android.graphics.Color.parseColor("#B2BDCA"))
                    binding.Trailer.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))
                    binding.Cast.setTextColor(android.graphics.Color.parseColor("#66B2BDCA"))

                    binding.hideFromBtn1.visibility = View.GONE
                    binding.hideFromBtn2.visibility = View.GONE
                    binding.hideFromBtn3.visibility = View.VISIBLE
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
}