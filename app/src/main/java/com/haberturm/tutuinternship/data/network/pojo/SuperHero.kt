package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName
import com.haberturm.tutuinternship.data.network.pojo.*


data class SuperHero (

  @SerializedName("id"          ) var id          : Int,
  @SerializedName("name"        ) var name        : String,
  @SerializedName("slug"        ) var slug        : String,
  @SerializedName("powerstats"  ) var powerstats  : Powerstats,
  @SerializedName("appearance"  ) var appearance  : Appearance,
  @SerializedName("biography"   ) var biography   : Biography,
  @SerializedName("work"        ) var work        : Work,
  @SerializedName("connections" ) var connections : Connections,
  @SerializedName("images"      ) var images      : Images

)