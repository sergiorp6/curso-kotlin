package es.pue.navegadorweb

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val actionName = "es.pue.navegadorweb.DING_DONG"
    private val iFilterDingDong = IntentFilter(actionName)
    private val iFilterBatteryLow = IntentFilter(Intent.ACTION_BATTERY_LOW)
    private val iFilterMyServiceDestroyed = IntentFilter(MyService.SERVICE_DESTROYED)
    private val receiver = MyReceiver()
    private lateinit var  service: MyService
    private val myServiceDestroyedReceiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            tvServiceDestroyed.text = "Servicio destruído"
        }
    }
    private val connection = object:ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("CONN","Servicio desconectado")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            this@MainActivity.service = (service as MyService.CalculadoraBinder).service
        }
    }

    companion object {
        const val message = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Intent().apply {
                action = actionName
                putExtra(message, resources.getString(R.string.warning))
                sendBroadcast(this)
            }
        }

        bt_start_service.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        bt_bind_service.setOnClickListener {
            val i = Intent(this,MyService::class.java)
            bindService(i,connection,0)
        }

        bt_sumar.setOnClickListener {
            tvJob.text = "${this.service.sumar(10,10)}"
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, iFilterDingDong)
        registerReceiver(receiver, iFilterBatteryLow)
        registerReceiver(myServiceDestroyedReceiver, iFilterMyServiceDestroyed)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
        unregisterReceiver(myServiceDestroyedReceiver)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the actionName bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle actionName bar item clicks here. The actionName bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
