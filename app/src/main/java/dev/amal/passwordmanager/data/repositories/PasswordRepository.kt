package dev.amal.passwordmanager.data.repositories

//@ViewModelScoped
//class PasswordRepository @Inject constructor(
//    private val passwordDao: PasswordManagerDao
//) {
//
//    val getAllItems: Flow<List<Password>> = passwordDao.getAllItems()
//
//    fun getSelectedItem(itemId: Int): Flow<Password> =
//        passwordDao.getSelectedItem(itemId = itemId)
//
//    suspend fun addItem(password: Password) {
//        passwordDao.addPassword(password = password)
//    }
//
//    fun searchDatabase(searchQuery: String): Flow<List<Password>> =
//        passwordDao.searchDatabase(searchQuery = searchQuery)
//
//    suspend fun deleteTask(password: Password) {
//        passwordDao.deletePassword(password = password)
//    }
//}