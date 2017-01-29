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

    public static final String PREF_STUDENT_NUMBERS = "pref_student_numbers";
    public static final String PREF_STUDENT_NUMERACY_SKILLS = "pref_student_numeracy_skills";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(getClass().getName(), "onReceive");

        // Customize the user interface to match the current Student's level

        ArrayList<String> availableNumbers = intent.getStringArrayListExtra("availableNumbers");
        Log.i(getClass().getName(), "availableNumbers: " + availableNumbers);

        ArrayList<String> availableNumeracySkills = intent.getStringArrayListExtra("availableNumeracySkills");
        Log.i(getClass().getName(), "availableNumeracySkills: " + availableNumeracySkills);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (availableNumbers != null) {
            Set<String> availableNumberSet = new HashSet<>();
            for (String availableNumber : availableNumbers) {
                availableNumberSet.add(availableNumber);
            }
            Log.i(getClass().getName(), "Storing availableNumbersSet: " + availableNumberSet);
            sharedPreferences.edit().putStringSet(PREF_STUDENT_NUMBERS, availableNumberSet).commit();
        }

        if (availableNumeracySkills != null) {
            Set<String> availableNumeracySkillSet = new HashSet<>();
            for (String availableNumeracySkill : availableNumeracySkills) {
                availableNumeracySkillSet.add(availableNumeracySkill);
            }
            Log.i(getClass().getName(), "Storing availableNumeracySkillSet: " + availableNumeracySkillSet);
            sharedPreferences.edit().putStringSet(PREF_STUDENT_NUMERACY_SKILLS, availableNumeracySkillSet).commit();
        }
    }
}
