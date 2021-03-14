package uz.pdp.realm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import io.realm.*
import uz.pdp.realm.databinding.ItemContactBinding
import uz.pdp.realm.models.ContactObject

class ContactAdapter(
    context: Context,
    var list: RealmResults<ContactObject>,
    var onItemClickListener: OnItemClickListener
) :
    RealmBasedRecyclerViewAdapter<ContactObject, ContactAdapter.Vh>(context, list, true, false) {

    inner class Vh(var itemContactBinding: ItemContactBinding) :
        RealmViewHolder(itemContactBinding.root) {

        fun onBind(contactObject: ContactObject) {
            itemContactBinding.tv1.text = contactObject.name
            itemContactBinding.tv2.text = contactObject.number

            itemContactBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(contactObject)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(contactObject: ContactObject)
    }

    override fun onCreateRealmViewHolder(p0: ViewGroup?, p1: Int): Vh {
        return Vh(ItemContactBinding.inflate(LayoutInflater.from(p0?.context)))
    }

    override fun onBindRealmViewHolder(p0: Vh?, p1: Int) {
        p0?.onBind(list[p1])
    }
}