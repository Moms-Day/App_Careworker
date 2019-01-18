package momsday.careworker.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import momsday.careworker.R
import momsday.careworker.adapter.ChatRecyclerViewAdapter
import momsday.careworker.model.MainRecyclerChatItem
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import momsday.careworker.util.getId
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


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

            sendPostToFCM(edit_chat_message.text.toString(),id)

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

    private fun sendPostToFCM(message: String, id: String) {

        val firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseDatabase.getReference("users").child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                sendNotify(message, dataSnapshot.value!!.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


    }

    private fun sendNotify(message: String, daughterToken: String) {
        val FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send"
        val SERVER_KEY = "AAAA3AwqIjw:APA91bFUYswTxWgsFddoDI7LOen1w97GzIvhjM5y5OK-x0sgT2WLyE__W3XpRCiuYPAxj4fdQtoyO16e5vSwDiASbu2E-734PXfB9Bp5lsM3K0rV17atvYfs7LYXuawGwWwwRu5igpfh0JXzhLlzJ_VSf1pPus_F-Q"

        Thread(Runnable {
            try {
                // FMC 메시지 생성 start
                val root = JSONObject()
                val notification = JSONObject()
                notification.put("body", message)
                notification.put("title", getString(R.string.app_name))
                root.put("notification", notification)
                root.put("to", daughterToken)

                Log.d("Debug", "상대방토큰토큰토큰  $daughterToken")
                // FMC 메시지 생성 end

                val Url = URL(FCM_MESSAGE_URL)
                val conn = Url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doOutput = true
                conn.doInput = true
                conn.addRequestProperty("Authorization", "key=$SERVER_KEY")
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-type", "application/json")
                val os = conn.outputStream
                os.write(root.toString().toByteArray(charset("utf-8")))
                os.flush()
                conn.responseCode
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
}
