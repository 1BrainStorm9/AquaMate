import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "water_volume")
data class WaterVolume(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: Float
)

@Entity(tableName = "water_tracker")
data class WaterTracker(
    @PrimaryKey val date: String = LocalDate.now().toString(),
    val currentValue: Float = 0f,
    val totalValue: Float = 2000f
)