import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterVolumeDao {
    @Query("SELECT * FROM water_volume ORDER BY id DESC")
    fun getAll(): Flow<List<WaterVolume>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WaterVolume)

    @Delete
    suspend fun delete(item: WaterVolume)

    @Query("DELETE FROM water_volume")
    suspend fun clear()
}


@Dao
interface WaterTrackerDao {

    @Query("SELECT * FROM water_tracker WHERE date = :date LIMIT 1")
    fun getByDate(date: String): Flow<WaterTracker?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(tracker: WaterTracker)
}