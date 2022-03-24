package com.haberturm.tutuinternship.data.repositories.detailScreen

import hero.herodb.Appearance
import hero.herodb.HeroEntity
import hero.herodb.Powerstats

interface DetailRepository {
    suspend fun getHeroById(id: Int): HeroEntity?
    suspend fun getHeroAppearanceById(id: Int): Appearance?
    suspend fun getHeroPowerstatsById(id:Int): Powerstats?
}