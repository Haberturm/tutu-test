package com.haberturm.tutuinternship.util

import com.haberturm.tutuinternship.ui.model.AppearanceUi
import com.haberturm.tutuinternship.ui.model.HeroUi
import com.haberturm.tutuinternship.ui.model.PowerstatsUi
import hero.herodb.Appearance
import hero.herodb.HeroEntity
import hero.herodb.Powerstats

object Util {
    fun Appearance.toAppearanceUi(): AppearanceUi {
      return AppearanceUi(
          Gender = gender,
          Race = race,
          Height = height,
          Weight = weight,
          Eyes = eyeColor,
          Hair = hairColor,
      )
    }

    fun Powerstats.toPowerstatsUi(): PowerstatsUi{
        return PowerstatsUi(
            Intelligence = intelligence,
            Strength = strength,
            Speed = speed,
            Durability = durability,
            Power = power,
            Combat = combat,
        )
    }

    fun HeroEntity.toHeroUi(): HeroUi{
        return HeroUi(
            name = name,
            fullName = fullName,
            image = image
        )
    }
}