package online.clugo19.examen_android

// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.clugo19.examen_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar el fragment por defecto (Formulario)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FormFragment())
            .commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_registro -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FormFragment())
                        .commit()
                    true
                }
                R.id.nav_registros -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RecordsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}

