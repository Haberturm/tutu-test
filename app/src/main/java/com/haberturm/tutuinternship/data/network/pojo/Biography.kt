package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Biography (

  @SerializedName("fullName"        ) var fullName        : String,
  @SerializedName("alterEgos"       ) var alterEgos       : String,
  @SerializedName("aliases"         ) var aliases         : ArrayList<String>,
  @SerializedName("placeOfBirth"    ) var placeOfBirth    : String,
  @SerializedName("firstAppearance" ) var firstAppearance : String,
  @SerializedName("publisher"       ) var publisher       : String?,
  @SerializedName("alignment"       ) var alignment       : String

)