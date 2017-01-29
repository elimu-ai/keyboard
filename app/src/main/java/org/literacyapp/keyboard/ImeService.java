package org.literacyapp.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Locale;

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
        MediaPlayer mediaPlayer = null;
        switch(keyCode) {
            case 48:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_0);
                mediaPlayer.start();
                break;
            case 49:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_1);
                mediaPlayer.start();
                break;
            case 50:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_2);
                mediaPlayer.start();
                break;
            case 51:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_3);
                mediaPlayer.start();
                break;
            case 52:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_4);
                mediaPlayer.start();
                break;
            case 53:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_5);
                mediaPlayer.start();
                break;
            case 54:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_6);
                mediaPlayer.start();
                break;
            case 55:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_7);
                mediaPlayer.start();
                break;
            case 56:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_8);
                mediaPlayer.start();
                break;
            case 57:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_9);
                mediaPlayer.start();
                break;

            case 97:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_a);
                mediaPlayer.start();
                break;
            case 98:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_b);
                mediaPlayer.start();
                break;
            case 99:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_c);
                mediaPlayer.start();
                break;
            case 100:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_d);
                mediaPlayer.start();
                break;
            case 101:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_e);
                mediaPlayer.start();
                break;
            case 102:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_f);
                mediaPlayer.start();
                break;
            case 103:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_g);
                mediaPlayer.start();
                break;
            case 104:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_h);
                mediaPlayer.start();
                break;
            case 105:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_i);
                mediaPlayer.start();
                break;
            case 106:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_j);
                mediaPlayer.start();
                break;
            case 107:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_k);
                mediaPlayer.start();
                break;
            case 108:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_l);
                mediaPlayer.start();
                break;
            case 109:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_m);
                mediaPlayer.start();
                break;
            case 110:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_n);
                mediaPlayer.start();
                break;
            case 111:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_o);
                mediaPlayer.start();
                break;
            case 112:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_p);
                mediaPlayer.start();
                break;
            case 113:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_qu);
                mediaPlayer.start();
                break;
            case 114:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_r);
                mediaPlayer.start();
                break;
            case 115:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_s);
                mediaPlayer.start();
                break;
            case 116:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_t);
                mediaPlayer.start();
                break;
            case 117:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_u);
                mediaPlayer.start();
                break;
            case 118:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_v);
                mediaPlayer.start();
                break;
            case 119:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_w);
                mediaPlayer.start();
                break;
            case 120:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_x);
                mediaPlayer.start();
                break;
            case 121:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_y);
                mediaPlayer.start();
                break;
            case 122:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_z);
                mediaPlayer.start();
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
        MediaPlayer mediaPlayer = null;
        switch(keyCode) {
            case 48:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_0);
                mediaPlayer.start();
                break;
            case 49:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_1);
                mediaPlayer.start();
                break;
            case 50:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_2);
                mediaPlayer.start();
                break;
            case 51:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_3);
                mediaPlayer.start();
                break;
            case 52:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_4);
                mediaPlayer.start();
                break;
            case 53:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_5);
                mediaPlayer.start();
                break;
            case 54:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_6);
                mediaPlayer.start();
                break;
            case 55:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_7);
                mediaPlayer.start();
                break;
            case 56:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_8);
                mediaPlayer.start();
                break;
            case 57:
                mediaPlayer = MediaPlayer.create(this, R.raw.digit_9);
                mediaPlayer.start();
                break;

            case 97:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_a);
                mediaPlayer.start();
                break;
            case 98:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_b);
                mediaPlayer.start();
                break;
            case 99:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_c);
                mediaPlayer.start();
                break;
            case 100:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_d);
                mediaPlayer.start();
                break;
            case 101:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_e);
                mediaPlayer.start();
                break;
            case 102:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_f);
                mediaPlayer.start();
                break;
            case 103:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_g);
                mediaPlayer.start();
                break;
            case 104:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_h);
                mediaPlayer.start();
                break;
            case 105:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_i);
                mediaPlayer.start();
                break;
            case 106:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_j);
                mediaPlayer.start();
                break;
            case 107:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_k);
                mediaPlayer.start();
                break;
            case 108:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_l);
                mediaPlayer.start();
                break;
            case 109:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_m);
                mediaPlayer.start();
                break;
            case 110:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_n);
                mediaPlayer.start();
                break;
            case 111:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_o);
                mediaPlayer.start();
                break;
            case 112:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_p);
                mediaPlayer.start();
                break;
            case 114:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_r);
                mediaPlayer.start();
                break;
            case 115:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_s);
                mediaPlayer.start();
                break;
            case 116:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_t);
                mediaPlayer.start();
                break;
            case 117:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_u);
                mediaPlayer.start();
                break;
            case 118:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_v);
                mediaPlayer.start();
                break;
            case 119:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_w);
                mediaPlayer.start();
                break;
            case 121:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_y);
                mediaPlayer.start();
                break;
            case 122:
                mediaPlayer = MediaPlayer.create(this, R.raw.letter_sound_z);
                mediaPlayer.start();
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
