package com.example.cinema_app.data.Api.DataClasses.CyberSportEvent

import com.bumptech.glide.load.model.GlideUrl
import java.io.Serializable

data class ProcessedCyberSportEvent(
    val disciplineName: String,
    val team1Name: String,
    val team2Name: String,
    val time: String,
    val date: String,
    val team1LogoUrl: GlideUrl?,
    val team2LogoUrl: GlideUrl?,
): Serializable
