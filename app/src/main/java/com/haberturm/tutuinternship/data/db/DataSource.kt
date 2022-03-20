package com.haberturm.tutuinternship.data.db

import hero.herodb.Appearance
import hero.herodb.HeroEntity
import hero.herodb.Powerstats
import kotlinx.coroutines.flow.Flow


interface DataSource {
    //heroEntity
    suspend fun getHeroById(id: Int): HeroEntity?
    fun getAllHeroes(): Flow<List<HeroEntity>>
    suspend fun insertHero(
        id: Int,
        name: String,
        fullName: String,
        image: String,
    )
    //powerstats
    suspend fun getStatsById(id: Int): Powerstats?
    suspend fun insertStats(
        id: Int,
        intelligence: Int,
        strength: Int,
        speed: Int,
        durability: Int,
        power: Int,
        combat: Int
    )
    //appearance
    suspend fun getAppearanceById(id: Int): Appearance?
    suspend fun insertAppearance(
        id: Int,
        gender: String,
        race: String,
        height: String,
        weight: String,
        eyeColor: String,
        hairColor: String
    )
}