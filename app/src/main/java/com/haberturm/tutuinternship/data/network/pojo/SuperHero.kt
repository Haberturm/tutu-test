package com.haberturm.tutuinternship.data.network.pojo

import com.google.gson.annotations.SerializedName
import com.haberturm.tutuinternship.data.network.pojo.*


data class SuperHero (

  @SerializedName("id"          ) var id          : Int?         = null,
  @SerializedName("name"        ) var name        : String?      = null,
  @SerializedName("slug"        ) var slug        : String?      = null,
  @SerializedName("powerstats"  ) var powerstats  : Powerstats?  = Powerstats(),
  @SerializedName("appearance"  ) var appearance  : Appearance?  = Appearance(),
  @SerializedName("biography"   ) var biography   : Biography?   = Biography(),
  @SerializedName("work"        ) var work        : Work?        = Work(),
  @SerializedName("connections" ) var connections : Connections? = Connections(),
  @SerializedName("images"      ) var images      : Images?      = Images()

)