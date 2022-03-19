package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Powerstats (

  @SerializedName("intelligence" ) var intelligence : Int? = null,
  @SerializedName("strength"     ) var strength     : Int? = null,
  @SerializedName("speed"        ) var speed        : Int? = null,
  @SerializedName("durability"   ) var durability   : Int? = null,
  @SerializedName("power"        ) var power        : Int? = null,
  @SerializedName("combat"       ) var combat       : Int? = null

)