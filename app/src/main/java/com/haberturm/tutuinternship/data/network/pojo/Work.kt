package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Work (

  @SerializedName("occupation" ) var occupation : String,
  @SerializedName("base"       ) var base       : String

)