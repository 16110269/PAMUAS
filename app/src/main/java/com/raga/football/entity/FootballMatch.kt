package com.raga.football.entity

import com.google.gson.annotations.SerializedName
import com.raga.football.entity.Event

data class FootballMatch (
        @SerializedName("events") var events: List<Event>)


