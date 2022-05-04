package com.example.testrretrofit

import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.figure.retrofit.RetroServiceInterface
import com.app.figure.retrofit.retroInstance
import com.example.testrretrofit.model.InfoUser
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterUser(private val context: Context)  : RecyclerView.Adapter<AdapterUser.viewHolder>() {

   private var list : ArrayList<InfoUser>? = null

    fun getList( list: ArrayList<InfoUser>){
        this.list = list
        notifyDataSetChanged()
    }

    class viewHolder(view : View) : RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.name)
        var email = view.findViewById<TextView>(R.id.usernames)
        var diachi = view.findViewById<TextView>(R.id.diachi)
        var sdt = view.findViewById<TextView>(R.id.sdt)
        var mMenus = view.findViewById<ImageView>(R.id.mMenus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var person = list!!.get(position)
        Log.e("person",person.username)
        holder.name.text = person.fullname
        holder.email.text = person.username
        holder.diachi.text = person.diachi
        holder.sdt.text = person.sdt
        holder.mMenus.setOnClickListener { mPopMenu(it,person,position) }
    }


    private fun mPopMenu(it: View?, person: InfoUser, position: Int) {
        var popupMenu = PopupMenu(context,it)
        popupMenu.inflate(R.menu.show_menu)
        var sharedPreferences = context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
        var user = sharedPreferences.getString("info","")


        var gson  = Gson()
        var info : InfoUser = gson.fromJson(user,InfoUser::class.java)


        popupMenu.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.editText -> {
                    if(info.id == person.id || info.phanquyen == 0){
                        var layoutInflater = LayoutInflater.from(context)
                        var v = layoutInflater.inflate(R.layout.add_item,null)

                        var names : EditText = v.findViewById(R.id.name)
                        var user :EditText = v.findViewById(R.id.user)
                        var pass :EditText = v.findViewById(R.id.pass)
                        var diachi :EditText = v.findViewById(R.id.diachi)
                        var sdt :EditText = v.findViewById(R.id.sdt)

                        names.text  = Editable.Factory.getInstance().newEditable(person.fullname)
                        user.text = Editable.Factory.getInstance().newEditable(person.username)
                        pass.text  = Editable.Factory.getInstance().newEditable(person.password)
                        diachi.text = Editable.Factory.getInstance().newEditable(person.diachi)
                        sdt.text  = Editable.Factory.getInstance().newEditable(person.sdt)


                        MaterialAlertDialogBuilder(context)
                            .setView(v)
                            .setPositiveButton("Lưu") { dialog,which ->
                                update(person.id,names.text.toString(),user.text.toString(),pass.text.toString(),diachi.text.toString(),sdt.text.toString())
                                var p = InfoUser(diachi.text.toString(),names.text.toString(),person.id,pass.text.toString(),person.phanquyen,sdt.text.toString(),user.text.toString())
                                list?.set(position,p)
                                notifyItemChanged(position)
                                notifyDataSetChanged()
                                dialog.dismiss()
                            }.setNegativeButton("canel"){ dialog,which ->
                                dialog.dismiss()
                            }.show()
                    }else{
                        Toast.makeText(context,"bạn không phải admin hoặc bạn không thể sửa thông tin người khác",Toast.LENGTH_SHORT).show()

                    }


                    true
                }
                R.id.delete -> {
                    if(info.phanquyen == 0){
                        Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show()
                        MaterialAlertDialogBuilder(context)
                            .setMessage("ban co muon xoa")
                            .setPositiveButton("xóa") { dialog,which ->
                                delete(person.id)
                                notifyItemRemoved(position)
                                list?.removeAt(position)
                                notifyDataSetChanged()
                            }.setNegativeButton("canel"){ dialog,which ->
                                dialog.dismiss()
                            }.show()
                    }else{
                        Toast.makeText(context,"Chỉ admin mới thực hiện được chức năng này",Toast.LENGTH_SHORT).show()
                    }




                    true
                }

                else -> false
            }

        }
        popupMenu.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible=true
        val menu = popup.get(popupMenu)
        menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java).invoke(menu,true)
    }

    fun delete(id : String){
        var retroInstance = retroInstance.getRetroInstance()
        var retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.delete(id)
        call.enqueue(object  : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,"that bai",Toast.LENGTH_SHORT).show()

            }

        })

    }

    fun update(id : String,fullname:String,user:String,password:String,diachi:String,sdt:String){
        var retroInstance = retroInstance.getRetroInstance()
        var retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.update(id,fullname,user,password,diachi,sdt)
        call.enqueue(object  : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(context,"thanh cong",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context,"that bai",Toast.LENGTH_SHORT).show()

            }

        })

    }

    override fun getItemCount(): Int {
        if(list != null){
            return list!!.size
        }
        return 0
    }

}