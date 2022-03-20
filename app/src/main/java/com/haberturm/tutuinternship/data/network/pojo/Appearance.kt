package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Appearance (

  @SerializedName("gender"    ) var gender    : String,
  @SerializedName("race"      ) var race      : String?,
  @SerializedName("height"    ) var height    : ArrayList<String>,
  @SerializedName("weight"    ) var weight    : ArrayList<String>,
  @SerializedName("eyeColor"  ) var eyeColor  : String,
  @SerializedName("hairColor" ) var hairColor : String

)