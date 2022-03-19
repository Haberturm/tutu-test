package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Appearance (

  @SerializedName("gender"    ) var gender    : String?           = null,
  @SerializedName("race"      ) var race      : String?           = null,
  @SerializedName("height"    ) var height    : ArrayList<String> = arrayListOf(),
  @SerializedName("weight"    ) var weight    : ArrayList<String> = arrayListOf(),
  @SerializedName("eyeColor"  ) var eyeColor  : String?           = null,
  @SerializedName("hairColor" ) var hairColor : String?           = null

)