package uz.pdp.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfiguration =
            RealmConfiguration.Builder()
                .name("test.db")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}