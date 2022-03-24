package com.haberturm.tutuinternship.data.db

import com.haberturm.tutuinternship.HeroDatabase
import com.haberturm.tutuinternship.data.network.pojo.Powerstats
import com.haberturm.tutuinternship.data.network.pojo.SuperHero
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
        return withContext(Dispatchers.IO) {
            heroQueries.getHeroById(id).executeAsOneOrNull()
        }
    }

    override fun getAllHeroes(): Flow<List<HeroEntity>> {
        return heroQueries.getAllHeroes().asFlow().mapToList()
    }

    override fun insertHero(id: Int, name: String, fullName: String, image: String) {
        heroQueries.insertHero(
            id, name, fullName, image
        )
    }

    override suspend fun clearAllData() {
        withContext(Dispatchers.IO) {
            heroQueries.clearData()
            powerstatsQueries.clearData()
            appearanceQueries.clearData()
        }
    }

    private fun clear() {
        heroQueries.transaction {
            heroQueries.clearData()
            appearanceQueries.clearData()
            powerstatsQueries.clearData()
        }
    }

    override fun insertData(heroes: List<SuperHero>) {
        heroQueries.transaction {
            clear()
            heroes.forEach { hero ->
                insertHero(
                    id = hero.id,
                    name = hero.name,
                    fullName = hero.biography.fullName,
                    image = hero.images.sm
                )

                insertStats(
                    id = hero.id,
                    intelligence = hero.powerstats.intelligence,
                    strength = hero.powerstats.strength,
                    speed = hero.powerstats.speed,
                    durability = hero.powerstats.durability,
                    power = hero.powerstats.power,
                    combat = hero.powerstats.combat
                )
                insertAppearance(
                    id = hero.id,
                    gender = hero.appearance.gender,
                    race = check4null(hero.appearance.race),
                    height = hero.appearance.height[1], //lets trust our api:)
                    weight = hero.appearance.weight[1],
                    eyeColor = hero.appearance.eyeColor,
                    hairColor = hero.appearance.hairColor
                )
            }
        }
    }

    override suspend fun getStatsById(id: Int): hero.herodb.Powerstats? {
        return withContext(Dispatchers.IO) {
            powerstatsQueries.getStatById(id).executeAsOneOrNull()
        }
    }

    override fun insertStats(
        id: Int,
        intelligence: Int,
        strength: Int,
        speed: Int,
        durability: Int,
        power: Int,
        combat: Int
    ) {

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

    override suspend fun getAppearanceById(id: Int): Appearance? {
        return withContext(Dispatchers.IO) {
            appearanceQueries.getApperanceById(id).executeAsOneOrNull()
        }
    }

    override fun insertAppearance(
        id: Int,
        gender: String,
        race: String,
        height: String,
        weight: String,
        eyeColor: String,
        hairColor: String
    ) {
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

    private fun check4null(nullable: String?): String {
        return nullable ?: "unknown"
    }
}