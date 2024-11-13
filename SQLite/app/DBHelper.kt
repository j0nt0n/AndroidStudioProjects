import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "mydb"
        const val DB_VERSION = 1
        const val DB_TABLE = "goods"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_COUNT = "count"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Создание таблицы
        db.execSQL(
            "CREATE TABLE $DB_TABLE (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME TEXT, " +
                    "$COLUMN_PRICE INTEGER, " +
                    "$COLUMN_COUNT INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обновление таблицы
        db.execSQL("DROP TABLE IF EXISTS $DB_TABLE")
        onCreate(db)
    }
}