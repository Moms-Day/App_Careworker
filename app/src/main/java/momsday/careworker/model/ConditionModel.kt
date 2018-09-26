package momsday.careworker.model

data class ConditionModel(
        val activity_reduction: Boolean = false,
        val low_temperature: Boolean = false,
        val high_fever: Boolean = false,
        val blood_pressure_increase: Boolean = false,
        val blood_pressure_reduction: Boolean = false,
        val lack_of_sleep: Boolean = false,
        val lose_Appetite: Boolean = false,
        val binge_eating: Boolean = false,
        val diarrhea: Boolean = false,
        val constipation: Boolean = false,
        val vomiting: Boolean = false,
        val urination_inconvenient: Boolean = false,
        val human_power_reduction: Boolean = false,
        val poverty_of_blood: Boolean = false,
        val cough: Boolean = false
) {
    fun isConnected(): Boolean {
        return !(!activity_reduction && !low_temperature && !high_fever &&
                !blood_pressure_increase &&
                !blood_pressure_reduction &&
                !lack_of_sleep &&
                !lose_Appetite &&
                !binge_eating &&
                !diarrhea &&
                !constipation &&
                !vomiting &&
                !urination_inconvenient &&
                !human_power_reduction &&
                !poverty_of_blood &&
                !cough)
    }
}