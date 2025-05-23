package ai.elimu.keyboard.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.edit

class StudentUpdatedReceiver : BroadcastReceiver() {
    
    private val TAG = javaClass.getName()
    
    override fun onReceive(context: Context?, intent: Intent) {
        Log.i(TAG, "onReceive")

        // Customize the user interface to match the current Student's level
        val availableLetters = intent.getStringArrayListExtra("availableLetters")
        Log.i(TAG, "availableLetters: $availableLetters")

        val availableNumbers = intent.getStringArrayListExtra("availableNumbers")
        Log.i(TAG, "availableNumbers: $availableNumbers")

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        if (availableLetters != null) {
            val availableLetterSet: MutableSet<String?> = HashSet<String?>()
            for (availableLetter in availableLetters) {
                availableLetterSet.add(availableLetter)
            }
            Log.i(TAG, "Storing availableLettersSet: $availableLetterSet")
            sharedPreferences.edit(commit = true) {
                putStringSet(
                    PREF_STUDENT_LETTERS,
                    availableLetterSet
                )
            }
        }

        if (availableNumbers != null) {
            val availableNumberSet: MutableSet<String?> = HashSet<String?>()
            for (availableNumber in availableNumbers) {
                availableNumberSet.add(availableNumber)
            }
            Log.i(TAG, "Storing availableNumbersSet: $availableNumberSet")
            sharedPreferences.edit(commit = true) {
                putStringSet(
                    PREF_STUDENT_NUMBERS,
                    availableNumberSet
                )
            }
        }
    }

    companion object {
        const val PREF_STUDENT_LETTERS: String = "pref_student_letters"
        const val PREF_STUDENT_NUMBERS: String = "pref_student_numbers"
    }
}
