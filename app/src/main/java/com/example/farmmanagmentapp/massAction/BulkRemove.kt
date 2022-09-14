package com.example.farmmanagmentapp.massAction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentBulkRemoveBinding
import io.realm.Realm

class BulkRemove : Fragment() {

    private var binding: FragmentBulkRemoveBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding

        val rootView = inflater.inflate(R.layout.fragment_bulk_remove,container,false)


        val scrollView = rootView.findViewById<ScrollView>(R.id.scrollView)

        val linearLayout = rootView.findViewById<LinearLayout>(R.id.linearLayout)
        val staticEditText = rootView.findViewById<EditText>(R.id.staticSearchBarEditText)
        val staticImageView = rootView.findViewById<ImageView>(R.id.staticSearchBarImageView)
        val movingEditText = rootView.findViewById<EditText>(R.id.movingSearchBarEditText)
        val movingImageView = rootView.findViewById<ImageView>(R.id.movingSearchBarImageView)


        val realm = Realm.getDefaultInstance()

        for (i in 1..20){
            val horLayout = LinearLayout(activity)

            for (t in 1..4) {
                val btn = Button(activity)
                btn.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                btn.text = i.toString() + " , " + t.toString()
                horLayout.addView(btn)
            }
            linearLayout.addView(horLayout)
        }


        //Making the image disappear when the search bar is clicked
        movingEditText.setOnFocusChangeListener { _, _ -> movingImageView.visibility = View.GONE }
        staticEditText.setOnFocusChangeListener { _, _ -> staticImageView.visibility = View.GONE }

        //Making the search bar stick to the top of the screen by alternating
        //between a searchbar in the scroll view and a static one
        scrollView.setOnScrollChangeListener { _, _, i, _, i2 ->
            if ((i in (i2 + 1)..312)||(i<440 && i<i2)){
                movingEditText.visibility = View.VISIBLE

                //Keeping icon hidden if still focused on text edit
                if (!(staticEditText.hasFocus()||movingEditText.hasFocus())) {
                    movingImageView.visibility = View.VISIBLE
                }

                //change focus from one text edit field to the other copying text across
                if (staticEditText.hasFocus()){
                    movingEditText.text = staticEditText.text
                    movingEditText.setSelection(movingEditText.length())
                    movingEditText.requestFocus()
                }

                staticEditText.visibility = View.GONE
                staticImageView.visibility = View.GONE
            } else {
                staticEditText.visibility = View.VISIBLE

                //Keep search icon hidden if still focused on textedit
                if(!(staticEditText.hasFocus()||movingEditText.hasFocus())) {
                    staticImageView.visibility = View.VISIBLE
                }

                //change focus from one text edit field to the other copying text across
                if (movingEditText.hasFocus()) {
                    staticEditText.text = movingEditText.text
                    staticEditText.setSelection(staticEditText.length())
                    staticEditText.requestFocus()
                }
                movingEditText.visibility = View.INVISIBLE
                movingImageView.visibility = View.INVISIBLE

            }
        }
        return rootView
    }

}