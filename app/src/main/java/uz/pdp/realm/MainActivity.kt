package uz.pdp.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmList
import uz.pdp.realm.adapters.ContactAdapter
import uz.pdp.realm.databinding.ActivityMainBinding
import uz.pdp.realm.models.ContactObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var realm: Realm

    private val TAG = "MainActivity"
    private lateinit var contactAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        realm = Realm.getDefaultInstance()


        binding.button.setOnClickListener {
            val name = binding.edit1.text.toString()
            val number = binding.edit2.text.toString()
            val contact = ContactObject()
            contact.name = name
            contact.number = number

            realm.executeTransaction {
                it.insert(contact)
            }
        }
        val realmResults =
            realm.where(ContactObject::class.java).findAllSorted(ContactObject.CONTACT_NUMBER)
        contactAdapter =
            ContactAdapter(this, realmResults, object : ContactAdapter.OnItemClickListener {
                override fun onItemClick(contactObject: ContactObject) {
//                realm.executeTransaction {
//                    contactObject.name = "Kotlin"
//                    realm.insertOrUpdate(contactObject)
//                }

                    realm.executeTransaction {
                        contactObject.deleteFromRealm()
//                    val contact = it.where(ContactObject::class.java).equalTo(ContactObject.CONTACT_NUMBER, contactObject.number).findFirst()
//                    contact?.deleteFromRealm()
                    }
                }
            })

        binding.rv.setAdapter(contactAdapter)

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}