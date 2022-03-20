package com.haberturm.tutuinternship.data.db

import com.haberturm.tutuinternship.HeroDatabase
import com.haberturm.tutuinternship.data.network.pojo.Powerstats
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import hero.herodb.Appearance
import hero.herodb.HeroEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class DataSourceImpl(
    db: HeroDatabase
) : DataSource {
    private val heroQueries = db.heroEntityQueries
    private val powerstatsQueries = db.powerstatsQueries
    private val appearanceQueries = db.appearanceQueries

    override suspend fun getHeroById(id: Int): HeroEntity? {
        return withContext(Dispatchers.IO){
            heroQueries.getHeroById(id).executeAsOneOrNull()
        }
    }

    override fun getAllHeroes(): Flow<List<HeroEntity>> {
        return heroQueries.getAllHeroes().asFlow().mapToList()
    }

    override suspend fun insertHero(id: Int, name: String, fullName: String, image: String) {
        withContext(Dispatchers.IO){
            heroQueries.insertHero(
                id,
                name,
                fullName,
                image
            )
        }
    }

    override suspend fun getStatsById(id: Int): hero.herodb.Powerstats? {
        return withContext(Dispatchers.IO){
            powerstatsQueries.getStatById(id).executeAsOneOrNull()
        }
    }

    override suspend fun insertStats(
        id: Int,
        intelligence: Int,
        strength: Int,
        speed: Int,
        durability: Int,
        power: Int,
        combat: Int
    ) {
        withContext(Dispatchers.IO){
            powerstatsQueries.insertStats(
                id,
                intelligence,
                strength,
                speed,
                durability,
                power,
                combat
            )
        }
    }

    override suspend fun getAppearanceById(id: Int): Appearance? {
        return withContext(Dispatchers.IO){
            appearanceQueries.getApperanceById(id).executeAsOneOrNull()
        }
    }

    override suspend fun insertAppearance(
        id: Int,
        gender: String,
        race: String,
        height: String,
        weight: String,
        eyeColor: String,
        hairColor: String
    ) {
        withContext(Dispatchers.IO){
            appearanceQueries.insertApperance(
                id,
                gender,
                race,
                height,
                weight,
                eyeColor,
                hairColor
            )
        }
    }
}