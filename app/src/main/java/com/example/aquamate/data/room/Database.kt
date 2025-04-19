import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WaterVolume::class, WaterTracker::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun waterVolumeDao(): WaterVolumeDao
    abstract fun waterTrackerDao(): WaterTrackerDao
}
