package es.pue.noconcatenaras

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val lorem =
        "Lorem fistrum por la gloria de mi madre papaar papaar va usté muy cargadoo sexuarl. Ese que llega apetecan fistro caballo blanco caballo negroorl papaar papaar a peich apetecan me cago en tus muelas. Apetecan tiene musho peligro está la cosa muy malar benemeritaar al ataquerl llevame al sircoo. Ahorarr ahorarr mamaar hasta luego Lucas papaar papaar tiene musho peligro. Condemor pecador a wan amatomaa caballo blanco caballo negroorl no te digo trigo por no llamarte Rodrigor te va a hasé pupitaa al ataquerl fistro."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btConcatenando.setOnClickListener(this);
        btNoConcatenando.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v) {
            btConcatenando -> concatenar()
            btNoConcatenando -> noConcatenar()
        }
    }

    private fun concatenar() {
        val iteraciones = Integer.parseInt(etIteraciones.text.toString())

        Concatenar().execute(iteraciones, lorem, tvTiempo)
    }

    /*
    private fun concatenar(){
        val iteraciones= Integer.parseInt(etIteraciones.text.toString())
        var localIpsum="";
        val inicio = System.currentTimeMillis()
        for(i in 0 until iteraciones){
            localIpsum+=lorem;
        }

       // Log.d("Concatenando",localIpsum.toString());
        val fin = System.currentTimeMillis()
        tvTiempo.text=(fin-inicio).toString()

    }*/

    private fun noConcatenar() {
        val iteraciones = Integer.parseInt(etIteraciones.text.toString())
        val sb = StringBuilder()
        val inicio = System.currentTimeMillis()

        for (i in 0 until iteraciones) {
            sb.append(lorem);
        }
        val fin = System.currentTimeMillis()
        tvTiempo.text = (fin - inicio).toString()
    }
}

class Concatenar : AsyncTask<Any, Any, TextView>() {

    private var tiempo: Long = 0

    override fun doInBackground(vararg params: Any?): TextView {

        val iteraciones = (params[0] as Int)
        val lorem = (params[1] as String)
        val tvTiempo = (params[2] as TextView)

        var localLorem = "";
        val inicio = System.currentTimeMillis();
        for (i in 0 until iteraciones) {
            localLorem += lorem;
        }
        val fin = System.currentTimeMillis();
        tiempo = fin - inicio

        return tvTiempo
    }

    override fun onProgressUpdate(vararg values: Any?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: TextView?) {
        super.onPostExecute(result)
        result?.text = tiempo.toString()
    }
}
