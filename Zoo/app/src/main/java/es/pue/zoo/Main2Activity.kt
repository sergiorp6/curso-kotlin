package es.pue.zoo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : BlankFragment.OnFragmentInteractionListener, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val blankFragment = BlankFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(container.id, blankFragment)
            .commit()

        btnReplace.setOnClickListener {

            val blankFragment2 = BlankFragment2.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(container.id, blankFragment2)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onFragmentInteraction() {
        Log.i("ZOO", "Main2Activity responde a BlankFragment")
    }
}
