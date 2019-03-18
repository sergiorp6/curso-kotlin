package es.pue.activitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var countOnCreate=0
    private var countOnRestart=0
    private var countOnResume=0
    private var countOnStart=0
    private var countOnPause=0
    private var countOnStop=0
    private var countOnDestroy=0

    private val tvOnCreateId=0
    private val tvOnRestartId=1
    private val tvOnResumeId=2
    private val tvOnStartId=3
    private val tvOnPauseId=4
    private val tvOnStopId=5
    private val tvOnDestroyId=6

    private val tvList = ArrayList<TextView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tvOnCreate = TextView(this)
        tvOnCreate.id=tvOnCreateId
        tvList.add(tvOnCreate)

        val tvOnRestart = TextView(this)
        tvOnRestart.id=tvOnRestartId
        tvList.add(tvOnRestart)

        val tvOnStart = TextView(this)
        tvOnStart.id=tvOnStartId
        tvList.add(tvOnStart)

        val tvOnResume = TextView(this)
        tvOnResume.id=tvOnResumeId
        tvList.add(tvOnResume)

        val tvOnPause = TextView(this)
        tvOnPause.id=tvOnPauseId
        tvList.add(tvOnPause)

        val tvOnStop = TextView(this)
        tvOnStop.id=tvOnStopId
        tvList.add(tvOnStop)

        val tvOnDestroy = TextView(this)
        tvOnDestroy.id=tvOnDestroyId
        tvList.add(tvOnDestroy)

    }

    override fun onRestart(){
        super.onRestart();
        ++countOnRestart;
    }
    override fun onResume(){
        super.onResume();
        ++countOnResume;
        generateView()
    }
    override fun onStart(){
        super.onStart();
        ++countOnStart;
    }
    override fun onPause(){
        super.onPause();
        ++countOnPause;

    }
    override fun onStop(){
        super.onStop();
        ++countOnStop;
    }

    override fun onDestroy(){
        super.onDestroy();
        ++countOnDestroy;
    }


    private fun generateView(){

        lyMain.removeAllViews();

        var textViewParent:TextView?=null

        val set=ConstraintSet();



        for(textView in tvList){

            when(textView.id){
                tvOnCreateId -> textView.text="On Create $countOnCreate"
                tvOnRestartId -> textView.text="On Destroy $countOnRestart"
                tvOnPauseId -> textView.text="On Pause $countOnPause"
                tvOnResumeId -> textView.text="On Resume $countOnResume"
                tvOnStartId -> textView.text="On Start $countOnStart"
                tvOnStopId -> textView.text="On Stop $countOnStop"
                tvOnDestroyId -> textView.text="On Destroy $countOnDestroy"
            }

            lyMain.addView(textView)

            set.clone(lyMain)
            if(textViewParent===null) {
                set.connect(
                    textView.id, ConstraintSet.TOP
                    , lyMain.id, ConstraintSet.TOP, 0
                )
                set.connect(
                    textView.id, ConstraintSet.LEFT
                    , lyMain.id, ConstraintSet.LEFT, 100
                )
            }else{
                set.connect(
                    textView.id, ConstraintSet.TOP
                    , textViewParent.id, ConstraintSet.TOP, 100
                )
                set.connect(
                    textView.id, ConstraintSet.LEFT
                    , lyMain.id, ConstraintSet.LEFT, 100
                )
            }
            set.applyTo(lyMain);

            textViewParent=textView


        }

    }


}
