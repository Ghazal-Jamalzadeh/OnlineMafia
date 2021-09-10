package com.jmzd.ghazal.onlinemafia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.onlinemafia.R
import com.jmzd.ghazal.onlinemafia.dataModel.PlayerDataModel
import com.jmzd.ghazal.onlinemafia.databinding.PlayersListItemBinding
import com.jmzd.ghazal.onlinemafia.repository.Api
import com.jmzd.ghazal.onlinemafia.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PlayersListAdapter (val context: Context, val list: ArrayList<PlayerDataModel>
, val change: GetChangeItems,val tableName:String , val tablePass:String)
    : RecyclerView.Adapter<PlayersListAdapter.viewholder>() {//, val user: String
    // اینجا ورودی لیست به جای list باید ArrayList تعریف شود تا بتوانیم remove را فراخوانی کنیم برای delete کردن ایتم ها
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.players_list_item, parent, false
        )
        )
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val data=list.get(position)
        holder.items.data=data
        holder.items.playersNameTv.text=data.playerName

//        holder.items.ImMines.setOnClickListener {
//            val Com = CompositeDisposable() // میشد این قسمت ها را هم با mutableLiveData و ... پیاده سازی کرد.
//            Repository.CustomResponse.request(Api.invoke().addCart(data.idproduct,"1",user,"m"),Com){
//                Log.e("mosbar",it.status)
//                if(it.status.equals("ok")){
//                    // holder.items.TvPrice.text=it.price[0].price + " تومان "
//                    holder.items.TvPrice.text=it.price_post + " تومان "
//
//                    change.getChange(0)
//                }
//            }
//        }

//        holder.items.ImMosbat.setOnClickListener {
//            val Com = CompositeDisposable()
//            Repository.CustomResponse.request(Api.invoke().addCart(data.idproduct,"1",user,"add"),Com){
//                if(it.status.equals("ok")){
//                    Log.e("mosbar",it.status)
//                    //holder.items.TvPrice.text=it.price[0].price + " تومان "
//                    holder.items.TvPrice.text=it.price_post + " تومان "
//                    change.getChange(0)
//                }
//            }
//        }


        holder.items.recycleBin.setOnClickListener {
            val Com = CompositeDisposable()
            Repository.CustomResponse.request(Api.invoke().deletePlayerApi(tableName ,tablePass , data.playerName ),Com){
                if(it.status.equals("ok")){
                    list.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,list.size) // delete from db and list
                    change.getChange()
                }else{
                    Log.d("delete" , "error")
                }
            }

        }


    }

    interface GetChangeItems{ // برای زمانی که آیتمی را حذف یا اضافه می کنیم از سبد خرید و باید به ما اطلاع داده شود چون مجموع قیمت تغییر می کند.
        fun getChange()
    }

    class viewholder(val items: PlayersListItemBinding) : RecyclerView.ViewHolder(items.root)


}