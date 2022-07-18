package developer.abdulaziz.mydictionary.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.mydictionary.R
import developer.abdulaziz.mydictionary.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        val anim = AnimationUtils.loadAnimation(binding.root.context, R.anim.my_splash_anim)
        binding.splashText.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        return binding.root
    }
}