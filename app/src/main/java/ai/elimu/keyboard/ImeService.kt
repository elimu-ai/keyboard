package ai.elimu.keyboard

import ai.elimu.keyboard.receiver.StudentUpdatedReceiver
import ai.elimu.keyboard.util.MediaPlayerHelper
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
import android.media.AudioManager
import android.preference.PreferenceManager
import android.util.Log
import android.view.KeyEvent
import android.view.View

class ImeService : InputMethodService(), OnKeyboardActionListener {
    
    private val TAG = javaClass.getName()

    private var keyboardView: KeyboardView? = null

    private var keyboard: Keyboard? = null

    private var caps = false

    override fun onCreate() {
        Log.i(TAG, "onCreate")
        super.onCreate()
    }

    override fun onCreateInputView(): View {
        Log.i(TAG, "onCreateInputView")

        // Personalize available letters/numbers
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val availableLettersSet =
            sharedPreferences.getStringSet(StudentUpdatedReceiver.PREF_STUDENT_LETTERS, null)
        Log.d(TAG, "availableLettersSet: $availableLettersSet")
        val availableNumbersSet =
            sharedPreferences.getStringSet(StudentUpdatedReceiver.PREF_STUDENT_NUMBERS, null)
        Log.d(TAG, "availableNumbersSet: $availableNumbersSet")

        // TODO: add custom Keyboard
        if (availableLettersSet == null) {
            keyboard = Keyboard(this, R.xml.qwerty)
        } else if (availableLettersSet.size <= 3) {
            keyboard = Keyboard(this, R.xml.qwerty)
        } else if (availableLettersSet.size == 4) {
            keyboard = Keyboard(this, R.xml.qwerty_4)
        } else if (availableLettersSet.size == 5) {
            keyboard = Keyboard(this, R.xml.qwerty_5)
        } else if (availableLettersSet.size == 6) {
            keyboard = Keyboard(this, R.xml.qwerty_6)
        } else if (availableLettersSet.size == 7) {
            keyboard = Keyboard(this, R.xml.qwerty_7)
        } else if (availableLettersSet.size == 8) {
            keyboard = Keyboard(this, R.xml.qwerty_8)
        } else {
            keyboard = Keyboard(this, R.xml.qwerty_8)
        }

        keyboardView = layoutInflater.inflate(R.layout.keyboard, null) as KeyboardView
        keyboardView!!.setKeyboard(keyboard)
        keyboardView!!.isPreviewEnabled = false
        keyboardView!!.setOnKeyboardActionListener(this)

        return keyboardView!!
    }

    override fun updateFullscreenMode() {
        Log.i(TAG, "updateFullscreenMode")
        //        super.updateFullscreenMode();
    }

    override fun onPress(i: Int) {
    }

    override fun onRelease(i: Int) {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = getCurrentInputConnection()

        val locale = resources.configuration.locale
        Log.d(TAG, "locale: $locale")
        playClick(primaryCode)

        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> ic.deleteSurroundingText(1, 0)
            Keyboard.KEYCODE_SHIFT -> {
                caps = !caps
                keyboard!!.setShifted(caps)
                keyboardView!!.invalidateAllKeys()
            }

            Keyboard.KEYCODE_DONE -> ic.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER
                )
            )

            else -> {
                var code = primaryCode.toChar()
                if (Character.isLetter(code) && caps) {
                    code = code.uppercaseChar()
                }
                ic.commitText(code.toString(), 1)
            }
        }
    }

    private fun playClick(keyCode: Int) {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        when (keyCode) {
            48 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_0)
            49 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_1)
            50 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_2)
            51 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_3)
            52 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_4)
            53 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_5)
            54 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_6)
            55 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_7)
            56 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_8)
            57 -> MediaPlayerHelper.play(applicationContext, R.raw.digit_9)
            97 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_a)
            98 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_b)
            99 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_c)
            100 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_d)
            101 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_e)
            102 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_f)
            103 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_g)
            104 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_h)
            105 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_i)
            106 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_j)
            107 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_k)
            108 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_l)
            109 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_m)
            110 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_n)
            111 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_o)
            112 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_p)
            113 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_qu)
            114 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_r)
            115 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_s)
            116 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_t)
            117 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_u)
            118 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_v)
            119 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_w)
            120 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_x)
            121 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_y)
            122 -> MediaPlayerHelper.play(applicationContext, R.raw.letter_sound_z)
            32 -> audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR)
            Keyboard.KEYCODE_DONE, 10 -> audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN)
            Keyboard.KEYCODE_DELETE -> audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE)
            else -> audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD)
        }
    }

    override fun onText(charSequence: CharSequence?) {
    }

    override fun swipeLeft() {
    }

    override fun swipeRight() {
    }

    override fun swipeDown() {
    }

    override fun swipeUp() {
    }
}
