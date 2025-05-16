package ai.elimu.keyboard.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log

class StudentUpdatedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Log.i(javaClass.getName(), "onReceive")

        // Customize the user interface to match the current Student's level
        val availableLetters = intent.getStringArrayListExtra("availableLetters")
        Log.i(javaClass.getName(), "availableLetters: " + availableLetters)

        val availableNumbers = intent.getStringArrayListExtra("availableNumbers")
        Log.i(javaClass.getName(), "availableNumbers: " + availableNumbers)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        if (availableLetters != null) {
            val availableLetterSet: MutableSet<String?> = HashSet<String?>()
            for (availableLetter in availableLetters) {
                availableLetterSet.add(availableLetter)
            }
            Log.i(javaClass.getName(), "Storing availableLettersSet: " + availableLetterSet)
            sharedPreferences.edit().putStringSet(PREF_STUDENT_LETTERS, availableLetterSet).commit()
        }

        if (availableNumbers != null) {
            val availableNumberSet: MutableSet<String?> = HashSet<String?>()
            for (availableNumber in availableNumbers) {
                availableNumberSet.add(availableNumber)
            }
            Log.i(javaClass.getName(), "Storing availableNumbersSet: " + availableNumberSet)
            sharedPreferences.edit().putStringSet(PREF_STUDENT_NUMBERS, availableNumberSet).commit()
        }
    }

    companion object {
        const val PREF_STUDENT_LETTERS: String = "pref_student_letters"
        const val PREF_STUDENT_NUMBERS: String = "pref_student_numbers"
    }
}
