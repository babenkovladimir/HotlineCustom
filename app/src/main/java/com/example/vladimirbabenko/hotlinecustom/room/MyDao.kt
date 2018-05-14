import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.example.vladimirbabenko.hotlinecustom.entity.User

@Dao interface MyDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUsers(vararg users: User)

  @Insert fun insertBothUsers(user1: User, user2: User)

  @Insert fun insertUsersAndFriends(user: User, friends: List<User>)
}
