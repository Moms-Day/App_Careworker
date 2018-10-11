package momsday.careworker.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import momsday.careworker.R
import momsday.careworker.adapter.ChatRecyclerViewAdapter
import momsday.careworker.model.MainRecyclerChatItem
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener
import momsday.careworker.util.getId


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val id = intent.getStringExtra("id")
        text_chat_toolbar_name.text = "정경서"
        var i = 0
        val nowDate = Date(System.currentTimeMillis())
        val timeDateFormat = SimpleDateFormat("hh:mm");
        val dateDateFormat = SimpleDateFormat("yyyy년 MM월 dd일");
        val timeText = timeDateFormat.format(nowDate);
        val dateText = dateDateFormat.format(nowDate);

        FirebaseApp.initializeApp(this);

        val database = FirebaseDatabase.getInstance()
        val reference = database.reference
        val chatItem = ArrayList<MainRecyclerChatItem>()
        val adapter = ChatRecyclerViewAdapter(chatItem)

        recycler_main_chat.adapter = adapter
        recycler_main_chat.layoutManager = LinearLayoutManager(this)



        text_chat_send_message.onClick {
            val chatData = MainRecyclerChatItem(2, edit_chat_message.text.toString(), timeText, nowDate)
            reference.child("${getId(this@ChatActivity.baseContext!!)}AND$id").child("message").push().setValue(chatData)
            edit_chat_message.text.clear()
        }

        reference.child("${getId(this@ChatActivity.baseContext!!)}AND$id").child("message").addChildEventListener(object : ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatData = p0.getValue(MainRecyclerChatItem::class.java)  // chatData를 가져오고
                if (chatData!!.getItemViewType() === 2) {
                    chatItem.add(MainRecyclerChatItem(2, chatData!!.messageText, chatData.timeText, chatData.date))
                    adapter.notifyDataSetChanged()
                    recycler_main_chat.smoothScrollToPosition(chatItem.size - 1)
                }
                if (chatData!!.getItemViewType() === 1) {
                    chatItem.add(MainRecyclerChatItem(1, chatData!!.messageText, chatData.timeText, chatData.date))
                    adapter.notifyDataSetChanged()
                    recycler_main_chat.smoothScrollToPosition(chatItem.size - 1)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }


            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}


            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
