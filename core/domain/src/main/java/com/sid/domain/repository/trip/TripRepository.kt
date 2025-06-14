package com.sid.domain.repository.trip

import com.aurora.data.data.dao.TripDao
import com.aurora.data.data.entity.trip.TripEntity
import com.aurora.data.data.entity.trip.TripPlanningStage
import com.aurora.data.data.entity.trip.getTripPlanningStage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface TripRepository {
    /**
     * Creates a new trip in the database.
     * @param trip The TripEntity to insert.
     * @return The ID of the newly inserted trip.
     */
    suspend fun createTrip(trip: TripEntity): Long

    suspend fun getTripById(tripId: Long?): TripEntity?

    /**
     * Fetches the most recently created trip from the database.
     * Assumes "latest" means the one with the most recent start date.
     * @return A Flow emitting the latest TripEntity, or null if no trips exist.
     */
    fun getLatestTrip(): Flow<TripEntity?>

    /**
     * Determines the planning stage of a given trip.
     * @param trip The TripEntity to evaluate.
     * @return The TripPlanningStage of the trip.
     */
    fun getTripStage(trip: TripEntity?): TripPlanningStage

    /**
     * Updates an existing trip in the database.
     * @param trip The TripEntity with updated information.
     * @return True if the update was successful (one or more rows affected), false otherwise.
     */
    suspend fun updateTrip(trip: TripEntity): Boolean
}

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
) : TripRepository {
    override suspend fun createTrip(trip: TripEntity): Long {
        Timber.d("createTrip")
        return tripDao.insert(trip)
    }

    override suspend fun getTripById(tripId: Long?): TripEntity? {
        Timber.d("Fetching trip by ID: $tripId")
        return tripDao.getTripById(tripId)
    }

    override fun getLatestTrip(): Flow<TripEntity?> {
        return tripDao.getAll().map { trips ->
            trips.firstOrNull()
        }
    }

    override fun getTripStage(trip: TripEntity?): TripPlanningStage {
        return getTripPlanningStage(trip)
    }

    override suspend fun updateTrip(trip: TripEntity): Boolean {
        return tripDao.update(trip) > 0
    }
}
