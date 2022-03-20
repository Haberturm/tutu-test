package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Images (

  @SerializedName("xs" ) var xs : String,
  @SerializedName("sm" ) var sm : String,
  @SerializedName("md" ) var md : String,
  @SerializedName("lg" ) var lg : String

)