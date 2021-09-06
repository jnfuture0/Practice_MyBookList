package com.example.mybooklist.datasource.database

//@Dao
//interface BookDao {
//    @Query("SELECT * FROM databasebook")
//    fun getBooks(): List<DatabaseBook>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg books: DatabaseBook)
//
//    @Query("DELETE from databasebook")
//    fun deleteAll()
//}
//
////@TypeConverters(Converters::class)
//@Database(entities = [DatabaseBook::class], version = 2)
//abstract class BooksDatabase : RoomDatabase() {
//    abstract val bookDao: BookDao
//}




//private lateinit var INSTANCE: BooksDatabase
//
//
//object DatabaseFactory{
//    fun build(context: Context): BooksDatabase{
//        return  Room.databaseBuilder(
//            context.applicationContext,
//            BooksDatabase::class.java,
//            "books"
//        ).build()
//    }
//}
//
//fun getDatabase(context: Context): BooksDatabase {
//    synchronized(BooksDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                BooksDatabase::class.java,
//                "books"
//            ).build()
//        }
//        return INSTANCE
//    }
//}