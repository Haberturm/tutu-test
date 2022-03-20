package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Powerstats (

  @SerializedName("intelligence" ) var intelligence : Int,
  @SerializedName("strength"     ) var strength     : Int,
  @SerializedName("speed"        ) var speed        : Int,
  @SerializedName("durability"   ) var durability   : Int,
  @SerializedName("power"        ) var power        : Int,
  @SerializedName("combat"       ) var combat       : Int

)