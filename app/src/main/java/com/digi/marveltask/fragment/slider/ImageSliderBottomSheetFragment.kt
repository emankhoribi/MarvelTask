package com.digi.marveltask.fragment.slider

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.digi.marveltask.R
import com.digi.marveltask.databinding.FragmentImageSliderBottomSheetBinding
import com.digi.marveltask.fragment.characterDetails.CharacterDetailsFragmentArgs
import com.digi.marveltask.fragment.slider.adapter.ImageSliderAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import java.util.Locale


class ImageSliderBottomSheetFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentImageSliderBottomSheetBinding
    val imageArgs: ImageSliderBottomSheetFragmentArgs by navArgs()
    private val numberFormat = "%d/%d"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageSliderBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageSliderAdapter = ImageSliderAdapter(requireActivity(), imageList = imageArgs.images)

        binding.sliderRv.adapter = imageSliderAdapter




        binding.closeIv.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.numberTv.text = String.format(Locale.ENGLISH, numberFormat, 1, imageArgs.images.size)
        binding.sliderRv.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.numberTv.text = String.format(Locale.ENGLISH, numberFormat, position+1, imageArgs.images.size)
            }
        })

        binding.sliderRv.currentItem = imageArgs.index

    }

    override fun onStart() {
        super.onStart()
        val dialog = getDialog()

        if (dialog != null) {
            val bottomSheet: View =
                dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT

        }
        val view = getView()
        view?.post {
            val parent: View = view.getParent() as View
            val params: CoordinatorLayout.LayoutParams =
                parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.getBehavior()
            val bottomSheetBehavior = behavior as (BottomSheetBehavior)
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());

        }
    }


}