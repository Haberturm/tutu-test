package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName


data class Connections (

  @SerializedName("groupAffiliation" ) var groupAffiliation : String,
  @SerializedName("relatives"        ) var relatives        : String

)