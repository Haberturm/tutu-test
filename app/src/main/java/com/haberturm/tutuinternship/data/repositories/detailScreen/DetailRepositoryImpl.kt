package com.haberturm.tutuinternship.data.repositories.detailScreen

import com.haberturm.tutuinternship.data.db.DataSource
import hero.herodb.Appearance
import hero.herodb.HeroEntity
import hero.herodb.Powerstats

class DetailRepositoryImpl(
    private val db: DataSource
) : DetailRepository {
    override suspend fun getHeroById(id: Int): HeroEntity? {
        return db.getHeroById(id)
    }

    override suspend fun getHeroAppearanceById(id: Int): Appearance? {
        return db.getAppearanceById(id)
    }

    override suspend fun getHeroPowerstatsById(id: Int): Powerstats? {
        return db.getStatsById(id)
    }

}