package jp.ac.asojuku.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool:SoundPool
    private var ansSound = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        soundPool =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                SoundPool(2, AudioManager.STREAM_SYSTEM, 0)
            } else {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build()
            }

        ansSound = soundPool.load(this,R.raw.answer,1)

        qesButton.setOnClickListener{
            val intent = Intent(this,QuestActivity::class.java)
            startActivity(intent)
        }

        //[答える]ボタンを押した時の処理
        answerButton.setOnClickListener{
            soundPool.play(ansSound,1.0f,1.0f,1,0,1.0f)
        }


    }


}
