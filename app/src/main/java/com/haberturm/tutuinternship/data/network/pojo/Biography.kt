package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Biography (

  @SerializedName("fullName"        ) var fullName        : String?           = null,
  @SerializedName("alterEgos"       ) var alterEgos       : String?           = null,
  @SerializedName("aliases"         ) var aliases         : ArrayList<String> = arrayListOf(),
  @SerializedName("placeOfBirth"    ) var placeOfBirth    : String?           = null,
  @SerializedName("firstAppearance" ) var firstAppearance : String?           = null,
  @SerializedName("publisher"       ) var publisher       : String?           = null,
  @SerializedName("alignment"       ) var alignment       : String?           = null

)