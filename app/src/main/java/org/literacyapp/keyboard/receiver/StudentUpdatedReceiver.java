package org.literacyapp.keyboard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentUpdatedReceiver extends BroadcastReceiver {

    public static final String PREF_STUDENT_LETTERS = "pref_student_letters";
    public static final String PREF_STUDENT_NUMBERS = "pref_student_numbers";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(getClass().getName(), "onReceive");

        // Customize the user interface to match the current Student's level

        ArrayList<String> availableLetters = intent.getStringArrayListExtra("availableLetters");
        Log.i(getClass().getName(), "availableLetters: " + availableLetters);

        ArrayList<String> availableNumbers = intent.getStringArrayListExtra("availableNumbers");
        Log.i(getClass().getName(), "availableNumbers: " + availableNumbers);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (availableLetters != null) {
            Set<String> availableLetterSet = new HashSet<>();
            for (String availableLetter : availableLetters) {
                availableLetterSet.add(availableLetter);
            }
            Log.i(getClass().getName(), "Storing availableLettersSet: " + availableLetterSet);
            sharedPreferences.edit().putStringSet(PREF_STUDENT_LETTERS, availableLetterSet).commit();
        }

        if (availableNumbers != null) {
            Set<String> availableNumberSet = new HashSet<>();
            for (String availableNumber : availableNumbers) {
                availableNumberSet.add(availableNumber);
            }
            Log.i(getClass().getName(), "Storing availableNumbersSet: " + availableNumberSet);
            sharedPreferences.edit().putStringSet(PREF_STUDENT_NUMBERS, availableNumberSet).commit();
        }
    }
}
