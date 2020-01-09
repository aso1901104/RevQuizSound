package jp.ac.asojuku.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quest.*

class QuestActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private var quesSound = 0
    private var rightSound = 0
    private var wrongSound = 0
    private var crapSound = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
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

        quesSound = soundPool.load(this,R.raw.question1,1)
        rightSound = soundPool.load(this,R.raw.right,1)
        wrongSound = soundPool.load(this,R.raw.wrong,1)
        crapSound = soundPool.load(this,R.raw.crap,1)

        //[まちがい]が押された時の処理
        wrong.setOnClickListener{
            soundPool.play(wrongSound,1.0f,1.0f,1,0,1.0f)
        }

        //[せいかい]が押された時の処理
        right.setOnClickListener{
            soundPool.play(rightSound,1.0f,1.0f,1,0,1.0f)
        }

        //[しゅつだい]が押された時の処理
        questions.setOnClickListener{
            soundPool.play(quesSound,1.0f,1.0f,1,0,1.0f)
        }

        //[はくしゅ]が押された時の処理
        handclap.setOnClickListener{
            soundPool.play(crapSound,1.0f,1.0f,1,0,1.0f)
        }

        ansButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
