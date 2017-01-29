package org.literacyapp.keyboard;

import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import org.literacyapp.keyboard.receiver.StudentUpdatedReceiver;
import org.literacyapp.keyboard.util.MediaPlayerHelper;

import java.util.Locale;
import java.util.Set;

public class ImeService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;

    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public void onCreate() {
        Log.i(getClass().getName(), "onCreate");
        super.onCreate();
    }

    @Override
    public View onCreateInputView() {
        Log.i(getClass().getName(), "onCreateInputView");

        // Personalize available letters/numbers
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> availableLettersSet = sharedPreferences.getStringSet(StudentUpdatedReceiver.PREF_STUDENT_LETTERS, null);
        Log.d(getClass().getName(), "availableLettersSet: " + availableLettersSet);
        Set<String> availableNumbersSet = sharedPreferences.getStringSet(StudentUpdatedReceiver.PREF_STUDENT_NUMBERS, null);
        Log.d(getClass().getName(), "availableNumbersSet: " + availableNumbersSet);
        // TODO

        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);

        return keyboardView;
    }

    @Override
    public void updateFullscreenMode() {
        Log.i(getClass().getName(), "updateFullscreenMode");
//        super.updateFullscreenMode();
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();

        Locale locale = getResources().getConfiguration().locale;
        if (locale.getLanguage().startsWith("sw")) {
            playClickSw(primaryCode);
        } else {
            playClick(primaryCode);
        }

        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                keyboardView.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code), 1);
        }
    }

    private void playClick(int keyCode){
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        switch(keyCode) {
            case 48:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_0);
                break;
            case 49:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_1);
                break;
            case 50:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_2);
                break;
            case 51:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_3);
                break;
            case 52:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_4);
                break;
            case 53:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_5);
                break;
            case 54:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_6);
                break;
            case 55:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_7);
                break;
            case 56:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_8);
                break;
            case 57:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_9);
                break;

            case 97:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_a);
                break;
            case 98:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_b);
                break;
            case 99:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_c);
                break;
            case 100:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_d);
                break;
            case 101:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_e);
                break;
            case 102:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_f);
                break;
            case 103:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_g);
                break;
            case 104:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_h);
                break;
            case 105:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_i);
                break;
            case 106:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_j);
                break;
            case 107:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_k);
                break;
            case 108:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_l);
                break;
            case 109:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_m);
                break;
            case 110:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_n);
                break;
            case 111:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_o);
                break;
            case 112:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_p);
                break;
            case 113:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_qu);
                break;
            case 114:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_r);
                break;
            case 115:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_s);
                break;
            case 116:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_t);
                break;
            case 117:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_u);
                break;
            case 118:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_v);
                break;
            case 119:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_w);
                break;
            case 120:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_x);
                break;
            case 121:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_y);
                break;
            case 122:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_z);
                break;

            case 32:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    private void playClickSw(int keyCode){
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        switch(keyCode) {
            case 48:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_0);
                break;
            case 49:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_1);
                break;
            case 50:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_2);
                break;
            case 51:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_3);
                break;
            case 52:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_4);
                break;
            case 53:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_5);
                break;
            case 54:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_6);
                break;
            case 55:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_7);
                break;
            case 56:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_8);
                break;
            case 57:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.digit_9);
                break;

            case 97:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_a);
                break;
            case 98:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_b);
                break;
            case 99:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_c);
                break;
            case 100:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_d);
                break;
            case 101:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_e);
                break;
            case 102:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_f);
                break;
            case 103:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_g);
                break;
            case 104:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_h);
                break;
            case 105:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_i);
                break;
            case 106:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_j);
                break;
            case 107:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_k);
                break;
            case 108:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_l);
                break;
            case 109:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_m);
                break;
            case 110:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_n);
                break;
            case 111:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_o);
                break;
            case 112:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_p);
                break;
            case 114:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_r);
                break;
            case 115:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_s);
                break;
            case 116:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_t);
                break;
            case 117:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_u);
                break;
            case 118:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_v);
                break;
            case 119:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_w);
                break;
            case 121:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_y);
                break;
            case 122:
                MediaPlayerHelper.play(getApplicationContext(), R.raw.letter_sound_z);
                break;

            case 32:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
