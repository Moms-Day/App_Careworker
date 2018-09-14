package momsday.careworker.ui.writeForm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write_form.*

import momsday.careworker.R
import momsday.careworker.model.PatientListModel
import momsday.careworker.util.gone
import momsday.careworker.util.visible
import org.jetbrains.anko.sdk25.coroutines.onClick

class WriteFormActivity : AppCompatActivity() {

    val selectFormFragment = SelectFormFragment()
    val scheduleListFragment = ScheduleListFragment()
    val addScheduleFragment = AddScheduleFragment()
    val addMealFragment = AddMealFragment()
    val addPhotoFragment = AddPhotoFragment()

    val viewModel by lazy {
        ViewModelProviders.of(this)[WriteFormViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_form)
        val intent = intent
        val patient = intent.getParcelableExtra<PatientListModel>("patient")
        tv_writeForm_title.text = patient.name
        viewModel.patient.postValue(patient)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()

        fragmentTransaction.replace(R.id.writeForm_container, selectFormFragment).commit()

        viewModel.selectedFragment.observe(this, Observer {
            val ft = fm.beginTransaction()
            when (it) {
                "Schedule" -> {
                    ft.replace(R.id.writeForm_container, ScheduleListFragment()).addToBackStack(null)
                    ft.commit()
                }
                "AddSchedule" -> {
                    ft.replace(R.id.writeForm_container, addScheduleFragment).addToBackStack(null)
                    ft.commit()
                }
                "Meal" -> {
                    ft.replace(R.id.writeForm_container, addMealFragment).addToBackStack(null)
                    ft.commit()
                }
                "Photo" -> {
                    ft.replace(R.id.writeForm_container, addPhotoFragment).addToBackStack(null)
                    ft.commit()
                }
            }
        })
    }
}
