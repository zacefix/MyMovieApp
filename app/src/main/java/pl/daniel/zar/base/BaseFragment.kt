package pl.daniel.zar.base

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import pl.daniel.zar.MainActivity

open class BaseFragment : Fragment() {

    fun showLoadingBar(show: Boolean) {
        (activity as MainActivity).showProgressBar(show)
    }

    fun showErrorSnackBar(message: String, okButton: Boolean = false) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).apply {
            takeIf { okButton }?.apply { setAction("OK") { dismiss() } }
            animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        }.show()
    }
}