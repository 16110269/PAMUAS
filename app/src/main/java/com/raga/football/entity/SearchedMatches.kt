package com.raga.football.entity

import com.google.gson.annotations.SerializedName
import com.raga.football.entity.Event

/**
 * Created by muhrahmatullah on 15/09/18.
 */
data class SearchedMatches(
        @SerializedName("event") var events: List<Event>
)