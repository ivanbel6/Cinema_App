package com.example.cinema_app.presentation

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.cinema_app.R
import com.example.cinema_app.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val topItems = listOf("Action", "Drama", "Comedy","Documentary")
    private val bottomItems = listOf("Football", "Basketball", "Baseball","Tennis")

    private var isTopExpanded = false
    private var isBottomExpanded = false

    companion object {
        val selectedMoviesAndSeries = mutableListOf<String>()
        val selectedSports = mutableListOf<String>()

        @JvmStatic
        fun newInstance() = SecondFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movableTop.setOnClickListener {
            toggleTopExpansion()
        }

        binding.movableBottom.setOnClickListener {
            binding.movableBottom.setBackgroundColor(resources.getColor(R.color.transparent_genre))
            toggleBottomExpansion()
        }
    }

    private fun toggleTopExpansion() {
        if (isTopExpanded) {
            removeItems(binding.movableTop, topItems.size)
            animateView(binding.movableTop, false)
        } else {
            animateView(binding.movableTop, true)
            addItems(binding.movableTop, topItems, true)
        }
        isTopExpanded = !isTopExpanded
        rotateArrow(binding.movableTop, isTopExpanded)
    }

    private fun toggleBottomExpansion() {
        if (isBottomExpanded) {
            binding.movableBottom.setBackgroundResource(R.drawable.half_rounded_lay_bottom)
            removeItems(binding.movableBottom, bottomItems.size)
            animateView(binding.movableBottom, false)
            animateView(binding.movableTop, false, false)
        } else {
            animateView(binding.movableBottom, true)
            animateView(binding.movableTop, true, true)
            addItems(binding.movableBottom, bottomItems, false)
        }
        isBottomExpanded = !isBottomExpanded
        rotateArrow(binding.movableBottom, isBottomExpanded)
    }

    private fun animateView(view: LinearLayout, expand: Boolean, alsoMoveTop: Boolean = false) {
        val moveDistance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
        val translationY = if (expand) -moveDistance else 0f

        view.animate().translationY(translationY).setDuration(1).start()

        if (alsoMoveTop && view == binding.movableBottom) {
            binding.movableTop.animate().translationY(translationY).setDuration(1).start()
        }
    }

    private fun rotateArrow(view: LinearLayout, isExpanded: Boolean) {
        val arrow = view.getChildAt(1) as ImageView
        val rotationAngle = if (isExpanded) 90f else 0f
        arrow.animate().rotation(rotationAngle).setDuration(300).start()
    }

    private fun addItems(view: LinearLayout, items: List<String>, isTop: Boolean) {
        val parentLayout = view.parent as LinearLayout
        val index = parentLayout.indexOfChild(view) + 1

        items.forEach { item ->
            val textView = TextView(requireContext()).apply {
                text = item
                textSize = 16f
                setTextColor(resources.getColor(R.color.silverDarker, null))
                typeface = ResourcesCompat.getFont(requireContext(), R.font.m_plus_rounded_1c_bold)
                id = View.generateViewId()
                setPadding(0, 10, 0, 10)
                visibility = View.INVISIBLE
            }

            val checkBox = CheckBox(requireContext()).apply {
                visibility = View.INVISIBLE
                id = View.generateViewId()
                setPadding(0, 5, 16, 5)
                isChecked = if (isTop) {
                    selectedMoviesAndSeries.contains(item)
                } else {
                    selectedSports.contains(item)
                }
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (isTop) {
                            selectedMoviesAndSeries.add(item)
                        } else {
                            selectedSports.add(item)
                        }
                    } else {
                        if (isTop) {
                            selectedMoviesAndSeries.remove(item)
                        } else {
                            selectedSports.remove(item)
                        }
                    }
                }
            }

            val newLayout = ConstraintLayout(requireContext()).apply {
                id = View.generateViewId()
                addView(textView)
                addView(checkBox)
            }

            val constraintSet = ConstraintSet().apply {
                clone(newLayout)
                connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 32)
                connect(textView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
                connect(checkBox.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 32)
                connect(checkBox.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
                connect(checkBox.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            }
            constraintSet.applyTo(newLayout)

            parentLayout.addView(newLayout, index)

            val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_in).apply {
                duration = 700
            }
            newLayout.startAnimation(fadeInAnimation)
            textView.visibility = View.VISIBLE
            checkBox.visibility = View.VISIBLE
        }
    }

    private fun removeItems(view: LinearLayout, itemCount: Int) {
        val parentLayout = view.parent as LinearLayout
        val index = parentLayout.indexOfChild(view) + 1

        repeat(itemCount) {
            parentLayout.removeViewAt(index)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
